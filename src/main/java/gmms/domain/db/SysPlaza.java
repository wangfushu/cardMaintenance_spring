package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017-07-31.
 */

@Entity
@Table(name = "SYS_PLAZA")
@DynamicInsert
public class SysPlaza {

    @Id
    @Column(name = "PLA_NO")
    private String plaNo;


    @Column(name = "PLA_NAME")
    private String plaName;
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
    private String plaInUse;


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

    public String getPlaInUse() {
        return plaInUse;
    }

    public void setPlaInUse(String plaInUse) {
        this.plaInUse = plaInUse;
    }

    public String getPlaNo() {
        return plaNo;
    }

    public void setPlaNo(String plaNo) {
        this.plaNo = plaNo;
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
