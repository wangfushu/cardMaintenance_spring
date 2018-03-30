package gmms.unifiedPay.payConfig;

/**
 * Created by wangfs on 2017-10-10 helloword.
 */
public class BaseConfig {
      public  static String  appId = "201701220001";       //统一支付平台颁发给源系统的唯一标识，为应用标识字段
      public static String secret = "xmrbi3967968@2017";      //
      public static String version = "1.0";     //接口版本号(v0.1,v0.2)
      public static String osType = "web";      //操作系统类型：ios，android，wp，web，weixin
      public static String devType = "testcase";     //设备类型，即硬件类型
      public static String devId = "testcase001";       //设备Id，标识
      public static String timestamp = "";   //当前时间戳
      public static String sign = "";        //认证码
      public static String signType = "md5";    //加密类型，默认md5
}
