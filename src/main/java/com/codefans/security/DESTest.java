package com.codefans.security;

import static org.junit.Assert.*;

import com.codefans.security.DES;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/5.
 */
public class DESTest {

    @Test
    public void test() throws Exception {
        String inputStr = "DES";
        String key = DES.initKey();
        System.err.println("原文:\t" + inputStr);

        System.err.println("密钥:\t" + key);

        byte[] inputData = inputStr.getBytes();
        inputData = DES.encrypt(inputData, key);

        System.err.println("加密后:\t" + DES.encryptBASE64(inputData));

        byte[] outputData = DES.decrypt(inputData, key);
        String outputStr = new String(outputData);

        System.err.println("解密后:\t" + outputStr);

        assertEquals(inputStr, outputStr);
    }









}

