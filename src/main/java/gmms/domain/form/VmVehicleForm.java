package gmms.domain.form;

import java.util.Date;

/**
 * Created by wangfs on 2017-10-19 helloword.
 */
public class VmVehicleForm {
    private String vehicleNo;//车辆编号生成规则：4位网点号 + 3位计算机编号（旧的是计算机编号） + 8位日期(YYYYMMDD) + 5位流水号;
    private String plateNo;//车牌
    private String plateColor;//车牌颜色
    private Long vKindNo;//车型
    private Float spec;//载量
    private Date vRegDate;//注册日期

    private String custName;//用户名
    private String handleName;//办理人姓名
    private String iDCard;//身份证ID
    private String incName;//用户单位
    private String fax;//传真
    private String phone;//用户手机
    private String address;//地址
    private String zip;//邮编
    private String email;//邮箱
    private String aMemo;//备注


    private Long newCarRegistNode;//注册节点
    private String pictureDirectory;//图片路径
    private String imageDirectory;//完整图片路径


    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getImageDirectory() {
        return imageDirectory;
    }

    public void setImageDirectory(String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }

    public String getPictureDirectory() {
        return pictureDirectory;
    }

    public void setPictureDirectory(String pictureDirectory) {
        this.pictureDirectory = pictureDirectory;
    }

    public Long getNewCarRegistNode() {
        return newCarRegistNode;
    }

    public void setNewCarRegistNode(Long newCarRegistNode) {
        this.newCarRegistNode = newCarRegistNode;
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

    public Float getSpec() {
        return spec;
    }

    public void setSpec(Float spec) {
        this.spec = spec;
    }

    public Date getvRegDate() {
        return vRegDate;
    }

    public void setvRegDate(Date vRegDate) {
        this.vRegDate = vRegDate;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getiDCard() {
        return iDCard;
    }

    public void setiDCard(String iDCard) {
        this.iDCard = iDCard;
    }

    public String getIncName() {
        return incName;
    }

    public void setIncName(String incName) {
        this.incName = incName;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getaMemo() {
        return aMemo;
    }

    public void setaMemo(String aMemo) {
        this.aMemo = aMemo;
    }

    public Long getvKindNo() {
        return vKindNo;
    }

    public void setvKindNo(Long vKindNo) {
        this.vKindNo = vKindNo;
    }
}
