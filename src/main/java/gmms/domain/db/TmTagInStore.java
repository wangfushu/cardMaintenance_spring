package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2018-04-09 helloword.
 * 年卡入库表
 */
@Entity
@Table(name = "TM_TAGINSTORE")
@DynamicInsert
public class TmTagInStore {
    @Id
    @TableGenerator(name="ID_GENERATOR",
            table="sys_SeqTable",
            pkColumnName="ST_SeqName",
            pkColumnValue="TmTagInStore.inID",
            valueColumnName="ST_SeqValue",
            allocationSize=2,
            initialValue=1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    @Column(name = "INID")
    public Long inID;

    @Column(name = "INSTORETYPE")
    public Long inStoreType;

    @Column(name = "INSTORETIME")
    public Date inStoreTime;

    @Column(name = "TAGTYPE")
    public Long tagType;

    @Column(name = "COUNT")
    public Long count;


    @Column(name = "UserId")
    public Long userId;

    @Column(name = "USERNO")
    public String userNo;
    @Column(name = "USERNAME")
    public String userName;

    @Column(name = "INRECIEVEDPLAZANO")
    public String inRecievedPlazaNo;

    @Column(name = "INRECIEVEDPLAZANAME")
    public String inRecievedPlazaName;

    @Column(name = "INSENDPLAZANO")
    public String inSendPlazaNo;

    @Column(name = "INSENDPLAZANAME")
    public String inSendPlazaName;

    @Column(name = "REMARK")
    public String remark;

    public Long getInID() {
        return inID;
    }

    public void setInID(Long inID) {
        this.inID = inID;
    }

    public Long getInStoreType() {
        return inStoreType;
    }

    public void setInStoreType(Long inStoreType) {
        this.inStoreType = inStoreType;
    }

    public Date getInStoreTime() {
        return inStoreTime;
    }

    public void setInStoreTime(Date inStoreTime) {
        this.inStoreTime = inStoreTime;
    }

    public Long getTagType() {
        return tagType;
    }

    public void setTagType(Long tagType) {
        this.tagType = tagType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getInRecievedPlazaNo() {
        return inRecievedPlazaNo;
    }

    public void setInRecievedPlazaNo(String inRecievedPlazaNo) {
        this.inRecievedPlazaNo = inRecievedPlazaNo;
    }

    public String getInRecievedPlazaName() {
        return inRecievedPlazaName;
    }

    public void setInRecievedPlazaName(String inRecievedPlazaName) {
        this.inRecievedPlazaName = inRecievedPlazaName;
    }

    public String getInSendPlazaNo() {
        return inSendPlazaNo;
    }

    public void setInSendPlazaNo(String inSendPlazaNo) {
        this.inSendPlazaNo = inSendPlazaNo;
    }

    public String getInSendPlazaName() {
        return inSendPlazaName;
    }

    public void setInSendPlazaName(String inSendPlazaName) {
        this.inSendPlazaName = inSendPlazaName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
