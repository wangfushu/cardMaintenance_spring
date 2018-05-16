package gmms.domain.query;

/**
 * Created by wangfs on 2017-10-25 helloword.
 */
public class OrderQueryTurnForm {
    private String payOrderNo;
    private String payType;
    private Double totalFee;
    private String plateNo;

    public OrderQueryTurnForm(String payOrderNo, String payType, Double totalFee) {
        this.payOrderNo = payOrderNo;
        this.payType = payType;
        this.totalFee = totalFee;
    }


    public OrderQueryTurnForm(String payOrderNo, String payType, Double totalFee, String plateNo) {
        this.payOrderNo = payOrderNo;
        this.payType = payType;
        this.totalFee = totalFee;
        this.plateNo = plateNo;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

}
