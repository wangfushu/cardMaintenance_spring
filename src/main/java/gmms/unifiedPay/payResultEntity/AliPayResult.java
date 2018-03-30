package gmms.unifiedPay.payResultEntity;

/**
 * Created by wangfs on 2017-10-23 helloword.
 */
public class AliPayResult {
    private String orderNo;
    private String payOrderNo;
    private String isOk;
    private AliCode extPam;
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getIsOk() {
        return isOk;
    }

    public void setIsOk(String isOk) {
        this.isOk = isOk;
    }

    public AliCode getExtPam() {
        return extPam;
    }

    public void setExtPam(AliCode extPam) {
        this.extPam = extPam;
    }
}
