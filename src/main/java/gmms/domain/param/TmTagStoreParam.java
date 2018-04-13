package gmms.domain.param;

/**
 * Created by wangfs on 2018-04-08 helloword.
 */
public class TmTagStoreParam extends DataTablesParam {
    private Long typeId;
    private Long plaNo;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getPlaNo() {
        return plaNo;
    }

    public void setPlaNo(Long plaNo) {
        this.plaNo = plaNo;
    }
}
