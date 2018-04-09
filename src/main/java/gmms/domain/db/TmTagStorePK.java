package gmms.domain.db;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by wangfs on 2018-03-06 helloword.
 */
public class TmTagStorePK implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PLAZANO")
    public String plazaNo;//网点号

    @Id
    @ManyToOne(targetEntity = TmTagType.class)
    @JoinColumn(name = "TAGTYPE", updatable = true)
    public TmTagType tagType;

    public TmTagStorePK(String plazaNo, TmTagType tagType) {
        this.plazaNo = plazaNo;
        this.tagType = tagType;
    }

    public String getPlazaNo() {
        return plazaNo;
    }

    public void setPlazaNo(String plazaNo) {
        this.plazaNo = plazaNo;
    }

    public TmTagType getTagType() {
        return tagType;
    }

    public void setTagType(TmTagType tagType) {
        this.tagType = tagType;
    }

    public TmTagStorePK() {
    }

}
