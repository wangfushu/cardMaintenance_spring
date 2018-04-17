package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2017-10-19 helloword.
 */

@Entity
@Table(name = "FEE")
@DynamicInsert
public class FmFee {

/*    @SequenceGenerator(name = "SYS_SEQTABLE")
    private String fID;*/

    @Id
    @Column(name = "F_ID")
    private String fId;//车辆编号生成规则：5位网点号  + 8位日期(YYYYMMDD) + 7位流水号;

    @Column(name = "F_VEHICLENO")
    private String fVehicleNo;

    @Column(name = "F_PLATENO")
    private String fPlateNo;

    @Column(name = "F_PLATECOLOR")
    private String fPlateColor;

    @Column(name = "F_VEHICLEKIND")
    private Long fVehicleKind;

    @Column(name = "F_CALCFEE")
    private Float fCalcFee;

    @Column(name = "F_SUBMITFEE")
    private Float fSubmitFee;

    @Column(name = "F_SUBMITTYPE")
    private String fSubmitType;//缴交方式 52-APP银联支付 52-APP银联支付53-APP支付宝54-APP微信

    @Column(name = "F_FEEREASON")
    private Long fFeeReason; //缴费理由

    @Column(name = "F_FEETIME")
    private Date fFeeTime;


    @Column(name = "F_CUSTNAME")
    private String custName;//用户名

    @Column(name = "F_INCNAME")
    private String incName;//用户单位

    @Column(name = "F_HANDLENAME")
    private String handleName;//办理人姓名
    @Column(name = "F_PHONE")
    private String phone;//用户手机


    @Column(name = "F_PLAZANO")
    private String fPlazaNo;//网点编号


    @Column(name = "F_MODIFYTIME")
    private Date fModifyTime;//操作时间

    @Column(name = "F_USERID")
    private Long fUserId;//操作人编号

    @Column(name = "F_USERNO")
    private String fUserNo;//工号

    @Column(name = "F_USERNAME")
    private String fUserName;//姓名

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfVehicleNo() {
        return fVehicleNo;
    }

    public void setfVehicleNo(String fVehicleNo) {
        this.fVehicleNo = fVehicleNo;
    }

    public String getfPlateNo() {
        return fPlateNo;
    }

    public void setfPlateNo(String fPlateNo) {
        this.fPlateNo = fPlateNo;
    }

    public String getfPlateColor() {
        return fPlateColor;
    }

    public void setfPlateColor(String fPlateColor) {
        this.fPlateColor = fPlateColor;
    }

    public Long getfVehicleKind() {
        return fVehicleKind;
    }

    public void setfVehicleKind(Long fVehicleKind) {
        this.fVehicleKind = fVehicleKind;
    }

    public Float getfCalcFee() {
        return fCalcFee;
    }

    public void setfCalcFee(Float fCalcFee) {
        this.fCalcFee = fCalcFee;
    }

    public Float getfSubmitFee() {
        return fSubmitFee;
    }

    public void setfSubmitFee(Float fSubmitFee) {
        this.fSubmitFee = fSubmitFee;
    }

    public String getfSubmitType() {
        return fSubmitType;
    }

    public void setfSubmitType(String fSubmitType) {
        this.fSubmitType = fSubmitType;
    }

    public Long getfFeeReason() {
        return fFeeReason;
    }

    public void setfFeeReason(Long fFeeReason) {
        this.fFeeReason = fFeeReason;
    }

    public Date getfFeeTime() {
        return fFeeTime;
    }

    public void setfFeeTime(Date fFeeTime) {
        this.fFeeTime = fFeeTime;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getIncName() {
        return incName;
    }

    public void setIncName(String incName) {
        this.incName = incName;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getfPlazaNo() {
        return fPlazaNo;
    }

    public void setfPlazaNo(String fPlazaNo) {
        this.fPlazaNo = fPlazaNo;
    }

    public Date getfModifyTime() {
        return fModifyTime;
    }

    public void setfModifyTime(Date fModifyTime) {
        this.fModifyTime = fModifyTime;
    }

    public Long getfUserId() {
        return fUserId;
    }

    public void setfUserId(Long fUserId) {
        this.fUserId = fUserId;
    }

    public String getfUserNo() {
        return fUserNo;
    }

    public void setfUserNo(String fUserNo) {
        this.fUserNo = fUserNo;
    }

    public String getfUserName() {
        return fUserName;
    }

    public void setfUserName(String fUserName) {
        this.fUserName = fUserName;
    }
}
