package com.codefans.java.serial;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Administrator on 2017/3/11.
 */
public class ExternalizableObject implements Externalizable {

    private String name;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getPassword());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        byte[] bytes = new byte[1024];
//        int byteCount = in.read(bytes);
//        setName(new String(bytes, 0, bytes.length, "UTF-8"));
//        byteCount = in.read(bytes);
//        setPassword(new String(bytes, 0, bytes.length, "UTF-8"));

        setName((String)in.readObject());
        setPassword((String)in.readObject());


    }
}
