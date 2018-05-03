package gmms.service;

import com.google.common.collect.Lists;
import gmms.dao.*;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.*;
import gmms.domain.form.YearFee;
import gmms.unifiedPay.payResultEntity.QueryOrderResult;
import gmms.util.DateUtils;
import gmms.util.DomainCopyUtil;
import gmms.util.StringUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangfs on 2017/6/28. helloWorld
 */
@Service
public class FmFeeService {
    private Logger LOGGER = LoggerFactory.getLogger(FmFeeService.class);

    @Autowired
    private FmFeeDao feeDao;

/*    @Autowired
    private VmVehicleService vmVehicleService;*/

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private VmVehicleDao vmVehicleDao;

    @Autowired
    private FmAliWeiChartPayLogDao fmAliWeiChartPayLogDao;
    @Autowired
    private SysConfigDao sysConfigDao;

    /**
     * 根据订单号查找微信支付宝支付留档
     *
     * @param orderNo
     * @param payOrderNo
     * @return
     */
    public FmAliWeiChartPayLog findById(String orderNo, String payOrderNo) {
        FmAliWeiChartPayLog fmAliWeiChartPayLog = fmAliWeiChartPayLogDao.findByOrderNoAndPayOrderNo(orderNo, payOrderNo);
        return fmAliWeiChartPayLog;
    }

    public List<FmAliWeiChartPayLog> findFmAliWeiChartPayLogByVehicleNo(String vehicleNo) {
        List<SearchFilter> filters = Lists.newArrayList();

        if (StringUtils.isNotNullBlank(vehicleNo)) {
            filters.add(new SearchFilter("vehicleNo", SearchFilter.Operator.EQ, vehicleNo));
        }
        Specification<FmAliWeiChartPayLog> spec = DynamicSpecifications.bySearchFilter(filters, FmAliWeiChartPayLog.class);
        List<FmAliWeiChartPayLog> fmAliWeiChartPayLogs = fmAliWeiChartPayLogDao.findAll(spec, new Sort(Sort.Direction.DESC, "inputtime"));

        return fmAliWeiChartPayLogs;
    }

    /**
     * 更新第三方支付订单
     * @param extPam(主动查询的最后一次参数)
     * @param vmVehicle
     * @param payType
     * @param queryOrderResult
     * @param fmAliWeiChartPayLog
     * @param notifyextPam(回调接口参数)
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public List<FmFee> updateFmAliWeiChartPayLog(String extPam, VmVehicle vmVehicle, String payType, QueryOrderResult queryOrderResult, FmAliWeiChartPayLog fmAliWeiChartPayLog,String notifyextPam) throws Exception {
        //更新
        //交易结束
        List<FmFee> fmFees = null;

        //总金额
        if (null != queryOrderResult.getTotalFee()) {
            Double fee = Double.valueOf(queryOrderResult.getTotalFee() / 100d);
            fmAliWeiChartPayLog.setFee(fee);
        }
        //交易状态
        if (null != queryOrderResult.getTradeStatus()) {
            String payStatus=getPayStatus(queryOrderResult.getTradeStatus());
            if(StringUtils.isNotNullBlank(payStatus)) {
                fmAliWeiChartPayLog.setPayStatus(Long.valueOf(payStatus));
            }else{
                fmAliWeiChartPayLog.setPayStatus(0L);
            }
        }else{
            fmAliWeiChartPayLog.setPayStatus(0L);
        }

        //最后一次查询参数
        if (StringUtils.isNotNullBlank(extPam)) {
            //最后一次查询时间
            fmAliWeiChartPayLog.setUpdatetime(new Date());
            fmAliWeiChartPayLog.setUpdateExtPam(extPam);
            String payStatus=getPayStatus(queryOrderResult.getTradeStatus());
            if(StringUtils.isNotNullBlank(payStatus)) {
                fmAliWeiChartPayLog.setPayStatus(Long.valueOf(payStatus));
            }else{
                fmAliWeiChartPayLog.setUpdateSign(0L);
            }

        }

        if(StringUtils.isNotNullBlank(notifyextPam)){
            //回调接口的参数与时间
            fmAliWeiChartPayLog.setNotifytime(new Date());
            fmAliWeiChartPayLog.setNotifyExtPam(notifyextPam);
        }

        //缴费成功保存fee表
        if (null != vmVehicle)
            fmFees = saveFmFee(vmVehicle,fmAliWeiChartPayLog, Long.valueOf(queryOrderResult.getPayType()));

        if (null != fmFees) {
            fmAliWeiChartPayLog.setPaytime(new Date());
            fmAliWeiChartPayLog.setSubmitType(Long.valueOf(payType));
            for (FmFee fmFee : fmFees) {
                if (fmFee.getfFeeReason().equals(Long.valueOf(1L))) {
                    fmAliWeiChartPayLog.setFeeID(fmFee.getfId());
                    fmAliWeiChartPayLog.setPlateno(fmFee.getfPlateNo());
                    System.out.println("================= : " + fmAliWeiChartPayLog.getUpdateSign());
                }
            }
        }
        if (null != queryOrderResult.getTradeStatus() && (queryOrderResult.getTradeStatus().toUpperCase().contains("TRADE_FINISHED") || queryOrderResult.getTradeStatus().toUpperCase().contains("TRADE_SUCCESS"))) {
            vmVehicle.setUpdateTime(new Date());//修改时间
            vmVehicle.setNewCarRegistNode(1L);//缴费成功
            vmVehicleDao.save(vmVehicle);
        }

        fmAliWeiChartPayLogDao.save(fmAliWeiChartPayLog);

        return fmFees;

    }

    @Transactional(rollbackFor = Exception.class)
    public List<FmFee> saveFmFee(VmVehicle vmVehicle, FmAliWeiChartPayLog fmAliWeiChartPayLog, Long paytype) {
        List<FmFee> fmFees = new ArrayList<FmFee>();
        String plazaNo = vmVehicle.getVehicleNo().substring(0, 5);
                        FmFee fmFee = new FmFee();
                        int initNumber = 1;
                        String currDate = plazaNo + DateUtils.getCurrTimeStr(6);
                        String predix="FmFee.fid";


                        fmFee.setfVehicleNo(vmVehicle.getVehicleNo());
                        fmFee.setfPlateNo(vmVehicle.getPlateNo());
                        fmFee.setfPlateColor(vmVehicle.getPlateColor());
                        fmFee.setfVehicleKind(vmVehicle.getvKindNo());
                        //微信
                        if (paytype.equals(92L))
                            fmFee.setfSubmitType("57");//支付方式：56-4S店-支付宝  57-4S店-微信
                        //支付宝
                        if (paytype.equals(91L))
                            fmFee.setfSubmitType("56");//支付方式：56-4S店-支付宝  57-4S店-微信

                        //fmFee.setfFeeReason(1L);//缴交理由 正常年费
                        fmFee.setfFeeTime(new Date());
                        fmFee.setCustName(vmVehicle.getCustName());
                        fmFee.setHandleName(vmVehicle.getHandleName());
                        fmFee.setPhone(vmVehicle.getPhone());

                        fmFee.setfPlazaNo(plazaNo);

                        fmFee.setfModifyTime(new Date());
                        fmFee.setfUserId(vmVehicle.getUserId());
                        fmFee.setfUserNo(vmVehicle.getUserNo());
                        fmFee.setfUserName(vmVehicle.getUserName());

                        fmFee.setfFeeReason(1L);//缴交理由 新卡保外换卡  保内换卡
                        //主键
                        fmFee.setfId( generate(currDate,predix) );

                        fmFee.setfCalcFee(Float.valueOf(sysConfigDao.findByCfConfigName("CardExpense").getCfConfigValue()));//应缴工本费
                        fmFee.setfSubmitFee(fmAliWeiChartPayLog.getFee().floatValue());//实缴工本费

                    fmFees.add(feeDao.save(fmFee));

        return fmFees;
    }

    public FmAliWeiChartPayLog updateFmAliWeiChartPayLogNotify(String notifyextPam,String extPam,FmAliWeiChartPayLog fmAliWeiChartPayLog){
        if(StringUtils.isNotNullBlank(notifyextPam)) {
            fmAliWeiChartPayLog.setNotifytime(new Date());
            fmAliWeiChartPayLog.setNotifyExtPam(notifyextPam);
        }
        if(StringUtils.isNotNullBlank(extPam)) {
            fmAliWeiChartPayLog.setUpdatetime(new Date());
            fmAliWeiChartPayLog.setUpdateExtPam(extPam);
        }
        return fmAliWeiChartPayLogDao.save(fmAliWeiChartPayLog);
    }




    public String getPayStatus(String tradestatus){
        String tradeStatus = tradestatus.toUpperCase() ;
        String result ="0";
        if ((tradeStatus.equals("TRADE_WAIT".toUpperCase()) || tradeStatus.equals("WAIT".toUpperCase()))){
                 result ="0";
        } else  if(tradeStatus.equals("TRADE_SUCCESS".toUpperCase())){
                 result ="1";
        }else if(tradeStatus.equals("TRADE_FINISHED".toUpperCase())){
            result ="2";
        }else if(tradeStatus.equals("TRADE_REFUND".toUpperCase())){
            result ="3";
        }else if(tradeStatus.equals("TRADE_CLOSED".toUpperCase())){
            result ="4";
        }else if(tradeStatus.equals("TRADE_REFUND_PART".toUpperCase())){
            result ="6";
        }else if(tradeStatus.equals("TRADE_EXCEPTION".toUpperCase())){
            result ="-5";
        } else if(tradeStatus.equals("TRADE_FAIL".toUpperCase())){
            result ="-10";
        }else if(tradeStatus.equals("TRADE_CANCEL".toUpperCase())){
            result ="-15";
        }else if(tradeStatus.equals("TRADE_TIMEOUT".toUpperCase())){
            result ="-30";
        }
        return result;
    }

    /**
     * 保内换卡缴费记录
     * @param vmVehicle
     * @param users
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public FmFee saveInSureFmFee(VmVehicle vmVehicle,Users users) {
        List<FmFee> fmFees = new ArrayList<FmFee>();
        String plazaNo = vmVehicle.getVehicleNo().substring(0, 5);
        FmFee fmFee = new FmFee();
        int initNumber = 1;
        String currDate = plazaNo + DateUtils.getCurrTimeStr(6);
        String predix="FmFee.fid";
        fmFee.setfVehicleNo(vmVehicle.getVehicleNo());
        fmFee.setfPlateNo(vmVehicle.getPlateNo());
        fmFee.setfPlateColor(vmVehicle.getPlateColor());
        fmFee.setfVehicleKind(vmVehicle.getvKindNo());
        fmFee.setfSubmitType("0");//支付方式：56-4S店-支付宝  57-4S店-微信  0-保内换卡免缴

        //fmFee.setfFeeReason(1L);//缴交理由 正常年费
        fmFee.setfFeeTime(new Date());
        fmFee.setCustName(vmVehicle.getCustName());
        fmFee.setHandleName(vmVehicle.getHandleName());
        fmFee.setPhone(vmVehicle.getPhone());

        fmFee.setfPlazaNo(plazaNo);

        fmFee.setfModifyTime(new Date());
        fmFee.setfUserId(vmVehicle.getUserId());
        fmFee.setfUserNo(vmVehicle.getUserNo());
        fmFee.setfUserName(vmVehicle.getUserName());

        fmFee.setfFeeReason(1L);//缴交理由 新卡保外换卡  保内换卡
        //主键
        fmFee.setfId( generate(currDate,predix) );

        fmFee.setfCalcFee(0f);//应缴工本费
        fmFee.setfSubmitFee(0f);//实缴工本费

        FmFee fmFee1=feeDao.save(fmFee);

        //缴费成功后节点变为贴卡
        vmVehicle.setNewCarRegistNode(1L);
        vmVehicle.setUpdateTime(new Date());
        vmVehicleDao.save(vmVehicle);

        return fmFee1;
    }


    /**
     * 第三方支付订单
     *
     * @param orderNo
     * @param payOrderNo
     * @param extPam
     * @param vmVehicle
     * @param payType
     * @return
     */
    public String saveFmAliWeiChartPayLog(String orderNo, String payOrderNo, String extPam, VmVehicle vmVehicle, String payType, Users users) {
        //VmVehicle vmVehicleTemp= vmVehicleDao.findByVVehicleNo(vmVehicle.getvVehicleNo());
        String result = "";
        FmAliWeiChartPayLog fmAliWeiChartPayLog = fmAliWeiChartPayLogDao.findByOrderNo(orderNo);
        if (null == fmAliWeiChartPayLog) {
            FmAliWeiChartPayLog fmAliWeiChartPayLog1 = new FmAliWeiChartPayLog();

            if (StringUtils.isNotNullBlank(orderNo))
                fmAliWeiChartPayLog1.setOrderNo(orderNo);

            if (StringUtils.isNotNullBlank(payOrderNo))
                fmAliWeiChartPayLog1.setPayOrderNo(payOrderNo);

            if (StringUtils.isNotNullBlank(extPam))
                fmAliWeiChartPayLog1.setPayExtPam(extPam);

            if (StringUtils.isNotNullBlank(vmVehicle.getVehicleNo()))
                fmAliWeiChartPayLog1.setVehicleNo(vmVehicle.getVehicleNo());
            if (StringUtils.isNotNullBlank(vmVehicle.getPlateNo()))
                fmAliWeiChartPayLog1.setPlateno(vmVehicle.getPlateNo());

            if (null != users.getId())
                fmAliWeiChartPayLog1.setUserID(users.getId());

            if (StringUtils.isNotNullBlank(users.getUserName()))
                fmAliWeiChartPayLog1.setUserName(users.getUserName());


            if (StringUtils.isNotNullBlank(payType))
                fmAliWeiChartPayLog1.setSubmitType(Long.valueOf(payType));

            fmAliWeiChartPayLog1.setEndSign(0);
            fmAliWeiChartPayLogDao.save(fmAliWeiChartPayLog1);
            result = "add";
        } else {

            if (StringUtils.isNotNullBlank(orderNo))
                fmAliWeiChartPayLog.setOrderNo(orderNo);

            if (StringUtils.isNotNullBlank(payOrderNo))
                fmAliWeiChartPayLog.setPayOrderNo(payOrderNo);

            if (StringUtils.isNotNullBlank(extPam))
                fmAliWeiChartPayLog.setPayExtPam(extPam);
            fmAliWeiChartPayLog.setPayStatus(0L);
            fmAliWeiChartPayLog.setInputtime(new Date());
            fmAliWeiChartPayLogDao.save(fmAliWeiChartPayLog);
            result = "update";
        }
        return result;

    }




    public List<FmAliWeiChartPayLog> getALLFmAliWeiChartPayLogHasPay(){
        List<SearchFilter> filters = Lists.newArrayList();

        filters.add(new SearchFilter("payStatus", SearchFilter.Operator.EQ, 1L));
        //filters.add(new SearchFilter("buComputerNo", SearchFilter.Operator.EQ, 0L));
        //filters.add(new SearchFilter("buUsedState", SearchFilter.Operator.NEQ, 1L));
        Specification<FmAliWeiChartPayLog> spec = DynamicSpecifications.bySearchFilter(filters, FmAliWeiChartPayLog.class);
        List<FmAliWeiChartPayLog> fms = fmAliWeiChartPayLogDao.findAll(spec);
        return fms;

    }

    /*********************************************数据库操作*********************************************************************/

    private static final Map<String, IdPool> ID_MAP = new ConcurrentHashMap<String, IdPool>();
    private int poosize=2;
    public synchronized String generate( String serialNumber,String objString) throws HibernateException {

       // String dayStr= DateUtils.getCurrDateStr(1);
        String entityName = objString+serialNumber;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("需要获取主键的实体名:[" + entityName + "]");
        }
        if (ID_MAP.get(entityName) == null) {
            initalize(entityName);
        }
        String id = serialNumber+ID_MAP.get(entityName).getNextId();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("线程:[" + Thread.currentThread().getName() + "] " +
                    "实体:[" + entityName + "] 生成的主键为:[" + id + "]");
        }
        return id;
    }
    /**
     * 初始化对象的主键
     *
     * @param entityName 对象名称
     */
    private void initalize(String entityName) {
        IdPool pool = new IdPool(entityName, poosize);
        ID_MAP.put(entityName, pool);
    }

/*********************************************************************************************************************/


}
