package gmms.domain.param;

/**
 * Created by wangfs on 2018-04-08 helloword.
 */
public class TmTagStoreParam extends DataTablesParam {
    private Long typeId;
    private String plaNo;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getPlaNo() {
        return plaNo;
    }

    public void setPlaNo(String plaNo) {
        this.plaNo = plaNo;
    }
}
