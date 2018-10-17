package com.codefans.java.io.eea;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-3-18 Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
public class MessageMetaInfo extends Persistent implements MetaInfo {

	public MessageMetaInfo() {
	}

	public boolean isCompressed() {
		return true;
	}

	public void setCompressed(boolean compressed) {
	}

	public synchronized void writeSelf(OutputStream out) throws IOException {
		writeInt(out, MagicNo);
		String sender = "sender@ms.com";
		writeAddress(out, sender);
		String[] recipients = new String[] { "recipient@ms.com", "recipient02@ms.com" };
		writeInt(out, recipients.length);
		for (int i = 0; i < recipients.length; i++) {
			writeAddress(out, recipients[i]);
		}

		// envelope addresses
		String envelopeSender = "sender@ms.com";
		writeAddress(out, envelopeSender);
		String[] envelopeRecipients = new String[] { "recipient@ms.com", "recipient02@ms.com" };
		writeInt(out, envelopeRecipients.length);
		for (int i = 0; i < envelopeRecipients.length; i++) {
			writeAddress(out, envelopeRecipients[i]);
		}

		writeString(out, "Subject");
		long messageSize = 123;
		writeLong(out, messageSize);
		long receivedDate = new Date().getTime();
		writeLong(out, receivedDate);
		int flags = 12;
		writeInt(out, flags);
		int priority = 13;
		writeInt(out, priority);
		int isAttachment = 0;
		writeInt(out, isAttachment);
		String[] attributeNames = new String[] { "id", "name", "password" };
		String[] attributeValues = new String[] { "1", "zhangsan", "123" };
		writeInt(out, attributeNames.length);
		for (int i = 0; i < attributeNames.length; i++) {
			writeString(out, attributeNames[i]);
			writeString(out, attributeValues[i]);
		}
		//
		int attach_count = 2;
		writeInt(out, attach_count);
		for (int i = 0; i < attach_count; i++) {
			int sequence = 123;
			writeInt(out, sequence);
			long storeId = 1234;
			writeLong(out, storeId);
			String typeFlag = "typeFlag";
			writeString(out, typeFlag);
			int ifExists = 1;
			writeInt(out, ifExists);
			String repositoryId = "repositoryId";
			writeString(out, repositoryId);
			String repositoryId_pri = "repositoryId_pri";
			writeString(out, repositoryId_pri);
			String folderId = "20140318";
			writeString(out, folderId);
			String hashcode = "fdjfdfdjfldjfadjf54d5gddfdf";
			writeString(out, hashcode);
			String header = "headerheader";
			writeString(out, header);
			String encoding = "encoding";
			writeString(out, encoding);
		}
	}

	public synchronized Object readSelf(InputStream in) throws IOException {
		int magicNo = readInt(in);
		if (magicNo != MagicNo) {
			throw new IOException("invalid message info");
		}
		this.print("magicNo", magicNo);

		String sender = readAddress(in);
		this.print("sender", sender);

		int reciSize = readInt(in);
		this.print("reciSize", reciSize);
		String[] recipients = new String[reciSize];
		for (int i = 0; i < recipients.length; i++) {
			recipients[i] = readAddress(in);
			this.print("recipients", recipients[i]);
		}

		// envelope addresses
		String envelopeSender = readAddress(in);
		this.print("envelopeSender", envelopeSender);

		int envelReciSize = readInt(in);
		this.print("envelReciSize", envelReciSize);
		String[] envelopeRecipients = new String[envelReciSize];
		for (int i = 0; i < envelopeRecipients.length; i++) {
			envelopeRecipients[i] = readAddress(in);
			this.print("envelopeRecipients", envelopeRecipients[i]);
		}

		String subject = readString(in);
		this.print("subject", subject);
		long size = readLong(in);
		this.print("size", size);
		long receivedDate = readLong(in);
		this.print("receivedDate", receivedDate);
		int flags = readInt(in);
		this.print("flags", flags);
		int priority = readInt(in);
		this.print("priority", priority);
		int attachment = readInt(in);
		this.print("attachment", attachment);

		int sz = readInt(in);
		this.print("sz", sz);
		for (int i = 0; i < sz; i++) {
			String key = readString(in);
			this.print("key", key);
			String value = readString(in);
			this.print("value", value);
		}
		int attachC = readInt(in);
		this.print("attachC", attachC);
		for (int i = 0; i < attachC; i++) {
			int sequence = readInt(in);
			this.print("sequence", sequence);
			long storeId = readLong(in);
			this.print("storeId", storeId);
			String typeflag = readString(in);
			this.print("typeflag", typeflag);
			int ifExist = readInt(in);
			this.print("ifExist", ifExist);
			String repositoryId = readString(in);
			this.print("repositoryId", repositoryId);
			String repositoryId_pri = readString(in);
			this.print("repositoryId_pri", repositoryId_pri);
			String folderId = readString(in);
			this.print("folderId", folderId);
			String hashcode = readString(in);
			this.print("hashcode", hashcode);
			String header = readString(in);
			this.print("header", header);
			String encoding = readString(in);
			this.print("encoding", encoding);
		}
		return null;
	}

	private String readAddress(InputStream in) throws IOException {
		String address = readString(in);
		return address;
	}

	private void writeAddress(OutputStream out, String address) throws IOException {
		if (address != null) {
			writeString(out, address);
		} else {
			writeString(out, null);
		}
	}

	public void print(String name, Object value) {
		System.out.println(name + ":[" + value + "]");
	}

}
