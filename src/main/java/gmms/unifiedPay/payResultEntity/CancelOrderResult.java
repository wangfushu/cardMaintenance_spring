package gmms.unifiedPay.payResultEntity;

/**
 * Created by wangfs on 2017-12-26 helloword.
 */
public class CancelOrderResult {
    private String extPam;//第三方支付返回信息
    private String signType;//加密类型
    private String sign;//签名

    public String getExtPam() {
        return extPam;
    }

    public void setExtPam(String extPam) {
        this.extPam = extPam;
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

    public void setSign(String sign) {
        this.sign = sign;
    }
}
