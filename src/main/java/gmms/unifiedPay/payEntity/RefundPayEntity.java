package gmms.unifiedPay.payEntity;

/**
 * Created by wangfs on 2017-12-26 helloword.
 */
public class RefundPayEntity {
    private String orderNo;//应用订单号
    private String payOrderNo;//支付平台订单号
    private String refundOrderNo;//应用退款订单号(不需要)
    private String refundReason;//退款原因
    private String refundFee;//退款金额，单位是（分） 是
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

    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
