package gmms.domain.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yangjb on 2017/7/25.
 * 输出Excel
 */
public class TagInStoreExcelVO {
    public String inStoreType;
    public String inStoreTime;
    public int count;
    public String userName;
    public String inRecievedPlazaName;
    public String inSendPlazaName;
    public String remark;


    public String getInStoreType() {
        return inStoreType;
    }

    public void setInStoreType(String inStoreType) {
        this.inStoreType = inStoreType;
    }


    public String getInStoreTime() {
        return inStoreTime;
    }

    public void setInStoreTime(String inStoreTime) {
        this.inStoreTime = inStoreTime;
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
}
