package gmms.controller;



import com.google.common.collect.Lists;
import gmms.domain.db.*;
import gmms.domain.param.IssueTagYearParam;
import gmms.domain.param.TagInStoreParam;
import gmms.domain.param.TagOutStoreParam;
import gmms.domain.param.TmTagStoreParam;
import gmms.domain.query.IssueTagYearQueryForm;
import gmms.service.BaseInformationService;
import gmms.service.FmFeeService;
import gmms.service.TagService;
import gmms.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangfs on 2017-11-09 helloword.
 *
 * app端数据统计
 *
 */

@RequestMapping("/statistic")
@Controller
public class StatisticController extends BaseControl {
    private Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);
    @Autowired
    private TagService tagService;
    @Autowired
    private BaseInformationService baseInformationService;

    int year;
    int month;
    int day;

    /**
     * 入库报表页面跳转
     * @param tmTagStoreParam
     * @param model
     * @return
     */
    @RequestMapping(value = "/taginstore-report")
    public String taginstorelist(TmTagStoreParam tmTagStoreParam, Model model) {
        Users currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        Calendar calendar = Calendar.getInstance();// 使用日历类
        String newMonth = new String();
        String newDay = new String();
        if (StringUtils.isNullBlank(tmTagStoreParam.getTimefrom())) {
            year = calendar.get(Calendar.YEAR);// 得到年

            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);
            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            tmTagStoreParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            tmTagStoreParam.setTimeto(year + "-" + newMonth + "-" + newDay);
        } else {
            try {
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(tmTagStoreParam.getTimefrom()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            year = calendar.get(Calendar.YEAR);// 得到年
            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);

            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            tmTagStoreParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            if(StringUtils.isNullBlank(tmTagStoreParam.getTimeto())) {
                try {
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(tmTagStoreParam.getTimeto()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                year = calendar.get(Calendar.YEAR);// 得到年
                month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
                newMonth = DateUtils.add0(month);

                day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
                newDay = DateUtils.add0(day);
                tmTagStoreParam.setTimeto(year + "-" + newMonth + "-" + newDay);
            }
            //
        }
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", tmTagStoreParam);

        return "cardmaintenanceadmin/statisticalreport/taginstore-report";
    }

    /**
     * 出库报表页面跳转
     * @param tmTagStoreParam
     * @param model
     * @return
     */
    @RequestMapping(value = "/tagoutstore-report")
    public String tagoutstorelist(TmTagStoreParam tmTagStoreParam, Model model) {
        Users currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        Calendar calendar = Calendar.getInstance();// 使用日历类
        String newMonth = new String();
        String newDay = new String();
        if (StringUtils.isNullBlank(tmTagStoreParam.getTimefrom())) {
            year = calendar.get(Calendar.YEAR);// 得到年

            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);
            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            tmTagStoreParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            tmTagStoreParam.setTimeto(year + "-" + newMonth + "-" + newDay);
        } else {
            try {
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(tmTagStoreParam.getTimefrom()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            year = calendar.get(Calendar.YEAR);// 得到年
            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);

            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            tmTagStoreParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            if(StringUtils.isNullBlank(tmTagStoreParam.getTimeto())) {
                try {
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(tmTagStoreParam.getTimeto()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                year = calendar.get(Calendar.YEAR);// 得到年
                month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
                newMonth = DateUtils.add0(month);

                day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
                newDay = DateUtils.add0(day);
                tmTagStoreParam.setTimeto(year + "-" + newMonth + "-" + newDay);
            }
            //
        }
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", tmTagStoreParam);
        return "cardmaintenanceadmin/statisticalreport/tagoutstore-report";
    }

    /**
     * 发卡日报表跳转
     * @param issueTagYearParam
     * @param model
     * @return
     */
    @RequestMapping(value = "/issuetag-day-report")
    public String issuetagdaylist(IssueTagYearParam issueTagYearParam, Model model) {
        Users currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        Calendar calendar = Calendar.getInstance();// 使用日历类
        String newMonth = new String();
        String newDay = new String();
        if (StringUtils.isNullBlank(issueTagYearParam.getTimefrom())) {
            year = calendar.get(Calendar.YEAR);// 得到年

            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);
            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            issueTagYearParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            issueTagYearParam.setTimeto(year + "-" + newMonth + "-" + newDay);
        } else {
            try {
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(issueTagYearParam.getTimefrom()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            year = calendar.get(Calendar.YEAR);// 得到年
            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);

            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            issueTagYearParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            if(StringUtils.isNullBlank(issueTagYearParam.getTimeto())) {
                try {
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(issueTagYearParam.getTimeto()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                year = calendar.get(Calendar.YEAR);// 得到年
                month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
                newMonth = DateUtils.add0(month);

                day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
                newDay = DateUtils.add0(day);
                issueTagYearParam.setTimeto(year + "-" + newMonth + "-" + newDay);
            }
            //
        }
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", issueTagYearParam);
        return "cardmaintenanceadmin/statisticalreport/issuetag-day-report";
    }

    /**
     * 发卡信息年报表跳转
     * @param issueTagYearParam
     * @param model
     * @return
     */
    @RequestMapping(value = "/issuetag-year-report")
    public String issuetagyearlist(IssueTagYearParam issueTagYearParam, Model model) {
        Users currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        Calendar calendar = Calendar.getInstance();// 使用日历类
        String newMonth = new String();
        String newDay = new String();
        if (StringUtils.isNullBlank(issueTagYearParam.getYear())) {
            year = calendar.get(Calendar.YEAR);// 得到年

            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);
            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);

        } else {
            try {
                calendar.setTime(new SimpleDateFormat("yyyy").parse(issueTagYearParam.getYear()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            year = calendar.get(Calendar.YEAR);// 得到年
            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);

            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
        }
        issueTagYearParam.setYear(String.valueOf(year));
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", issueTagYearParam);
        return "cardmaintenanceadmin/statisticalreport/issuetag-year-report";
    }

    /**
     * 发卡信息详细信息报表跳转
     * @param issueTagYearParam
     * @param model
     * @return
     */
    @RequestMapping(value = "/issuetag-detail-report")
    public String issuetagdetaillist(IssueTagYearParam issueTagYearParam, Model model) {
        Users currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        Calendar calendar = Calendar.getInstance();// 使用日历类
        String newMonth = new String();
        String newDay = new String();
        if (StringUtils.isNullBlank(issueTagYearParam.getTimefrom())) {
            year = calendar.get(Calendar.YEAR);// 得到年

            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);
            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            issueTagYearParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            issueTagYearParam.setTimeto(year + "-" + newMonth + "-" + newDay);
        } else {
            try {
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(issueTagYearParam.getTimefrom()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            year = calendar.get(Calendar.YEAR);// 得到年
            month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
            newMonth = DateUtils.add0(month);

            day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
            newDay = DateUtils.add0(day);
            issueTagYearParam.setTimefrom(year + "-" + newMonth + "-" + newDay);
            if(StringUtils.isNullBlank(issueTagYearParam.getTimeto())) {
                try {
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(issueTagYearParam.getTimeto()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                year = calendar.get(Calendar.YEAR);// 得到年
                month = calendar.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
                newMonth = DateUtils.add0(month);

                day = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
                newDay = DateUtils.add0(day);
                issueTagYearParam.setTimeto(year + "-" + newMonth + "-" + newDay);
            }
            //
        }
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", issueTagYearParam);
        return "cardmaintenanceadmin/statisticalreport/issuetag-detail-report";
    }

    /**
     * 入库分页查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tagInStore" , method = RequestMethod.GET)
    public String tagInStoreStatistic(TagInStoreParam param){
        int pageNo = param.getStart() / param.getLength() + 1;
        Pagination<TmTagInStore> pagination = new Pagination<TmTagInStore>();
        Page<TmTagInStore> orderDBList = tagService.TmTagInStorelistAllPage(param, pageNo, param.getLength());
        pagination.setData(orderDBList.getContent());
        pagination.setDraw(param.getDraw());
        pagination.setRecordsFiltered((int) orderDBList.getTotalElements());
        pagination.setRecordsTotal((int) orderDBList.getTotalElements());
        if(orderDBList.getContent().size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
    }

    /**
     * 入库报表Excel导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tagInStoreexport" ,method = RequestMethod.GET)
    public ResponseEntity tagInStoreexport(TagInStoreParam param) {
        Users currentUser = getCurrentUser();
        List<TmTagInStore> tmTagInStoreList = tagService.TmTagInStorelistAll(param);

        String[] Title = { "入库类型 ", "入库时间", "数量 ", "操作人姓名", "接收方网点", "发送方网点", "备注" };
        String fileName=param.getTimefrom()+"到"+param.getTimeto()+"二维卡入库记录报表.xls";
        byte[] tagInStoreExcel = tagService.getTmTagInStoreExcel(tmTagInStoreList, fileName, Title);
        //byte[] BmBillToComputerExcel = fmFeeService.getBmBillToComputerExcel(bmBillToComputers,fileName,Title);
        if (tagInStoreExcel == null) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        try {
            return MyFileUtil.downloadFile(tagInStoreExcel, fileName, request);
        } catch (IOException e) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
    }


    /**
     * 出库分页数据
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tagOutStore" , method = RequestMethod.GET)
    public String tagOutStoreStatistic(TagOutStoreParam param){
        int pageNo = param.getStart() / param.getLength() + 1;
        Pagination<TmTagOutStore> pagination = new Pagination<TmTagOutStore>();
        Page<TmTagOutStore> tmTagOutStorePage = tagService.TmTagOutStorelistAllPage(param, pageNo, param.getLength());
        pagination.setData(tmTagOutStorePage.getContent());
        pagination.setDraw(param.getDraw());
        pagination.setRecordsFiltered((int) tmTagOutStorePage.getTotalElements());
        pagination.setRecordsTotal((int) tmTagOutStorePage.getTotalElements());
        if(tmTagOutStorePage.getContent().size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
       // return JsonMapper.nonEmptyMapper().toJson(pagination);
    }

    /**
     * 出库数据Excel导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tagOutStoreexport" ,method = RequestMethod.GET)
    public ResponseEntity tagOutStoreexport(TagOutStoreParam param) {
        Users currentUser = getCurrentUser();
        List<TmTagOutStore> tmTagOutStoreList = tagService.TmTagOutStorelistAll(param);

        String[] Title = {"接收方网点" ,"发送方网点", "出库时间", "数量 ", "操作人姓名",  "备注" };
        String fileName=param.getTimefrom()+"到"+param.getTimeto()+"二维卡出库库记录报表.xls";

        byte[] tagInStoreExcel = tagService.getTmTagOutStoreExcel(tmTagOutStoreList, fileName, Title);
        if (tagInStoreExcel == null) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        try {
            return MyFileUtil.downloadFile(tagInStoreExcel, fileName, request);
        } catch (IOException e) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * 发卡年报表分页
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issueTagYear" , method = RequestMethod.GET)
    public String issueTagYearStatistic(IssueTagYearParam param){
        int pageNo = param.getStart() / param.getLength() + 1;
        Pagination<IssueTagYearQueryForm> pagination = new Pagination<IssueTagYearQueryForm>();
        List<IssueTagYearQueryForm> objectList = tagService.IssueTagYearlistAllPage(param,getCurrentUser(),pageNo,param.getLength());

        pagination.setData(objectList);
        pagination.setDraw(param.getDraw());
        pagination.setRecordsFiltered(tagService.IssueTagYearlistSize(param,getCurrentUser()));
        pagination.setRecordsTotal(tagService.IssueTagYearlistSize(param,getCurrentUser()));
        if(objectList.size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
    }

    /**
     * 发卡年报表Excel导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issueTagYearexport" ,method = RequestMethod.GET)
    public ResponseEntity issueTagYearexport(IssueTagYearParam param) {
        Users currentUser = getCurrentUser();
        List<IssueTagYearQueryForm> objectList = tagService.IssueTagYearlistAllList(param, getCurrentUser());

        String[] Title = {"网点名称" ,"发卡账号", "发卡姓名","统计日期" ,"新发卡数量","保内换卡数量","保外换卡数量","人为损坏换卡数量","总发卡数量 ", "实收金额"};
        String fileName=param.getYear()+"年-发卡信息年报表.xls";

        byte[] tagInStoreExcel = tagService.getIssueTagYearExcel(objectList, fileName, Title);
        if (tagInStoreExcel == null) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        try {
            return MyFileUtil.downloadFile(tagInStoreExcel, fileName, request);
        } catch (IOException e) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * 发卡日报表分页
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issueTagDay" , method = RequestMethod.GET)
    public String issueTagDay(IssueTagYearParam param){
        int pageNo = param.getStart() / param.getLength() + 1;
        Pagination<IssueTagYearQueryForm> pagination = new Pagination<IssueTagYearQueryForm>();
        List<IssueTagYearQueryForm> objectList = tagService.IssueTagDaylistAllPage(param, getCurrentUser(), pageNo, param.getLength());
        pagination.setData(objectList);
        pagination.setDraw(param.getDraw());
        pagination.setRecordsFiltered(tagService.IssueTagDaylistSize(param, getCurrentUser()));
        pagination.setRecordsTotal(tagService.IssueTagDaylistSize(param,getCurrentUser()));
        if(objectList.size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
    }

    /**
     * 发卡日报表Excel导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issueTagDayexport" ,method = RequestMethod.GET)
    public ResponseEntity issueTagDayexport(IssueTagYearParam param) {
        Users currentUser = getCurrentUser();
        List<IssueTagYearQueryForm> objectList = tagService.IssueTagDaylistAllList(param, getCurrentUser());

        String[] Title = {"网点名称" ,"发卡账号", "发卡姓名","统计日期" ,"新发卡数量","保内换卡数量","保外换卡数量","人为损坏换卡数量","总发卡数量 ", "实收金额"};
        String fileName = param.getTimefrom()+"到"+param.getTimeto()+"年-发卡信息年报表.xls";

        byte[] tagInStoreExcel = tagService.getIssueTagDayExcel(objectList, fileName, Title);
        if (tagInStoreExcel == null) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        try {
            return MyFileUtil.downloadFile(tagInStoreExcel, fileName, request);
        } catch (IOException e) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
    }


    /**
     * 发卡详细信息报表分页
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issueTagDetail" , method = RequestMethod.GET)
    public String issueTagDetail(IssueTagYearParam param){
        int pageNo = param.getStart() / param.getLength() + 1;
        Pagination<IssueTag> pagination = new Pagination<IssueTag>();
        Page<IssueTag> objectList = tagService.IssueTaglistAllPage(param, getCurrentUser(), pageNo, param.getLength());
        pagination.setData(objectList.getContent());
        pagination.setDraw(param.getDraw());
        pagination.setRecordsFiltered((int) objectList.getTotalElements());
        pagination.setRecordsTotal((int) objectList.getTotalElements());
        if(objectList.getContent().size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
    }

    /**
     * 发卡详细信息Excel导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issueTagDetailexport" ,method = RequestMethod.GET)
    public ResponseEntity issueTagDetailexport(IssueTagYearParam param) {
        Users currentUser = getCurrentUser();
        List<IssueTag> objectList = tagService.IssueTaglistAll(param, getCurrentUser());
        String[] Title = {"网点名称" ,"发卡账号", "发卡姓名","车牌号" ,"车牌颜色","车型","发卡时间","发卡类型 ", "金额(元)"};
        String fileName = param.getTimefrom()+"到"+param.getTimeto()+"年-发卡信息详细报表.xls";

        byte[] tagInStoreExcel = tagService.getIssueTagExcel(objectList, fileName, Title);
        if (tagInStoreExcel == null) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        try {
            return MyFileUtil.downloadFile(tagInStoreExcel, fileName, request);
        } catch (IOException e) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
    }
}
