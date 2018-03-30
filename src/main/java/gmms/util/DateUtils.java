package gmms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Title:</p>
 * <p>
 * <p>Description: </p>
 * <p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>
 * <p>Company: 厦门路桥信息股份有限公司</p>
 *
 * @author
 * @version 2.0
 */
public class DateUtils {

    /**
     * 数据库存储的时间格式串，如yyyymmdd 或yyyymmddHHMiSS
     */
    public static final int DB_STORE_DATE = 1;

    /**
     * 用连字符-分隔的时间时间格式串，如yyyy-mm-dd 或yyyy-mm-dd HH:Mi:SS
     */
    public static final int HYPHEN_DISPLAY_DATE = 2;

    /**
     * 用连字符.分隔的时间时间格式串，如yyyy.mm.dd 或yyyy.mm.dd HH:Mi:SS
     */
    public static final int DOT_DISPLAY_DATE = 3;

    /**
     * 用中文字符分隔的时间格式串，如yyyy年mm月dd 或yyyy年mm月dd HH:Mi:SS
     */
    public static final int CN_DISPLAY_DATE = 4;

    /**
     * **数据库存储时间格式串,如yyyymmddHHMissSS*
     */
    public static final int DB_LONG_DATE = 5;

    /**
     * 用连字符-分隔的时间时间格式串，如yyyy-mm-dd
     */
    public static final int YMD_DISPLAY_DATE = 6;

    /**
     * 得到精确到秒的格式化当前时间串
     *
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @return 当前时间格式化时间串
     */
    public static String getCurrTimeStr(int formatType) {
        return getTimeStr(new Date(), formatType);
    }

    public static Date getCurrDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = getTimeStr(date, 2);
        Date formatDate;
        try {
            formatDate = format.parse(strDate);
            return formatDate;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到精确到秒的格式化时间串
     *
     * @param date       指定时间
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @return 指定时间的格式化时间串
     */
    public static String getTimeStr(Date date, int formatType) {
        if (formatType < DB_STORE_DATE || formatType > YMD_DISPLAY_DATE) {
            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
        } else {
            String formatStr = null;
            switch (formatType) {
                case DB_STORE_DATE:
                    formatStr = "yyyyMMddHHmmss";
                    break;
                case HYPHEN_DISPLAY_DATE:
                    formatStr = "yyyy'-'MM'-'dd HH:mm:ss";
                    break;
                case DOT_DISPLAY_DATE:
                    formatStr = "yyyy.MM.dd HH:mm:ss";
                    break;
                case CN_DISPLAY_DATE:
                    formatStr = "yyyy'年'MM'月'dd'日' HH:mm:ss";
                    break;
                case DB_LONG_DATE:
                    formatStr = "yyyyMMddHHmmssSSS";
                    break;
                case 111:
                    formatStr = "yyyy'年'MM'月'dd'日'";
                    break;
                case YMD_DISPLAY_DATE:
                    formatStr = "yyyyMMdd";


            }

            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.format(date);
        }
    }

    /**
     * 得到精确到天的当前格式化日期串
     *
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @return
     */
    public static String getCurrDateStr(int formatType) {
        return getDateStr(new Date(), formatType);
    }

    /**
     * 得到精确到天的指定时间格式化日期串
     *
     * @param date       指定时间
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @return 指定时间格式化日期串
     */
    public static String getDateStr(Date date, int formatType) {
        if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
        } else {
            String formatStr = null;
            switch (formatType) {
                case DB_STORE_DATE:
                    formatStr = "yyyyMMdd";
                    break;
                case HYPHEN_DISPLAY_DATE:
                    formatStr = "yyyy-MM-dd";
                    break;
                case DOT_DISPLAY_DATE:
                    formatStr = "yyyy.MM.dd";
                    break;
                case CN_DISPLAY_DATE:
                    formatStr = "yyyy'年'MM'月'dd'日'";
                    break;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.format(date);
        }
    }

    /**
     * 得到精确到月的当前时间格式化年月串
     *
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @return 精确到月当前时间格式化年月串
     */
    public static String getYearMonthStr(int formatType) {
        return getYearMonthStr(new Date(), formatType);
    }

    /**
     * 得到精确到月的指定时间格式化年月串
     *
     * @param date       指定的时间
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @return 精确到月当前时间格式化年月串
     */
    public static String getYearMonthStr(Date date, int formatType) {
        if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
        } else {
            String formatStr = null;
            switch (formatType) {
                case DB_STORE_DATE:
                    formatStr = "yyyyMM";
                    break;
                case HYPHEN_DISPLAY_DATE:
                    formatStr = "yyyy-MM";
                    break;
                case DOT_DISPLAY_DATE:
                    formatStr = "yyyy.MM";
                    break;
                case CN_DISPLAY_DATE:
                    formatStr = "yyyy'年'MM'月'";
                    break;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.format(date);
        }
    }

    /**
     * 将数据库存储的日期格式串转换为各种显示的格式
     *
     * @param dateStr    最小6位，最大14位的数据库存储格式时间串如:20041212
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @return 格式化的时间串
     */
    public static String toDisplayStr(String dateStr, int formatType) {
        if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
        }
        if (dateStr == null || dateStr.length() < 6 || dateStr.length() > 14
                || formatType == DB_STORE_DATE) {
            return StringUtils.toVisualString(dateStr);
        } else {
            char[] charArr = null;
            switch (formatType) {
                case HYPHEN_DISPLAY_DATE:
                    charArr = new char[]{'-', '-', ' ', ':', ':'};
                    break;
                case DOT_DISPLAY_DATE:
                    charArr = new char[]{'.', '.', ' ', ':', ':'};
                    break;
                case CN_DISPLAY_DATE:
                    charArr = new char[]{'年', '月', ' ', ':', ':'};
                    break;
                default:
                    charArr = new char[]{'-', '-', ' ', ':', ':'};
            }
            try {
                SimpleDateFormat sdf_1 = null;
                SimpleDateFormat sdf_2 = null;
                switch (dateStr.length()) {
                    case 6:
                        sdf_1 = new SimpleDateFormat("yyyyMM");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM");
                        break;
                    case 8:
                        sdf_1 = new SimpleDateFormat("yyyyMMdd");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'"
                                + charArr[1] + "'dd");
                        break;
                    case 10:
                        sdf_1 = new SimpleDateFormat("yyyyMMddHH");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'"
                                + charArr[1] + "'dd'" + "+charArr[2]" + "'HH");
                        break;
                    case 12:
                        sdf_1 = new SimpleDateFormat("yyyyMMddHHmm");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'"
                                + charArr[1] + "'dd'" + charArr[2] + "'HH'"
                                + charArr[3] + "'mm");
                        break;
                    case 14:
                        sdf_1 = new SimpleDateFormat("yyyyMMddHHmmss");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'"
                                + charArr[1] + "'dd'" + charArr[2] + "'HH'"
                                + charArr[3] + "'mm'" + charArr[4] + "'ss");
                        break;
                    default:
                        return dateStr;
                }
                return sdf_2.format(sdf_1.parse(dateStr));
            } catch (ParseException ex) {
                return dateStr;
            }
        }
    }

    /**
     * 将显示格式的时间字符串转换为数据库存储的类型
     *
     * @param dateStr 最小4位，最大19位。显示的时间格式时间串如:2004-12-12
     * @return 数据库存储的时间字符串
     */
    public static String toStoreStr(String dateStr) {
        if (dateStr == null || dateStr.trim().equals("")) {
            return "";
        }
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < dateStr.length(); i++) {
            if (dateStr.charAt(i) >= '0' && dateStr.charAt(i) <= '9') {
                strBuf.append(dateStr.charAt(i));
            }
        }
        return strBuf.toString();
    }

    /**
     * 将生日存储的时间格式转化为年龄（周岁，小数点后不计）
     *
     * @param birthdayStr 生日字段 "yyyymmdd"
     * @return 年龄
     */
    public static String birthdayToAge(String birthdayStr) {
        if (birthdayStr == null || birthdayStr.length() < 6) {
            return "";
        } else {
            int birthYear = Integer.parseInt(birthdayStr.substring(0, 4));
            int birthMonth = Integer.parseInt(birthdayStr.substring(4, 6));
            Calendar cal = new GregorianCalendar();
            int currYear = cal.get(Calendar.YEAR);
            int currMonth = cal.get(Calendar.MONTH);
            int age = currYear - birthYear;
            age -= (currMonth < birthMonth) ? 1 : 0;
            return "" + age;
        }
    }

    /**
     * @param dateTimeStr String 格式化的时间串
     * @param formatType  数据格式类型 {@link #DB_STORE_DATE},{@link #EN_HTML_DISPLAY_DATE},{@link #CN_HTML_DISPLAY_DATE}
     * @param detal       int 增加或减少的时间
     * @param field       int 参见Calendar中关于时间字段属性的定义
     * @return String 返回的
     */
    public static String add(String dateTimeStr, int formatType, int detal,
                             int field) {
        if (dateTimeStr == null || dateTimeStr.length() < 6) {
            return dateTimeStr;
        } else {
            try {
                String formatStr = null;
                switch (formatType) {
                    case DB_STORE_DATE:
                        formatStr = "yyyyMMddHHmmss";
                        break;
                    case HYPHEN_DISPLAY_DATE:
                        formatStr = "yyyy-MM-dd HH:mm:ss";
                        break;
                    case DOT_DISPLAY_DATE:
                        formatStr = "yyyy.MM.dd HH:mm:ss";
                        break;
                    case CN_DISPLAY_DATE:
                        formatStr = "yyyy'年'MM'月' HH：mm：ss";
                        break;
                    default:
                        formatStr = "yyyyMMddHHmmss";
                        break;
                }

                formatStr = formatStr.substring(0, dateTimeStr.length());
                SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
                Date d = sdf.parse(dateTimeStr);
                GregorianCalendar g = new GregorianCalendar();
                g.setTime(d);
                g.add(field, detal);
                d = g.getTime();
                return sdf.format(d);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return dateTimeStr;
            }
        }
    }

    // /**
    // * @param date Date 时间
    // * @param detal int 增加的时间
    // * @param field int 参见Calendar中关于时间字段属性的定义
    // * @return Date
    // */
    // public static Date add(Date date, int detal, int field)
    // {
    // Calendar g = new GregorianCalendar();
    // g.setTime(date);
    // g.add(field, detal);
    // return g.getTime();
    // }

    /**
     * 日期、时间格式化
     *
     * @param date   Date 将要被格式化的日期对象
     * @param outFmt String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串，data为null时返回null，outFmt非法时返回yyyyMMdd格式
     */
    public static String getDateFormat(Date date, String outFmt) {
        if (null == date) {
            return null;
        }
        if (null == outFmt || "".equals(outFmt.trim())) { // outFmt非法
            outFmt = "yyyyMMdd";
        }

        String retu = null;
        SimpleDateFormat dateFormat = null;
        try {
            dateFormat = new SimpleDateFormat(outFmt);
        } catch (IllegalArgumentException iaex) { // outFmt非法
            dateFormat = new SimpleDateFormat("yyyyMMdd");
        }
        retu = dateFormat.format(date);
        dateFormat = null;

        return retu;
    }

    /**
     * 把日期时间对象格式化为yyyyMMdd样式
     *
     * @param date Date 将要被格式化的日期对象
     * @return String 格式化后的日期、时间字符串，如：20041001
     */
    public static String getDateFormat(Date date) {
        return getDateFormat(date, "yyyyMMdd");
    }

    /**
     * 把系统当前日期时间格式化为指定的样式
     *
     * @param outFmt String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串，如：2004年10月01日
     */
    public static String getDateFormat(String outFmt) {
        return getDateFormat(new Date(), outFmt);
    }

    /**
     * 把系统当前日期时间格式化为默认样式yyyyMMdd
     *
     * @return String 格式化后的日期、时间字符串，如：20041001
     */
    public static String getDateFormat() {
        return getDateFormat(new Date(), "yyyyMMdd");
    }

    /**
     * 日期、时间格式化
     *
     * @param millis long the number of milliseconds（毫秒） since January 1, 1970,
     *               00:00:00 GMT.
     * @param outFmt String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串
     */
    public static String getDateFormat(long millis, String outFmt) {
        Date d = new Date(millis);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        String retu = getDateFormat(calendar.getTime(), outFmt);
        calendar = null;
        return retu;
    }

    /**
     * 日期、时间格式化
     *
     * @param datestr String 存在一定格式的日期、时间字符串，如：20041001、200410011503
     * @param inFmt   String 对datestr参数格式说明，参照类说明，如：yyyyMMdd、yyyyMMddHHmm
     * @param outFmt  String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串，如：2004年10月01日、2004年10月01日 <BR>
     * 输出样式outFmt非法时，使用yyyyMMdd格式输出
     * @throws java.text.ParseException 当datestr不能格式化为inFmt格式时抛出此异常
     */
    public static String getDateFormat(String datestr, String inFmt,
                                       String outFmt) throws ParseException {
        if (null == datestr || "".equals(datestr.trim())) {
            return datestr;
        }

        if (null == inFmt || "".equals(inFmt.trim())) {
            return datestr;
        }

        if (null == outFmt || "".equals(outFmt.trim())) { // 输出样式非法
            outFmt = "yyyyMMdd";
        }

        Date inDate = getDate(datestr, inFmt);

        if (null == inDate) { // 根据inFmt分析datestr时抛出异常
            return datestr;
        }

        String retu = getDateFormat(inDate, outFmt);
        inDate = null;
        return retu;
    }

    /**
     * 把日期时间字符串，按inFmt样式转化为日期对象，然后格式化为默认样式yyyyMMdd
     *
     * @param datestr String 存在一定格式的日期、时间字符串，如：20041001、200410011503
     * @param inFmt   String 对datestr参数格式说明，参照类说明，如：yyyyMMdd、yyyyMMddHHmm
     * @return String 格式化后的日期、时间字符串，如：20041001、20041001
     * @throws java.text.ParseException 当datestr不能格式化为inFmt格式时抛出此异常
     */
    public static String getDateFormat(String datestr, String inFmt)
            throws ParseException {
        return getDateFormat(datestr, inFmt, "yyyyMMdd");
    }

    /**
     * 根据inFmt的样式，日期时间字符串转化为日期时间对象
     *
     * @param datestr String 日期时间字符串，如：20041001、2004年10月01日 15:03
     * @param inFmt   String 对datestr参数格式说明，参照类说明，如yyyyMMdd、yyyy年MM月dd日 HH:mm
     * @return Date 日期时间对象，格式inFmt非法时，使用yyyyMMdd格式
     * @throws java.text.ParseException 当datestr不能格式化为inFmt格式时抛出此异常
     */
    public static Date getDate(String datestr, String inFmt)
            throws ParseException {
        if (null == datestr || "".equals(datestr.trim())) {
            return null;
        }

        if (null == inFmt || "".equals(inFmt.trim())) { // inFmt非法
            inFmt = "yyyyMMdd";
        }

        Date inDate = null;

        // 依据inFmt格式把日期字符串转化为日期对象
        SimpleDateFormat inDateFormat = new SimpleDateFormat(inFmt);
        inDateFormat.setLenient(true);
        inDate = inDateFormat.parse(datestr);

        inDateFormat = null;
        return inDate;
    }

    /**
     * 对日期时间对象进行调整，实现如昨天是几号，去年的今天星期几等. <BR>
     * 例子：
     * <p>
     * <pre>
     *  &lt;blockquote&gt;
     *  计算去年今天星期几
     *  Date date = DateUtils.addDate(new Date(),Calendar.YEAR,-1);
     *  System.out.println(DateUtils.getDateFormat(date,&quot;E&quot;));
     *  打印60天后是什么日期，并显示为 yyyy-MM-dd 星期
     *  Date date = DateUtils.addDate(new Date(),Calendar.DATE,60);
     *  System.out.println(DateUtils.getDateFormat(date,&quot;yyyy-MM-dd E&quot;));
     *  &lt;/blockquote&gt;
     * </pre>
     *
     * @param date          Date 需要调整的日期时间对象
     * @param CALENDARFIELD int 对日期时间对象以什么单位进行调整：
     *                      <p>
     *                      <pre>
     *                       &lt;blockquote&gt;
     *                       年 Calendar.YEAR
     *                       月 Calendar.MONTH
     *                       日 Calendar.DATE
     *                       时 Calendar.HOUR
     *                       分 Calendar.MINUTE
     *                       秒 Calendar.SECOND
     *                       &lt;/blockquote&gt;
     *                      </pre>
     * @param amount        int 调整数量，>0表向后调整（明天，明年），<0表向前调整（昨天，去年）
     * @return Date 调整后的日期时间对象
     */
    public static Date addDate(Date date, int CALENDARFIELD, int amount) {
        if (null == date) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(CALENDARFIELD, amount);
        return calendar.getTime();
    }

    /**
     * 对日期时间对象进行调整.
     *
     * @param datestr       String 需要调整的日期时间字符串，它的格式为yyyyMMdd
     * @param CALENDARFIELD int 对日期时间对象以什么单位进行调整
     * @param amount        int 调整数量
     * @return Date 调整后的日期时间对象
     * @throws java.text.ParseException 当datestr不能格式化为yyyyMMdd格式时抛出此异常
     * @see #addDate(java.util.Date, int, int)
     */
    public static Date addDate(String datestr, int CALENDARFIELD, int amount)
            throws ParseException {
        return addDate(getDate(datestr, "yyyyMMdd"), CALENDARFIELD, amount);
    }

    /**
     * 根据出生日期，计算出在某一个日期的年龄
     *
     * @param birthday Date 出生日期时间对象
     * @param date2    Date 计算日期对象
     * @return int 返回date2那一天出生日期为birthday的年龄，如果birthday大于date2则返回-1
     */
    public static int getAge(Date birthday, Date date2) {
        if (null == birthday || null == date2) {
            return -1;
        }

        if (birthday.after(date2)) { // birthday大于date2
            return -1;
        }

        int ibdYear = StringUtils.getInt(getDateFormat(birthday, "yyyy"), -1);
        int idate2Year = StringUtils.getInt(getDateFormat(date2, "yyyy"), -1);

        if (ibdYear < 0 || idate2Year < 0) {
            return -1;
        }
        if (ibdYear > idate2Year) {
            return -1;
        }

        return idate2Year - ibdYear + 1;
    }

    /**
     * 根据出生日期，计算出当前的年龄
     *
     * @param birthday Date 出生日期时间对象
     * @return int 返回出生日期为birthday的年龄，如果birthday大于当前系统日期则返回-1
     */
    public static int getAge(Date birthday) {
        return getAge(birthday, new Date());
    }

    /**
     * 根据出生日期，计算出当前的年龄
     *
     * @param birthdaystr String 出生日期时间字符串，其格式一定为yyyyMMdd
     * @return int 返回出生日期为birthday的年龄，如果birthday大于当前系统日期则返回-1
     * @throws java.text.ParseException 当datestr不能格式化为yyyyMMdd格式时抛出此异常
     */
    public static int getAge(String birthdaystr) throws ParseException {
        return getAge(getDate(birthdaystr, "yyyyMMdd"));
    }

    public static void main(String[] args)

    {
        try {
            System.out.println(DateUtils
                    .getCurrTimeStr(DateUtils.DB_STORE_DATE));
            System.out.println(new Date().getTime());
            System.out.println("date:" + getDate("04-1-6", "yy-MM-dd"));
            System.out.println("    " + DateUtils.getCurrTimeStr(1));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 取得当天日期的最后时间
     *
     * @param date
     * @return
     */
    public static Date getLastTime(Date date) {
        if (date == null) return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 小于10在前面加0
     *
     * @return
     */
    public static String add0(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return num + "";
    }

    /**
     * 获得当前时间(2016-08-10 19:58:54)
     *
     * @return
     */
    public static String getCurrTime(String flag) {
        Calendar calendar = Calendar.getInstance();//使用日历类
        int year = calendar.get(Calendar.YEAR);//得到年
        int month = calendar.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
        String monthStr = String.valueOf(month);
        if (month < 10) {
            monthStr = "0" + monthStr;
        }

        int day = calendar.get(Calendar.DAY_OF_MONTH);//得到天
        String dayStr = String.valueOf(day);
        if (day < 10) {
            dayStr = "0" + dayStr;
        }
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//得到小时
        int minute = calendar.get(Calendar.MINUTE);//得到分钟
        int second = calendar.get(Calendar.SECOND);//得到秒
        String secondStr = String.valueOf(second);
        if (second < 10) {
            secondStr = "0" + secondStr;
        }
        String currTime = new String();
        if ("ymd".equals(flag)) {
            currTime = year + "-" + monthStr + "-" + dayStr;
        } else if ("ymd2".equals(flag)) {
            currTime = year + monthStr + dayStr;
        } else if ("ymdhms".equals(flag)) {
            currTime = year + "-" + monthStr + "-" + dayStr + " " + hour + ":" + minute + ":" + secondStr;
        }
        return currTime;
    }

    public static Double daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time1 - time2) / (1000 * 3600 * 24);

        return Double.parseDouble(String.valueOf(between_days));
    }

    public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }
}
