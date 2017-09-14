package javaaes;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class javaaes {
    public static void main(String[] args) throws Exception{
        byte[] a = AesEncrypt("f51-*f4ad".getBytes(),"d82f*a+df");
        System.out.println(new String(Base64.getEncoder().encodeToString(a)));
    }
    //AES加密
    public static byte[] AesEncrypt(byte[] byteContent, String password) throws Exception {
        
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);

        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();//AES加密实际的Key值        
        //如果直接enCodeFormat=password.getBytes(),那.NET直接就可以解密

        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(byteContent);
    }
    //AES解密
    public static byte[] AesDecrypt(byte[] byteContent, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);
        
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(byteContent);
    }

}