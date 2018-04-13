package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2018-04-08 helloword.
 */

@Entity
@Table(name = "TM_TAGSTORE")
@DynamicInsert
public class TmTagStore{
    @Id
    @Column(name = "PLAZANO")
    public Long plazaNo;

    @Column(name = "PLAZANAME")
    public String plazaName;

/*    @Id
    @ManyToOne(targetEntity = TmTagType.class)
    @JoinColumn(name = "TAGTYPE", updatable = true)
    public TmTagType tagType;*/

    @Column(name = "GOODTAGCOUNT")
    public int goodTagCount;


    @Column(name = "BADTAGCOUNT")
    public int badTagCount;

    @Column(name = "UPDATETIME")
    public Date updateTime;


    @Column(name = "REMARK")
    public String remark;

    public Long getPlazaNo() {
        return plazaNo;
    }

    public void setPlazaNo(Long plazaNo) {
        this.plazaNo = plazaNo;
    }

    public String getPlazaName() {
        return plazaName;
    }

    public void setPlazaName(String plazaName) {
        this.plazaName = plazaName;
    }


    public int getGoodTagCount() {
        return goodTagCount;
    }

    public void setGoodTagCount(int goodTagCount) {
        this.goodTagCount = goodTagCount;
    }

    public int getBadTagCount() {
        return badTagCount;
    }

    public void setBadTagCount(int badTagCount) {
        this.badTagCount = badTagCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }






}
