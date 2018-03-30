package gmms.domain.form;

/**
 * Created by wangfs on 2017-09-04 helloword.
 */
public class YearFee {
    Integer year;
    Integer startMonth;
    Integer endMonth ;
    Double fee;
    Double delayPay;
    Double freeFee;
    Integer freeDay;
    Double newEnergyFreeDelayPay;
    Double newEnergyFreeFee;

    public YearFee(Integer year, Integer startMonth, Integer endMonth, Double fee, Double delayPay) {
        this.year = year;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.fee = fee;
        this.delayPay = delayPay;
    }

    public YearFee(Integer year, Integer startMonth, Integer endMonth, Double fee, Double delayPay, Double newEnergyFreeDelayPay, Double newEnergyFreeFee) {
        this.year = year;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.fee = fee;
        this.delayPay = delayPay;
        this.newEnergyFreeDelayPay = newEnergyFreeDelayPay;
        this.newEnergyFreeFee = newEnergyFreeFee;
    }

    public YearFee() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

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

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getDelayPay() {
        return delayPay;
    }

    public void setDelayPay(Double delayPay) {
        this.delayPay = delayPay;
    }

    public Double getFreeFee() {
        return freeFee;
    }

    public void setFreeFee(Double freeFee) {
        this.freeFee = freeFee;
    }

    public Double getNewEnergyFreeDelayPay() {
        return newEnergyFreeDelayPay;
    }

    public void setNewEnergyFreeDelayPay(Double newEnergyFreeDelayPay) {
        this.newEnergyFreeDelayPay = newEnergyFreeDelayPay;
    }

    public Double getNewEnergyFreeFee() {
        return newEnergyFreeFee;
    }

    public void setNewEnergyFreeFee(Double newEnergyFreeFee) {
        this.newEnergyFreeFee = newEnergyFreeFee;
    }


    public Integer getFreeDay() {
        return freeDay;
    }

    public void setFreeDay(Integer freeDay) {
        this.freeDay = freeDay;
    }
}
