package gmms.domain.db;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2017-09-05 helloword.
 */

@Entity
@Table(name = "SYS_BASEINFORMATION")
@SuppressWarnings("serial")
@IdClass(SysBaseInformationPK.class)
public class SysBaseInformation {

    @Id
    @Column(name = "BI_TYPE")
    private String biType;

    @Id
    @Column(name = "BI_VALUE")
    private String biValue;


    @Column(name = "BI_DESCRIPTION")
    private String biDescription;

    @Column(name = "BI_MODIFYTIME")
    private Date biModifyTime;

    @Column(name = "BI_USERNO")
    private String biUserNo;

    @Column(name = "BI_USERNAME")
    private String biUserName;

    @Column(name = "BI_USERID")
    private String biUserId;

    @Column(name = "AMEMO")
    private String aMemo;//备注
    @Column(name = "SORT")
    private Long sort;

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getBiType() {
        return biType;
    }

    public void setBiType(String biType) {
        this.biType = biType;
    }

    public String getBiValue() {
        return biValue;
    }

    public void setBiValue(String biValue) {
        this.biValue = biValue;
    }

    public String getBiDescription() {
        return biDescription;
    }

    public void setBiDescription(String biDescription) {
        this.biDescription = biDescription;
    }

    public Date getBiModifyTime() {
        return biModifyTime;
    }

    public void setBiModifyTime(Date biModifyTime) {
        this.biModifyTime = biModifyTime;
    }

    public String getBiUserNo() {
        return biUserNo;
    }

    public void setBiUserNo(String biUserNo) {
        this.biUserNo = biUserNo;
    }

    public String getBiUserName() {
        return biUserName;
    }

    public void setBiUserName(String biUserName) {
        this.biUserName = biUserName;
    }

    public String getBiUserId() {
        return biUserId;
    }

    public void setBiUserId(String biUserId) {
        this.biUserId = biUserId;
    }

    public String getaMemo() {
        return aMemo;
    }

    public void setaMemo(String aMemo) {
        this.aMemo = aMemo;
    }
}
