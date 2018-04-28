package gmms.domain.vo;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by yangjb on 2017/7/25.
 * 输出Excel
 */
public class TagOutStoreExcelVO {
    public String outStoreTime;
    public Long tagType;
    public int count;
    public String userName;
    public Long outRecievedPlazaNo;
    public String outRecievedPlazaName;
    public Long outSendPlazaNo;
    public String outSendPlazaName;
    public String remark;


    public String getOutStoreTime() {
        return outStoreTime;
    }

    public void setOutStoreTime(String outStoreTime) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOutRecievedPlazaNo() {
        return outRecievedPlazaNo;
    }

    public void setOutRecievedPlazaNo(Long outRecievedPlazaNo) {
        this.outRecievedPlazaNo = outRecievedPlazaNo;
    }

    public String getOutRecievedPlazaName() {
        return outRecievedPlazaName;
    }

    public void setOutRecievedPlazaName(String outRecievedPlazaName) {
        this.outRecievedPlazaName = outRecievedPlazaName;
    }

    public Long getOutSendPlazaNo() {
        return outSendPlazaNo;
    }

    public void setOutSendPlazaNo(Long outSendPlazaNo) {
        this.outSendPlazaNo = outSendPlazaNo;
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
}
