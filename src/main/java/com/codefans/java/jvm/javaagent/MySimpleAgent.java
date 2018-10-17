package com.codefans.java.jvm.javaagent;

/**
 * Created by Administrator on 2017/2/17.
 */
import java.lang.instrument.Instrumentation;

public class MySimpleAgent {
    public static void premain(String agentArgs,Instrumentation inst) {
        inst.addTransformer(new MySimpleTransformer());
    }
}
