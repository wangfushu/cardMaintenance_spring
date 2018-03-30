package gmms.util;

import org.springframework.security.crypto.codec.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
 * MD5 算法
*/
public class MD5 {

    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public MD5() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) throws UnsupportedEncodingException {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    public static void main(String[] args) {
        MD5 getMD5 = new MD5();
       /* System.out.println(getMD5.GetMD5Code("201701220001&version=1.0&osType=web&devType=testcase&devId=testcase001&timestamp=1507885974610&payType=52&optType=unionpay&notifyUrl=http://www.baidu.com&title=aaa&body=0&orderNo=20171013171254615&totalFee=1&timeout=2m&attach=1&checkCode=9353ce0717659551cbe038a6c0085832xmrbi3967968@2017"));*/
       // System.out.println(getMD5.GetMD5Code("201701220001&version=1.0&osType=web&devType=testcase&devId=testcase001&timestamp=1511944861078&payType=52&optType=unionpay&title=年费缴交&orderNo=4SPAY20171129164101078&totalFee=1&timeout=2m&attach=1&checkCode=b1911cef793df6b64b44d4ee9d04c901xmrbi3967968@2017"));
       String str= new String(Base64.encode("f5c78fc39df55f93936a826fb5e55ffb".getBytes()));
        System.out.println(str);
    }
}