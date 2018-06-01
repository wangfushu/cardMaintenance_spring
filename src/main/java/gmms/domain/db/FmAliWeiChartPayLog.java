package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2017-10-24 helloword.
 */

@Entity
@Table(name = "ALIWEICHARTPAYLOG")
@DynamicInsert
public class FmAliWeiChartPayLog {


    /*************** 支付参数信息  ****************/
    @Id
    @TableGenerator(name="ID_GENERATOR",
            table="sys_SeqTable",
            pkColumnName="ST_SeqName",
            pkColumnValue="FmAliWeiChartPayLog.FID",
            valueColumnName="ST_SeqValue",
            allocationSize=1,
            initialValue=1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    @Column(name = "FID")
    private Long  fID;//自增Id

    @Column(name = "SUBMITTYPE")
    private Long submitType;

    @Column(name = "ORDERNO")
    private String orderNo;

    @Column(name = "PAYORDERNO")
    private String payOrderNo;

    @Column(name = "FEE")
    private Double fee;

    @Column(name = "PAYSTATUS")
    private Long payStatus;//交易状态

    @Column(name = "PAYTIME")
    private Date paytime;

    @Column(name = "PAYEXTPAM")
    private String payExtPam;//交易返回参数


    @Column(name = "FEEID")
    private String feeID;//fm_fee.F_Id

    @Column(name = "VEHICLENO")
    private String vehicleNo;//表fm_fee.F_VehicleNo

    @Column(name = "PLATENO")
    private String plateno;//表fm_fee.F_Plateno

    @Column(name = "USERID")
    private Long userID;//操作员ID

    @Column(name = "USERNAME")
    private String userName;//操作员

    /*************** 撤销或退款信息  ****************/
    @Column(name = "CANCELTIME")
    private Date cancelTime;//撤销或退款 时间


    @Column(name = "CANCELNOTE")
    private String cancelNote;//撤销或退款 说明

    @Column(name = "CANCELEXTPAM")
    private String cancelExtPam;//撤销或退款 返回的参数


    /*************** 最后更新信息  ****************/

    @Column(name = "UPDATETIME")
    private Date updatetime;//最后一次的查询状态时间


    @Column(name = "UPDATEEXTPAM")
    private String updateExtPam;//最后一次查询返回的参数

    @Column(name = "UPDATESIGN")
    private Long updateSign;//0-临时存单 ，1-已提交支付 2- 交易结束


    @Column(name = "NOTIFYTIME")
    private Date notifytime;//回调时间

    @Column(name = "NOTIFYEXTPAM")
    private String notifyExtPam;//回调返回参数

/*    @Column(name = "AUTOSHOPNO")
    private String autoshopNo;//回调返回参数*/

    @Column(name = "INPUTTIME")
    private Date inputtime;//订单生成时间

    @Column(name = "ENDSIGN")
    private int endSign;//完成标识   0-流程未结束  2-该流程已经完全结束

    @Column(name = "INSTALLTYPE")
    private int installType;//贴卡类型


    public Long getfID() {
        return fID;
    }

    public void setfID(Long fID) {
        this.fID = fID;
    }

    public Long getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Long submitType) {
        this.submitType = submitType;
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

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Long getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Long payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPayExtPam() {
        return payExtPam;
    }

    public void setPayExtPam(String payExtPam) {
        this.payExtPam = payExtPam;
    }

    public String getFeeID() {
        return feeID;
    }

    public void setFeeID(String feeID) {
        this.feeID = feeID;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    public String getCancelExtPam() {
        return cancelExtPam;
    }

    public void setCancelExtPam(String cancelExtPam) {
        this.cancelExtPam = cancelExtPam;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateExtPam() {
        return updateExtPam;
    }

    public void setUpdateExtPam(String updateExtPam) {
        this.updateExtPam = updateExtPam;
    }

    public Long getUpdateSign() {
        return updateSign;
    }

    public void setUpdateSign(Long updateSign) {
        this.updateSign = updateSign;
    }

    public Date getNotifytime() {
        return notifytime;
    }

    public void setNotifytime(Date notifytime) {
        this.notifytime = notifytime;
    }

    public String getNotifyExtPam() {
        return notifyExtPam;
    }

    public void setNotifyExtPam(String notifyExtPam) {
        this.notifyExtPam = notifyExtPam;
    }


    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public int getEndSign() {
        return endSign;
    }

    public void setEndSign(int endSign) {
        this.endSign = endSign;
    }

    public int getInstallType() {
        return installType;
    }

    public void setInstallType(int installType) {
        this.installType = installType;
    }
}
