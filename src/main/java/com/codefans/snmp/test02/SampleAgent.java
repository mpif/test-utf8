package com.codefans.snmp.test02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.snmp4j.MessageDispatcher;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.TransportMapping;
import org.snmp4j.agent.AgentConfigManager;
import org.snmp4j.agent.DefaultMOContextScope;
import org.snmp4j.agent.DefaultMOQuery;
import org.snmp4j.agent.DefaultMOServer;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOQuery;
import org.snmp4j.agent.MOScope;
import org.snmp4j.agent.MOServer;
import org.snmp4j.agent.ManagedObject;
import org.snmp4j.agent.cfg.EngineBootsCounterFile;
import org.snmp4j.agent.example.Snmp4jDemoMib;
import org.snmp4j.agent.io.DefaultMOPersistenceProvider;
import org.snmp4j.agent.io.MOInput;
import org.snmp4j.agent.io.MOInputFactory;
import org.snmp4j.agent.io.prop.PropertyMOInput;
import org.snmp4j.agent.mo.DefaultMOFactory;
import org.snmp4j.agent.mo.MOFactory;
import org.snmp4j.agent.mo.MOMutableTableRow;
import org.snmp4j.agent.mo.MOTableRowEvent;
import org.snmp4j.agent.mo.MOTableRowListener;
import org.snmp4j.agent.mo.snmp.TimeStamp;
import org.snmp4j.agent.mo.util.VariableProvider;
import org.snmp4j.agent.request.Request;
import org.snmp4j.agent.request.RequestStatus;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.agent.request.SubRequestIterator;
import org.snmp4j.log.JavaLogFactory;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.TransportMappings;
import org.snmp4j.util.ArgumentParser;
import org.snmp4j.util.ThreadPool;

public class SampleAgent implements VariableProvider {
	static {
		LogFactory.setLogFactory(new JavaLogFactory());
	}

	private LogAdapter logger = LogFactory.getLogger(SampleAgent.class);

	protected AgentConfigManager agent;
	protected MOServer server;
	private String configFile;
	private File bootCounterFile;

	// supported MIBs
	protected HelloModules modules;

	protected Properties tableSizeLimits;

	public SampleAgent(Map args) {
		configFile = (String) ((List) args.get("c")).get(0);
		bootCounterFile = new File((String) ((List) args.get("bc")).get(0));

		server = new DefaultMOServer();
		MOServer[] moServers = new MOServer[] { server };
		InputStream configInputStream = SampleAgent.class.getResourceAsStream("SampleAgentConfig.properties");
		if (args.containsKey("cfg")) {
			try {
				configInputStream = new FileInputStream((String) ArgumentParser.getValue(args, "cfg", 0));
			} catch (FileNotFoundException ex1) {
				ex1.printStackTrace();
			}
		}
		final Properties props = new Properties();
		try {
			props.load(configInputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		MOInputFactory configurationFactory = new MOInputFactory() {
			public MOInput createMOInput() {
				return new PropertyMOInput(props, SampleAgent.this);
			}
		};
		InputStream tableSizeLimitsInputStream = SampleAgent.class
				.getResourceAsStream("SampleAgentTableSizeLimits.properties");
		if (args.containsKey("ts")) {
			try {
				tableSizeLimitsInputStream = new FileInputStream((String) ArgumentParser.getValue(args, "ts", 0));
			} catch (FileNotFoundException ex1) {
				ex1.printStackTrace();
			}
		}
		tableSizeLimits = new Properties();
		try {
			tableSizeLimits.load(tableSizeLimitsInputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		MessageDispatcher messageDispatcher = new MessageDispatcherImpl();
		addListenAddresses(messageDispatcher, (List) args.get("address"));
		agent = new AgentConfigManager(new OctetString(MPv3.createLocalEngineID()), messageDispatcher, null, moServers,
				ThreadPool.create("SampleAgent", 3), configurationFactory,
				new DefaultMOPersistenceProvider(moServers, configFile), new EngineBootsCounterFile(bootCounterFile));
	}

	protected void addListenAddresses(MessageDispatcher md, List addresses) {
		for (Iterator it = addresses.iterator(); it.hasNext();) {
			Address address = GenericAddress.parse((String) it.next());
			TransportMapping tm = TransportMappings.getInstance().createTransportMapping(address);
			if (tm != null) {
				md.addTransportMapping(tm);
			} else {
				logger.warn("No transport mapping available for address '" + address + "'.");
			}
		}
	}

	public void run() {
		// initialize agent before registering our own modules
		agent.initialize();
		// this requires sysUpTime to be available.
		registerMIBs();
		// add proxy forwarder
		agent.setupProxyForwarder();
		// apply table size limits
		agent.setTableSizeLimits(tableSizeLimits);
		// now continue agent setup and launch it.
		agent.run();
	}

	/**
	 * Get the {@link MOFactory} that creates the various MOs (MIB Objects).
	 * 
	 * @return a {@link DefaultMOFactory} instance by default.
	 * @since 1.3.2
	 */
	protected MOFactory getFactory() {
		return DefaultMOFactory.getInstance();
	}

	/**
	 * Register your own MIB modules in the specified context of the agent. The
	 * {@link MOFactory} provided to the <code>Modules</code> constructor is
	 * returned by {@link #getFactory()}.
	 */
	protected void registerMIBs() {

		try {
			modules = new HelloModules(getFactory());

			modules.registerMOs(server, null);
			// Some alternatives
			// Register a scalar with your OID in your enterprise subtree:
			/*
			 * MOScalar myScalar = new MOScalar(new OID(new int[] {
			 * 1,3,6,1,4,1,1,0 }), MOAccessImpl.ACCESS_READ_WRITE, new
			 * OctetString("myText"));
			 * 
			 * server.register(myScalar, null);
			 */
			// Register a table with a string index and a single integer payload
			// column
			// a row status column to
			/*
			 * DefaultMOTable myTable = new DefaultMOTable(new
			 * OID("<tableEntryOID>"), new MOTableIndex(new MOTableSubIndex[] {
			 * new MOTableSubIndex(new OID("<indexObjectClassOID>"),
			 * SMIConstants.SYNTAX_OCTET_STRING, 1, 16) }, true), new
			 * MOMutableColumn[] { new MOMutableColumn(1,
			 * SMIConstants.SYNTAX_INTEGER32, MOAccessImpl.ACCESS_READ_CREATE,
			 * new Integer32(10), true), new RowStatus(2) });
			 * server.register(myTable, null);
			 */

		} catch (DuplicateRegistrationException drex) {
			logger.error("Duplicate registration: " + drex.getMessage() + "."
					+ " MIB object registration may be incomplete!", drex);
		}
	}

	public Variable getVariable(String name) {
		OID oid;
		OctetString context = null;
		int pos = name.indexOf(':');
		if (pos >= 0) {
			context = new OctetString(name.substring(0, pos));
			oid = new OID(name.substring(pos + 1, name.length()));
		} else {
			oid = new OID(name);
		}
		final DefaultMOContextScope scope = new DefaultMOContextScope(context, oid, true, oid, true);
		MOQuery query = new DefaultMOQuery(scope, false, this);
		ManagedObject mo = server.lookup(query);
		if (mo != null) {
			final VariableBinding vb = new VariableBinding(oid);
			final RequestStatus status = new RequestStatus();
			SubRequest req = new SubRequest() {
				private boolean completed;
				private MOQuery query;

				public boolean hasError() {
					return false;
				}

				public void setErrorStatus(int errorStatus) {
					status.setErrorStatus(errorStatus);
				}

				public int getErrorStatus() {
					return status.getErrorStatus();
				}

				public RequestStatus getStatus() {
					return status;
				}

				public MOScope getScope() {
					return scope;
				}

				public VariableBinding getVariableBinding() {
					return vb;
				}

				public Request getRequest() {
					return null;
				}

				public Object getUndoValue() {
					return null;
				}

				public void setUndoValue(Object undoInformation) {
				}

				public void completed() {
					completed = true;
				}

				public boolean isComplete() {
					return completed;
				}

				public void setTargetMO(ManagedObject managedObject) {
				}

				public ManagedObject getTargetMO() {
					return null;
				}

				public int getIndex() {
					return 0;
				}

				public void setQuery(MOQuery query) {
					this.query = query;
				}

				public MOQuery getQuery() {
					return query;
				}

				public SubRequestIterator repetitions() {
					return null;
				}

				public void updateNextRepetition() {
				}

				public Object getUserObject() {
					return null;
				}

				public void setUserObject(Object userObject) {
				}

			};
			mo.get(req);
			return vb.getVariable();
		}
		return null;
	}

	class DemoTableRowListener implements MOTableRowListener {
		public void rowChanged(MOTableRowEvent event) {
			if ((event.getType() == MOTableRowEvent.CREATE) || (event.getType() == MOTableRowEvent.UPDATED)) {
				// ignore
				return;
			}
			// update counter
			Counter32 counter = (Counter32) event.getRow().getValue(Snmp4jDemoMib.idxSnmp4jDemoEntryCol3);
			if (counter == null) {
				counter = new Counter32(0);
				((MOMutableTableRow) event.getRow()).setValue(Snmp4jDemoMib.idxSnmp4jDemoEntryCol3, counter);
			}
			counter.increment();
			// update timestamp
			TimeStamp timestamp = (TimeStamp) event.getTable().getColumn(Snmp4jDemoMib.idxSnmp4jDemoEntryCol4);
			timestamp.update((MOMutableTableRow) event.getRow(), Snmp4jDemoMib.idxSnmp4jDemoEntryCol4);
			// fire notification
			Integer32 type = new Integer32(Snmp4jDemoMib.Snmp4jDemoTableRowModificationEnum.updated);
			switch (event.getType()) {
			case MOTableRowEvent.ADD:
				type.setValue(Snmp4jDemoMib.Snmp4jDemoTableRowModificationEnum.created);
				break;
			case MOTableRowEvent.DELETE:
				type.setValue(Snmp4jDemoMib.Snmp4jDemoTableRowModificationEnum.deleted);
				break;
			}
			VariableBinding[] payload = new VariableBinding[2];
			OID table = event.getTable().getOID();
			OID updateCount = new OID(table);
			updateCount.append(Snmp4jDemoMib.colSnmp4jDemoEntryCol3);
			updateCount.append(event.getRow().getIndex());

			OID modifyType = new OID(table);
			modifyType.append(Snmp4jDemoMib.colSnmp4jDemoTableRowModification);
			modifyType.append(event.getRow().getIndex());

			payload[0] = new VariableBinding(updateCount, counter);
			payload[1] = new VariableBinding(modifyType, type);
			// modules.getSnmp4jDemoMib().snmp4jDemoEvent(
			// agent.getNotificationOriginator(), new OctetString(), payload);
		}
	}

	/**
	 * Runs a sample agent with a default configuration defined by
	 * <code>SampleAgentConfig.properties</code>. A sample command line is:
	 * 
	 * <pre>
	 * -c SampleAgent.cfg -bc SampleAgent.bc udp:127.0.0.1/4700 tcp:127.0.0.1/4700
	 * </pre>
	 * 
	 * @param args
	 *            the command line arguments defining at least the listen
	 *            addresses. The format is
	 *            <code>-c[s{=SampleAgent.cfg}] -bc[s{=SampleAgent.bc}]
	 *    +ts[s] +cfg[s] #address[s<(udp|tcp):.*[/[0-9]+]?>] ..</code>. For the
	 *            format description see {@link ArgumentParser}.
	 */
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParser(
				"-c[s{=SampleAgent.cfg}] -bc[s{=SampleAgent.bc}] " + "+ts[s] +cfg[s]",
				"#address[s<(udp|tcp):.*[/[0-9]+]?>] ..");
		Map commandLineParameters = null;
		try {
			args = new String[1];
			args[0] = "udp:127.0.0.1/4700";
			commandLineParameters = parser.parse(args);
			SampleAgent sampleAgent = new SampleAgent(commandLineParameters);
			// Add all available security protocols (e.g.
			// SHA,MD5,DES,AES,3DES,..)
			SecurityProtocols.getInstance().addDefaultProtocols();
			// configure system group:
			// Set system description:
			// sampleAgent.agent.getSysDescr().setValue("My system
			// description".getBytes());
			// Set system OID (= OID of the AGENT-CAPABILITIES statement
			// describing
			// the implemented MIB objects of this agent:
			// sampleAgent.agent.getSysOID().setValue("1.3.1.6.1.4.1....");
			// Set the system services
			// sampleAgent.agent.getSysServices().setValue(72);
			sampleAgent.run();

		} catch (ParseException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
