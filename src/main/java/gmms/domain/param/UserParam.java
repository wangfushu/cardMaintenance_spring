package gmms.domain.param;

/**
 * Created by yangjb on 2017/7/29.
 * helloWorld
 */
public class UserParam extends DataTablesParam {
    private Long roleId;
    private int timeType;
    private Long plaNo;

    public Long getPlaNo() {
        return plaNo;
    }

    public void setPlaNo(Long plaNo) {
        this.plaNo = plaNo;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }
}
