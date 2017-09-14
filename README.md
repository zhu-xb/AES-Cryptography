# AES-Cryptography
JAVA AES加解密和.NET AES加解密对比
我们在接收JAVA发送的AES加密字符串后，在.NET没有对应的KeyGenerator 和 SecureRandom去生成AES 的 KEY值，导致无法直接解密。
利用IKVM类库，在.NET中对JAVA的AES加密字符串进行解密处理。
