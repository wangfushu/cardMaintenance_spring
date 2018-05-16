package gmms.domain.query;

/**
 * Created by wangfs on 2018-04-25 helloword.
 */
public class IssueTagYearQueryForm {
    private String plazaName;//网点名称
    private String statisticDate;//统计日期
    private String issueUserNo;//发卡账号
    private String issueUserName;//发卡人姓名
    private int  newCardIssueCount;//发新卡数量
    private int  insureCardIssueCount;//保内换卡数量
    private int  outsureCardIssueCount;//保外换卡数量
    private int  insureDamageCardIssueCount;//人为损坏换卡数量
    private int count;//总数量
    private double totalfee; //实收总金额；

    public int getInsureDamageCardIssueCount() {
        return insureDamageCardIssueCount;
    }

    public void setInsureDamageCardIssueCount(int insureDamageCardIssueCount) {
        this.insureDamageCardIssueCount = insureDamageCardIssueCount;
    }

    public String getPlazaName() {
        return plazaName;
    }

    public void setPlazaName(String plazaName) {
        this.plazaName = plazaName;
    }

    public String getStatisticDate() {
        return statisticDate;
    }

    public void setStatisticDate(String statisticDate) {
        this.statisticDate = statisticDate;
    }

    public String getIssueUserNo() {
        return issueUserNo;
    }

    public void setIssueUserNo(String issueUserNo) {
        this.issueUserNo = issueUserNo;
    }

    public String getIssueUserName() {
        return issueUserName;
    }

    public void setIssueUserName(String issueUserName) {
        this.issueUserName = issueUserName;
    }

    public int getNewCardIssueCount() {
        return newCardIssueCount;
    }

    public void setNewCardIssueCount(int newCardIssueCount) {
        this.newCardIssueCount = newCardIssueCount;
    }

    public int getInsureCardIssueCount() {
        return insureCardIssueCount;
    }

    public void setInsureCardIssueCount(int insureCardIssueCount) {
        this.insureCardIssueCount = insureCardIssueCount;
    }

    public int getOutsureCardIssueCount() {
        return outsureCardIssueCount;
    }

    public void setOutsureCardIssueCount(int outsureCardIssueCount) {
        this.outsureCardIssueCount = outsureCardIssueCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(double totalfee) {
        this.totalfee = totalfee;
    }
}
