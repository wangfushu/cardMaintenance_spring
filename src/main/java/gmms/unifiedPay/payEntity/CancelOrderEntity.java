package gmms.unifiedPay.payEntity;

/**
 * Created by wangfs on 2017-12-26 helloword.
 */
public class CancelOrderEntity {
    private String orderNo;//应用订单号 否
    private String payOrderNo;//支付平台订单号 否
    private String cancelReason;//撤销原因 否
    private String checkCode;//计费校验 是

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

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
