package gmms.domain.db;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
            allocationSize=1,
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
    public int count;


    @Column(name = "USERID")
    public Long userId;

    @Column(name = "USERNO")
    public String userNo;
    @Column(name = "USERNAME")
    public String userName;

    @Column(name = "INRECIEVEDPLAZANO")
    public Long inRecievedPlazaNo;

    @Column(name = "INRECIEVEDPLAZANAME")
    public String inRecievedPlazaName;

    @Column(name = "INSENDPLAZANO")
    public Long inSendPlazaNo;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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


    public String getInRecievedPlazaName() {
        return inRecievedPlazaName;
    }

    public void setInRecievedPlazaName(String inRecievedPlazaName) {
        this.inRecievedPlazaName = inRecievedPlazaName;
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

    public Long getInRecievedPlazaNo() {
        return inRecievedPlazaNo;
    }

    public void setInRecievedPlazaNo(Long inRecievedPlazaNo) {
        this.inRecievedPlazaNo = inRecievedPlazaNo;
    }

    public Long getInSendPlazaNo() {
        return inSendPlazaNo;
    }

    public void setInSendPlazaNo(Long inSendPlazaNo) {
        this.inSendPlazaNo = inSendPlazaNo;
    }

    @Transient
    public boolean getIsNewCardInStore() {

            if (this.getInStoreType().equals(1L)) {
                return true;
            }

        return false;
    }
}
