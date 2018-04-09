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
/*@DynamicInsert*/
@SuppressWarnings("serial")
@IdClass(TmTagStorePK.class)
public class TmTagStore{
    @Id
    @Column(name = "PLAZANO")
    public String plazaNo;

    @Column(name = "PLAZANAME")
    public String plazaName;

    @Id
    @ManyToOne(targetEntity = TmTagType.class)
    @JoinColumn(name = "TAGTYPE", updatable = true)
    public TmTagType tagType;

    @Column(name = "GOODTAGCOUNT")
    public Long goodTagCount;


    @Column(name = "BADTAGCOUNT")
    public Long badTagCount;

    @Column(name = "UPDATETIME")
    public Date updateTime;


    @Column(name = "REMARK")
    public String remark;

    public String getPlazaNo() {
        return plazaNo;
    }

    public void setPlazaNo(String plazaNo) {
        this.plazaNo = plazaNo;
    }

    public String getPlazaName() {
        return plazaName;
    }

    public void setPlazaName(String plazaName) {
        this.plazaName = plazaName;
    }



    public Long getGoodTagCount() {
        return goodTagCount;
    }

    public void setGoodTagCount(Long goodTagCount) {
        this.goodTagCount = goodTagCount;
    }

    public Long getBadTagCount() {
        return badTagCount;
    }

    public void setBadTagCount(Long badTagCount) {
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

    public TmTagType getTagType() {
        return tagType;
    }

    public void setTagType(TmTagType tagType) {
        this.tagType = tagType;
    }
}
