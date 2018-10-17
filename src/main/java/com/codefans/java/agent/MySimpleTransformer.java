package main.java.com.codefans.java.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by Administrator on 2017/2/16.
 */
public class MySimpleTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        if(!className.endsWith("HelloWorld"))
            return(null);

        String line = "";
        for(int i=0; i < classfileBuffer.length;i++){
            line += Byte.toString(classfileBuffer[i]) + " ";
            if(line.length() > 60) {
                System.out.println(line);
                line = "";
            }
            if(classfileBuffer[i] == (byte)'6')
                classfileBuffer[i] = (byte)'7';
        }
        System.out.println(line);
        System.out.println("The number of bytes in HelloWorld: " + classfileBuffer.length);
        return(classfileBuffer);
    }

}
