package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * Created by wangfs on 2018-02-23 helloword.
 * 年卡类型表
 */
@Entity
@Table(name = "TM_TAGTYPE")
@DynamicInsert
public class TmTagType {

    @Id
    @TableGenerator(name="ID_GENERATOR",
           table="sys_SeqTable",
            pkColumnName="ST_SeqName",
            pkColumnValue="TmTagType.typeId",
            valueColumnName="ST_SeqValue",
            allocationSize=2,
            initialValue=1
            )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    @Column(name = "TT_TYPEID")
    private Long typeId;//标签ID

    @Column(name = "TT_TAGTYPE")
    private String tagType ;//标签类型名称


    @Column(name = "TT_COMMUNICATERATE")
    private String communicateRate ;//通讯速率

    @Column(name = "TT_FACTORY")
    private String factory ;//厂家

    @Column(name = "TT_INUSE")
    private Long inUse ;//是否在用

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getCommunicateRate() {
        return communicateRate;
    }

    public void setCommunicateRate(String communicateRate) {
        this.communicateRate = communicateRate;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Long getInUse() {
        return inUse;
    }

    public void setInUse(Long inUse) {
        this.inUse = inUse;
    }


}
