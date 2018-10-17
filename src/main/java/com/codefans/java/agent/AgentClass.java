package main.java.com.codefans.java.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by Administrator on 2017/2/16.
 */
public class AgentClass {

    public static void premain(String agentArgs,Instrumentation inst) {
        inst.addTransformer(new MySimpleTransformer());
    }

}
