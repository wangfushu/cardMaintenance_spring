package gmms.domain.param;

import java.util.Date;

/**
 * Created by wangfs on 2018-04-19 helloword.
 */
public class IssueForm {
    private Date installDate;//发卡时间
    private String tagNo;//卡号
    private String outTagNo;//外码
    private String epc;//epc码
    private String tid;//tid码
    private String vehicleNo;//车辆编号生成规则：5位网点号  + 8位日期(YYYYMMDD) + 7位流水号;
    private String plateNo;//车牌
    private String plateColor;//车牌颜色
    private Long vKindNo;//车型
    private String userNo;
    private String userName;
    private Long userId;
    private Long plazaNo;//网点
    private String plazaName;//网点名称

    public String getPlazaName() {
        return plazaName;
    }

    public void setPlazaName(String plazaName) {
        this.plazaName = plazaName;
    }

    public Long getPlazaNo() {
        return plazaNo;
    }

    public void setPlazaNo(Long plazaNo) {
        this.plazaNo = plazaNo;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public String getTagNo() {
        return tagNo;
    }

    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }

    public String getOutTagNo() {
        return outTagNo;
    }

    public void setOutTagNo(String outTagNo) {
        this.outTagNo = outTagNo;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public Long getvKindNo() {
        return vKindNo;
    }

    public void setvKindNo(Long vKindNo) {
        this.vKindNo = vKindNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
