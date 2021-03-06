package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2018-04-09 helloword.
 * 年卡入库表
 */
@Entity
@Table(name = "TM_TAGOUTSTORE")
@DynamicInsert
public class TmTagOutStore {
    @Id
    @TableGenerator(name="ID_GENERATOR",
            table="sys_SeqTable",
            pkColumnName="ST_SeqName",
            pkColumnValue="TmTagOutStore.outID",
            valueColumnName="ST_SeqValue",
            allocationSize=1,
            initialValue=1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    @Column(name = "OUTID")
    public Long outID;

    @Column(name = "OUTSTORETYPE")
    public Long outStoreType;

    @Column(name = "OUTSTORETIME")
    public Date outStoreTime;

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

    @Column(name = "OUTRECIEVEDPLAZANO")
    public Long outRecievedPlazaNo;

    @Column(name = "OUTRECIEVEDPLAZANAME")
    public String outRecievedPlazaName;

    @Column(name = "OUTSENDPLAZANO")
    public Long outSendPlazaNo;

    @Column(name = "OUTSENDPLAZANAME")
    public String outSendPlazaName;

    @Column(name = "REMARK")
    public String remark;

    public Long getOutID() {
        return outID;
    }

    public void setOutID(Long outID) {
        this.outID = outID;
    }

    public Long getOutStoreType() {
        return outStoreType;
    }

    public void setOutStoreType(Long outStoreType) {
        this.outStoreType = outStoreType;
    }

    public Date getOutStoreTime() {
        return outStoreTime;
    }

    public void setOutStoreTime(Date outStoreTime) {
        this.outStoreTime = outStoreTime;
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


    public String getOutRecievedPlazaName() {
        return outRecievedPlazaName;
    }

    public void setOutRecievedPlazaName(String outRecievedPlazaName) {
        this.outRecievedPlazaName = outRecievedPlazaName;
    }

    public String getOutSendPlazaName() {
        return outSendPlazaName;
    }

    public void setOutSendPlazaName(String outSendPlazaName) {
        this.outSendPlazaName = outSendPlazaName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOutRecievedPlazaNo() {
        return outRecievedPlazaNo;
    }

    public void setOutRecievedPlazaNo(Long outRecievedPlazaNo) {
        this.outRecievedPlazaNo = outRecievedPlazaNo;
    }

    public Long getOutSendPlazaNo() {
        return outSendPlazaNo;
    }

    public void setOutSendPlazaNo(Long outSendPlazaNo) {
        this.outSendPlazaNo = outSendPlazaNo;
    }
}
