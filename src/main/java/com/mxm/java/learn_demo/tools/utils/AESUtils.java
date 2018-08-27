package com.mxm.java.learn_demo.tools.utils;

import com.mxm.java.learn_demo.tools.enums.ErrorCode;
import com.mxm.java.learn_demo.tools.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author maxianming
 * @date 2018/6/12 16:57
 */
@Slf4j
public class AESUtils {

    private static final String UTF8 = "UTF-8";

    private static final String AES_CBC = "AES/CBC/PKCS5Padding";

    //private static
    private AESUtils(){

    }


    /**
     * 加密
     * @author maxianming
     * @param
     * @return
     * @date 2018/6/12 17:23
     */
    public static String encrypt(String source, String aesKey, String aesIv) {
        try {

            byte[] raw = aesKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(aesIv.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(source.getBytes(UTF8));
            return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
        } catch (Exception e) {
            log.error("AES加密异常，source:{}", source, e);
            throw new SystemException(ErrorCode.AES_ENCRYPT_ERROR);
        }

    }

    /**
     * 解密
     * @author maxianming
     * @param
     * @return
     * @date 2018/6/12 17:24
     */
    public static String decrypt(String secretStr, String aesKey, String aesIv) {
        try {
            byte[] raw = aesKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(aesIv.getBytes());

            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(secretStr);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, UTF8);
            return originalString;
        } catch (Exception e) {
            log.error("AES解密异常，source:{}", secretStr, e);
            throw new SystemException(ErrorCode.AES_DECRYPT_ERROR);
        }
    }
}
