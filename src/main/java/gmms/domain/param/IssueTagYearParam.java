package gmms.domain.param;

import javafx.scene.chart.PieChart;

/**
 * Created by wangfs on 2018-04-20 helloword.
 */
public class IssueTagYearParam extends DataTablesParam{
    private  String year;
    private Long plaNo;

    public Long getPlaNo() {
        return plaNo;
    }

    public void setPlaNo(Long plaNo) {
        this.plaNo = plaNo;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
}
