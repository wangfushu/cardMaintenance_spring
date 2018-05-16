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

    public static void main(String[] args) throws UnsupportedEncodingException {
      /*  MD5 getMD5 = new MD5();
        System.out.println(getMD5.GetMD5Code("123456"));
       *//* System.out.println(getMD5.GetMD5Code("201701220001&version=1.0&osType=web&devType=testcase&devId=testcase001&timestamp=1507885974610&payType=52&optType=unionpay&notifyUrl=http://www.baidu.com&title=aaa&body=0&orderNo=20171013171254615&totalFee=1&timeout=2m&attach=1&checkCode=9353ce0717659551cbe038a6c0085832xmrbi3967968@2017"));*//*
        String md5String=getMD5.GetMD5Code("201701220001&version=1.0&osType=web&devType=testcase&devId=testcase001&timestamp=1525917876503&payType=91&optType=Mobile2C&notifyUrl=http://172.16.54.205:8089/cardMaintenance/feepay/notify&title=二维卡工本费缴交(闽D12345)&orderNo=4SPAY20180510100436503&totalFee=100&timeout=2m&attach=1&checkCode=19dc37ca600f6f091573c61b77780f72xmrbi3967968@2017");
       // String md5String=MD5.GetMD5Code(sign);
        String str= new String(Base64Util.encode(md5String.getBytes())).toUpperCase();
        System.out.println(str);*/
  /*     String str= new String(Base64.encode("e10adc3949ba59abbe56e057f20f883e".getBytes()));
        System.out.println(str);*/
    }
}