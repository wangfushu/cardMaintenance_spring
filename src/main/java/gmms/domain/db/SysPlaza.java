package gmms.domain.db;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-07-31.
 */

@Entity
@Table(name = "SYS_PLAZA")
@DynamicInsert
public class SysPlaza {

    @Id
    @TableGenerator(name="ID_GENERATOR",
            table="sys_SeqTable",
            pkColumnName="ST_SeqName",
            pkColumnValue="SysPlaza.plaNo",
            valueColumnName="ST_SeqValue",
            allocationSize=1,
            initialValue=1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    @Column(name = "PLA_NO")
    private Long plaNo;


    @Column(name = "PLA_NAME")
    private String plaName;


    @Column(name = "PLA_LINKMAN")
    private String plaLinkMan;

    @Column(name = "PLA_PHONE")
    private String plaPhone;

    @Column(name = "PLA_FAX")
    private String plaFax;


    @Column(name = "PLA_ADDRESS")
    private String plaAddress;

    @Column(name = "PLA_REMARK")
    private String plaRemark;
    @Column(name = "PLA_ZIPCODE")
    private String plaZipCode;
    @Column(name = "PLA_USERID")
    private String plaUserId;
    @Column(name = "PLA_USERNO")
    private String plaUserNo;
    @Column(name = "PLA_USERNAME")
    private String plaUserName;
    @Column(name = "PLA_MODIFYTIME")
    private Date plaModifyTime;
    @Column(name = "PLA_INUSE")
    private int plaInUse;


    public String getPlaUserId() {
        return plaUserId;
    }

    public void setPlaUserId(String plaUserId) {
        this.plaUserId = plaUserId;
    }

    public String getPlaUserNo() {
        return plaUserNo;
    }

    public void setPlaUserNo(String plaUserNo) {
        this.plaUserNo = plaUserNo;
    }

    public String getPlaUserName() {
        return plaUserName;
    }

    public void setPlaUserName(String plaUserName) {
        this.plaUserName = plaUserName;
    }

    public Date getPlaModifyTime() {
        return plaModifyTime;
    }

    public void setPlaModifyTime(Date plaModifyTime) {
        this.plaModifyTime = plaModifyTime;
    }


    public int getPlaInUse() {
        return plaInUse;
    }

    public void setPlaInUse(int plaInUse) {
        this.plaInUse = plaInUse;
    }

    public String getPlaName() {
        return plaName;
    }

    public void setPlaName(String plaName) {
        this.plaName = plaName;
    }

    public String getPlaRemark() {
        return plaRemark;
    }

    public void setPlaRemark(String plaRemark) {
        this.plaRemark = plaRemark;
    }

    public String getPlaZipCode() {
        return plaZipCode;
    }

    public void setPlaZipCode(String plaZipCode) {
        this.plaZipCode = plaZipCode;
    }


    public Long getPlaNo() {
        return plaNo;
    }

    public void setPlaNo(Long plaNo) {
        this.plaNo = plaNo;
    }

    public String getPlaLinkMan() {
        return plaLinkMan;
    }

    public void setPlaLinkMan(String plaLinkMan) {
        this.plaLinkMan = plaLinkMan;
    }

    public String getPlaPhone() {
        return plaPhone;
    }

    public void setPlaPhone(String plaPhone) {
        this.plaPhone = plaPhone;
    }

    public String getPlaFax() {
        return plaFax;
    }

    public void setPlaFax(String plaFax) {
        this.plaFax = plaFax;
    }

    public String getPlaAddress() {
        return plaAddress;
    }

    public void setPlaAddress(String plaAddress) {
        this.plaAddress = plaAddress;
    }

    //是否是所有网点
    @Transient
    public boolean isAllPlazaNo() {
          if (plaNo.equals(0l)) {
                return true;
          }
        return false;
    }

    @Override
    public String toString() {
        return "SysPlaza{" +
                "plaNo=" + plaNo +
                ", plaName='" + plaName + '\'' +
                ", plaLinkMan='" + plaLinkMan + '\'' +
                ", plaPhone='" + plaPhone + '\'' +
                ", plaFax='" + plaFax + '\'' +
                ", plaAddress='" + plaAddress + '\'' +
                ", plaRemark='" + plaRemark + '\'' +
                ", plaZipCode='" + plaZipCode + '\'' +
                ", plaUserId='" + plaUserId + '\'' +
                ", plaUserNo='" + plaUserNo + '\'' +
                ", plaUserName='" + plaUserName + '\'' +
                ", plaModifyTime=" + plaModifyTime +
                ", plaInUse=" + plaInUse +
                '}';
    }

/*   private String Pla_DataDirectory;
    private String Pla_BackupDirectory;
    private String Pla_ServerNo;
    private String Pla_ServerIp;
    private String Pla_DataBaseName;
    private String Pla_DataBaseUser;
    private String Pla_DataBasePassword;
    private String Pla_Transactor;
    private String Pla_Phone;
    private String Pla_Address;
    private String Pla_Fax;
    private String Pla_UserNo;
    private String Pla_UserName;
    private Date Pla_ModifyTime;
    private Long Pla_InUse;
    private Long Pla_MonitorId;
    private Long Pla_BillManagerId;
    private Long Pla_TagManagerId;
    private Long Pla_SystemManagerId;
    private Long Pla_UserId;
    private Long Pla_IsTollgate;*/



}
