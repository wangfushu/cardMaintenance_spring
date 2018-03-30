package gmms.unifiedPay.payEntity;

import gmms.util.Base64Util;
import gmms.util.MD5;

import java.io.UnsupportedEncodingException;

/**
 * Created by wangfs on 2017-10-12 helloword.
 */
public class BaseDataPay {
    private String version;//接口版本号(v0.1,v0.2) 是
    private String osType;//操作系统类型：ios，android，wp，web，weixin 是
    private String appId;//统一支付平台颁发给源系统的唯一标识，为应用标识字段 是
    private String devType;//设备类型，即硬件类型 否
    private String devId;//设备Id，标识 否
    private String timestamp;//当前时间戳  是
    private String signType;//加密类型，默认md5 是
    private String sign;//认证码=BASE64(md5(appId&key1=value1&key2=value2...secret))secret为平台颁发给BAS的密钥 是

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) throws UnsupportedEncodingException {
        String md5String=MD5.GetMD5Code(sign);
        String str= new String(Base64Util.encode(md5String.getBytes())).toUpperCase();
        //MD5 md5 = new MD5();
        //this.sign = Base64Util.encode(MD5.GetMD5Code(sign).getBytes()).toUpperCase();
        this.sign = str;
    }
}
