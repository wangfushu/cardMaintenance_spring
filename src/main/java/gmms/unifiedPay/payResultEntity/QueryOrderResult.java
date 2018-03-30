package gmms.unifiedPay.payResultEntity;

/**
 * Created by wangfs on 2017-10-24 helloword.
 */
public class QueryOrderResult {

    private String orderNo;//应用的订单号
    private Double totalFee;//总支付金额
    private String payType;//支付类型
    private String optType;//业务类型
    private String attach;//附加参数
    private String payOrderNo;//支付平台订单号
    private String outTradeNo;//第三方订单号
    private String appId;//应用id
    private String tradeStatus;//交易状态
    private String extPam;//扩展参数
    private String signType;//加密类型
    private String sign;//签名
    private Long payStatus;//pay状态

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

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

    public Long getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Long payStatus) {
        this.payStatus = payStatus;
    }
}
