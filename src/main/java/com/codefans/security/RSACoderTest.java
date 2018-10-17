package com.codefans.security;

import static org.junit.Assert.*;

import com.codefans.security.RSACoder;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/5.
 */
public class RSACoderTest {

    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSACoder.initKey();

        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
                privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

    }

    //@Test
    public void testPrivateSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

        byte[] decodedData = RSACoder
                .decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSACoder.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);

        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.err.println("状态:\r" + status);
        assertTrue(status);

    }

    @Test
    public void testPublicSign() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPrivateKey(data, publicKey);

        byte[] decodedData = RSACoder
                .decryptByPublicKey(encodedData, privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

        System.err.println("公钥签名——私钥验证签名");
        // 产生签名
        String sign = RSACoder.sign(encodedData, publicKey);
        System.err.println("签名:\r" + sign);

        // 验证签名
        boolean status = RSACoder.verify(encodedData, privateKey, sign);
        System.err.println("状态:\r" + status);
        assertTrue(status);

    }


}

