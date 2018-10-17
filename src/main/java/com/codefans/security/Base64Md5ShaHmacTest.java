package com.codefans.security;

/**
 * Created by Administrator on 2017/3/5.
 */
import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigInteger;

/**
 * 如基本的单向加密算法：
 BASE64 严格地说，属于编码格式，而非加密算法
 MD5(Message Digest algorithm 5，信息摘要算法)
 SHA(Secure Hash Algorithm，安全散列算法)
 HMAC(Hash Message Authentication Code，散列消息鉴别码)

 复杂的对称加密（DES、PBE）、非对称加密算法：
 DES(Data Encryption Standard，数据加密算法)
 PBE(Password-based encryption，基于密码验证)
 RSA(算法的名字以发明者的名字命名：Ron Rivest, AdiShamir 和Leonard Adleman)
 DH(Diffie-Hellman算法，密钥一致协议)
 DSA(Digital Signature Algorithm，数字签名)
 ECC(Elliptic Curves Cryptography，椭圆曲线密码编码学)


 本篇内容简要介绍BASE64、MD5、SHA、HMAC几种方法。
 MD5、SHA、HMAC这三种加密算法，可谓是非可逆加密，就是不可解密的加密方法。我们通常只把他们作为加密的基础。单纯的以上三种的加密并不可靠。

 BASE64的加密解密是双向的，可以求反解。
 MD5、SHA以及HMAC是单向加密，任何数据加密后只会产生唯一的一个加密串，通常用来校验数据在传输过程中是否被修改。其中HMAC算法有一个密钥，增强了数据传输过程中的安全性，强化了算法外的不可控因素。
 单向加密的用途主要是为了校验数据在传输过程中是否被修改。

 参考资料：
 http://snowolf.iteye.com/blog/379860

 */
public class Base64Md5ShaHmacTest {

        @Test
        public void test() throws Exception {
            String inputStr = "简单加密";
            System.err.println("原文:\n" + inputStr);

            byte[] inputData = inputStr.getBytes();
            String code = Base64Md5ShaHmac.encryptBASE64(inputData);

            System.err.println("BASE64加密后:\n" + code);

            byte[] output = Base64Md5ShaHmac.decryptBASE64(code);

            String outputStr = new String(output);

            System.err.println("BASE64解密后:\n" + outputStr);

            // 验证BASE64加密解密一致性
            assertEquals(inputStr, outputStr);

            // 验证MD5对于同一内容加密是否一致
            assertArrayEquals(Base64Md5ShaHmac.encryptMD5(inputData), Base64Md5ShaHmac
                    .encryptMD5(inputData));

            // 验证SHA对于同一内容加密是否一致
            assertArrayEquals(Base64Md5ShaHmac.encryptSHA(inputData), Base64Md5ShaHmac
                    .encryptSHA(inputData));

            String key = Base64Md5ShaHmac.initMacKey();
            System.err.println("Mac密钥:\n" + key);

            // 验证HMAC对于同一内容，同一密钥加密是否一致
            assertArrayEquals(Base64Md5ShaHmac.encryptHMAC(inputData, key), Base64Md5ShaHmac.encryptHMAC(
                    inputData, key));

            BigInteger md5 = new BigInteger(Base64Md5ShaHmac.encryptMD5(inputData));
            System.err.println("MD5:\n" + md5.toString(16));

            BigInteger sha = new BigInteger(Base64Md5ShaHmac.encryptSHA(inputData));
            System.err.println("SHA:\n" + sha.toString(32));

            BigInteger mac = new BigInteger(Base64Md5ShaHmac.encryptHMAC(inputData, inputStr));
            System.err.println("HMAC:\n" + mac.toString(16));

            System.out.println("=================================");





        }




}
