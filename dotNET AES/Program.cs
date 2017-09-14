using java.security;
using javax.crypto;
using System.Security.Cryptography;
using System.Text;
using System;

public class Program
{
    public static void Main()
    {
        byte[] a = Convert.FromBase64String("nEQzFy+vafrYNHlZauNwUw==");
        string result = DeAES(a, "d82f*a+df");
        Console.WriteLine(result);
        Console.Read();
    }
    /// <summary>
    /// AES解密
    /// </summary>
    /// <param name="data"></param>
    /// <param name="key"></param>
    /// <returns></returns>
    public static string DeAES(byte[] content, string key)
    {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(Encoding.ASCII.GetBytes(key));
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();

        using (AesCryptoServiceProvider aesProvider = new AesCryptoServiceProvider())
        {
            aesProvider.Key = enCodeFormat;
            aesProvider.Mode = CipherMode.ECB;
            aesProvider.Padding = PaddingMode.PKCS7;
            using (ICryptoTransform cryptoTransform = aesProvider.CreateDecryptor())
            {
                byte[] inputBuffers = content;
                byte[] results = cryptoTransform.TransformFinalBlock(inputBuffers, 0, inputBuffers.Length);
                aesProvider.Clear();
                return Encoding.UTF8.GetString(results);
            }
        }
    }

}