package gmms.domain.vo;

/**
 * Created by yangjb on 2017/7/25.
 * 输出Excel
 */
public class IssueTagExcelVO {
    private String plazaName;
    private String userNo;
    private String userName;
    private String plateNo;
    private String plateColor;
    private String vKindNo;
    private String installDate;
    private String installType;
    private float fee;

    public String getPlazaName() {
        return plazaName;
    }

    public void setPlazaName(String plazaName) {
        this.plazaName = plazaName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getvKindNo() {
        return vKindNo;
    }

    public void setvKindNo(String vKindNo) {
        this.vKindNo = vKindNo;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getInstallType() {
        return installType;
    }

    public void setInstallType(String installType) {
        this.installType = installType;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
