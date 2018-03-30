package gmms.domain.form;

/**
 * Created by wangfs on 2017-09-04 helloword.
 */
public class StartAndEndMonthForm {
    Integer startMonth;
    Integer endMonth;
    Boolean isNeedFee;



    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Boolean getIsNeedFee() {
        return isNeedFee;
    }

    public void setIsNeedFee(Boolean isNeedFee) {
        this.isNeedFee = isNeedFee;
    }
}
