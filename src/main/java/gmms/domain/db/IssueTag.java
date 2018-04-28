package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2018-04-19 helloword.
 */

@Entity
@Table(name = "ISSUETAG")
@DynamicInsert
@DynamicUpdate
public class IssueTag {

    @Id
    @TableGenerator(name="ID_GENERATOR",
            table="sys_SeqTable",
            pkColumnName="ST_SeqName",
            pkColumnValue="IssueTag.id",
            valueColumnName="ST_SeqValue",
            allocationSize=1,
            initialValue=1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    @Column(name = "id")
    private Long id;


    @Column(name = "INSTALLDATE")
    private Date installDate;//发卡时间

    @Column(name = "TAGTYPE")
    private Long tagType;//卡类型

    @Column(name = "TAGNO")
    private String tagNo;//卡号

    @Column(name = "OUTTAGNO")
    private String outTagNo;//外码

    @Column(name = "epc")
    private String epc;//epc码

    @Column(name = "tid")
    private String tid;//tid码

    @Column(name = "VEHICLENO")
    private String vehicleNo;//车辆编号生成规则：5位网点号  + 8位日期(YYYYMMDD) + 7位流水号;

    @Column(name = "PLATENO")
    private String plateNo;//车牌

    @Column(name = "PLATECOLOR")
    private String plateColor;//车牌颜色

    @Column(name = "VKINDNO")
    private Long vKindNo;//车型

    @Column(name = "USERNO")
    private String userNo;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "USERID")
    private Long userId;

    @Column(name = "PLAZANO")
    private Long plazaNo;//网点

    @Column(name = "INSTALLTYPE")
    private Long installType;//贴卡类型 1-新卡 2-保内换卡  3-保外换卡

    @Column(name = "FEE")
    private float fee;//缴费金额

    @Column(name = "PLAZANAME")
    private String plazaName;//网点名称

    public String getPlazaName() {
        return plazaName;
    }

    public void setPlazaName(String plazaName) {
        this.plazaName = plazaName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Long getTagType() {
        return tagType;
    }

    public void setTagType(Long tagType) {
        this.tagType = tagType;
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

    public Long getInstallType() {
        return installType;
    }

    public void setInstallType(Long installType) {
        this.installType = installType;
    }

    public Long getPlazaNo() {
        return plazaNo;
    }

    public void setPlazaNo(Long plazaNo) {
        this.plazaNo = plazaNo;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
