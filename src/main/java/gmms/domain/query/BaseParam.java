package gmms.domain.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import gmms.util.DateUtil;

/**
 * Created by wangfs on 2017/7/2. helloWorld
 */
public class BaseParam {
	private String timeFrom;
	private String timeTo;
	private Date timeFromFormat;
	private Date timeToFormat;
	private String searchKey;

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public Date getTimeFromFormat() {
		if (timeFromFormat == null && StringUtils.isNotBlank(timeFrom)) {
			timeFromFormat = DateUtil.fromDateStringToDate(timeFrom);
		}
		return timeFromFormat;
	}

	public void setTimeFromFormat(Date timeFromFormat) {
		this.timeFromFormat = timeFromFormat;
	}

	public Date getTimeToFormat() {
		if (timeToFormat == null && StringUtils.isNotBlank(timeTo)) {
			timeToFormat = DateUtil.fromDateStringToDate(timeTo);
		}
		return timeToFormat;
	}

	public void setTimeToFormat(Date timeToFormat) {
		this.timeToFormat = timeToFormat;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
