package gmms.domain.query;

/**
 * Created by wangfs on 2017/7/2. helloWorld
 */
public class VmVehicleQueryParam {
    private String plateNo;//车牌
    private String plateColor;//车牌颜色
    private Long vKindNo;//车型
    private Long userId;
    private String  vNewCarRegistNode;//注册节点标识 0--待支付 1--待贴卡 2--完成
    private String startPassTime;
    private String endPassTime;

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public Long getvKindNo() {
        return vKindNo;
    }

    public void setvKindNo(Long vKindNo) {
        this.vKindNo = vKindNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getvNewCarRegistNode() {
        return vNewCarRegistNode;
    }

    public void setvNewCarRegistNode(String vNewCarRegistNode) {
        this.vNewCarRegistNode = vNewCarRegistNode;
    }

    public String getStartPassTime() {
        return startPassTime;
    }

    public void setStartPassTime(String startPassTime) {
        this.startPassTime = startPassTime;
    }

    public String getEndPassTime() {
        return endPassTime;
    }

    public void setEndPassTime(String endPassTime) {
        this.endPassTime = endPassTime;
    }
}
