package gmms.domain.param;

import gmms.util.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created by yangjb on 2017/7/13.
 * helloWorld
 */
public class DataTablesParam {
    private Integer draw;
    private Integer start;
    private Integer length;
    private String timefrom;
    private String timeto;
    private Date timetoFormat;
    private Date timefromFormat;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(String timefrom) {
        this.timefrom = timefrom;
    }

    public String getTimeto() {
        return timeto;
    }

    public void setTimeto(String timeto) {
        this.timeto = timeto;
    }

    public Date getTimetoFormat() {
        if (StringUtils.isNotBlank(this.timeto) && this.timetoFormat == null) {
            this.timetoFormat = DateUtil.parseDate(this.timeto, "yyyy-MM-dd");
        }
        return timetoFormat;
    }

    public void setTimetoFormat(Date timetoFormat) {
        this.timetoFormat = timetoFormat;
    }

    public Date getTimefromFormat() {
        if (StringUtils.isNotBlank(this.timefrom) && this.timefromFormat == null) {
            this.timefromFormat = DateUtil.parseDate(this.timefrom, "yyyy-MM-dd");
        }
        return timefromFormat;
    }

    public void setTimefromFormat(Date timefromFormat) {
        this.timefromFormat = timefromFormat;
    }
}
