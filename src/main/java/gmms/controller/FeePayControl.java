package gmms.controller;


import com.alibaba.fastjson.JSONObject;
import gmms.domain.db.*;
import gmms.domain.form.YearFee;
import gmms.domain.query.OrderQueryTurnForm;
import gmms.service.BaseInformationService;
import gmms.service.FmFeeService;
import gmms.service.UsersService;
import gmms.service.VmVehicleService;
import gmms.unifiedPay.RBIPay;
import gmms.unifiedPay.RbiQueryPayOrder;
import gmms.unifiedPay.payConfig.BaseConfig;
import gmms.unifiedPay.payEntity.NotifyUrlForm;
import gmms.unifiedPay.payResultEntity.AliPayResult;
import gmms.unifiedPay.payResultEntity.QueryOrderResult;
import gmms.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfs on 2017-10-10 helloword.
 *
 * 支付控制层
 *
 */

@RequestMapping("/feepay")
@Controller
public class FeePayControl {

    private Logger LOGGER = LoggerFactory.getLogger(FeePayControl.class);
    @Autowired
    private VmVehicleService vmVehicleService;

    @Autowired
    private BaseInformationService baseInformationService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private FmFeeService fmFeeService;


    /**
     * 计算年费欠费以及相应的需要交费具体信息
     * @param vehicleNo
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/gettotalpay", method = RequestMethod.POST)
    private String getTotalPay(String vehicleNo, Model model) {
        VmVehicle vmVehicle = vmVehicleService.findById(vehicleNo);
        SysConfig sysConfig =baseInformationService.findCardExpenseByValue();
        model.addAttribute("vmVehicle", vmVehicle);
        model.addAttribute("fee",sysConfig.getCfConfigValue());//工本费用
        return JsonMapper.nonEmptyMapper().toJson(model);
    }

    /**
     * 获取二维码
     * @param vehicleNo
     * @param payType
     * @param totalFee
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getqrcode", method = RequestMethod.POST)
    private String getQrcode(String vehicleNo, String payType, Double totalFee,  Long userId ,String installType) {
        Users users= usersService.findById(userId);

        //返回的参数{"extPam":{"code_url":"weixin://wxpay/bizpayurl?pr=DQVcCZ9","appid":"wxe06b890980f4cd92"},"orderNo":"4SPAY20171023160850876","payOrderNo":"20171023160844937865003362974860","isOk":true}
        String orderNo = "2CPAY" + DateUtils.getCurrTimeStr(5);
        VmVehicle vmVehicle = vmVehicleService.findById(vehicleNo);
        if (null != vmVehicle) {
            String flag = fmFeeService.saveFmAliWeiChartPayLog(orderNo, "", "", vmVehicle, payType,users,installType);

            if (flag == "add") {

                RBIPay rbiPay = new RBIPay();
                try {
                    String reslut = rbiPay.Pay(payType, totalFee, vmVehicle.getPlateNo(), orderNo);
                    LOGGER.info(vehicleNo + "    getqrcode: " + reslut);
                    AliPayResult aliPayResult = JSONObject.parseObject(reslut, AliPayResult.class);
                    if ("true".equals(aliPayResult.getIsOk()))
                        fmFeeService.saveFmAliWeiChartPayLog(aliPayResult.getOrderNo(), aliPayResult.getPayOrderNo(), reslut, vmVehicle, payType, users,installType);
                    return JsonMapper.nonEmptyMapper().toJson(aliPayResult);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error("车牌[{}]----getqrcode error : {}" ,vehicleNo, e);
                    return "error";
                }
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }


    /**
     * 支付平台回调接口（支付公共后回调）
     * @param notifyUrlForm
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    private String Querynotify(@RequestBody NotifyUrlForm notifyUrlForm) throws UnsupportedEncodingException {
        LOGGER.info("微信支付回调了：" + notifyUrlForm.getAppId() + ",orderNo:" + notifyUrlForm.getOrderNo()+",TradeStatus:" + notifyUrlForm.getTradeStatus());
        System.out.println("orderNo==================== : " + notifyUrlForm.getOrderNo());
        if (null != notifyUrlForm.getTradeStatus()) {
            if (notifyUrlForm.getTradeStatus().toUpperCase().contains("TRADE_FINISHED") || notifyUrlForm.getTradeStatus().toUpperCase().contains("TRADE_SUCCESS")) {
                //请求支付是否成功
                String signs = CalSign(notifyUrlForm.getAppId() + IsNulls("orderNo", notifyUrlForm.getOrderNo()) + IsNulls("totalFee", notifyUrlForm.getTotalFee()) + IsNulls("payType", notifyUrlForm.getPayType()) + IsNulls("optType", notifyUrlForm.getOptType())
                        + IsNulls("attach", notifyUrlForm.getAttach()) + IsNulls("payOrderNo", notifyUrlForm.getPayOrderNo()) + IsNulls("outTradeNo", notifyUrlForm.getOutTradeNo())
                        + IsNulls("payStatus", notifyUrlForm.getPayStatus()) + IsNulls("tradeStatus", notifyUrlForm.getTradeStatus()) + IsNulls("backEnd", notifyUrlForm.getBackEnd()) + IsNulls("extPam", notifyUrlForm.getExtPam()) + BaseConfig.secret);

                LOGGER.info("signs：" + signs + ",notifyUrlForm.extPam" + notifyUrlForm.getExtPam() + ",OrderNo:" + notifyUrlForm.getOrderNo() + ",notifyUrlForm.sign:" +
                        notifyUrlForm.getSign() + ",签名数据:" + notifyUrlForm.getAppId() + IsNulls("orderNo", notifyUrlForm.getOrderNo()) + IsNulls("totalFee", notifyUrlForm.getTotalFee()) + IsNulls("payType", notifyUrlForm.getPayType()) + IsNulls("optType", notifyUrlForm.getOptType())
                        + IsNulls("attach", notifyUrlForm.getAttach()) + IsNulls("payOrderNo", notifyUrlForm.getPayOrderNo()) + IsNulls("outTradeNo", notifyUrlForm.getOutTradeNo())
                        + IsNulls("payStatus", notifyUrlForm.getPayStatus()) + IsNulls("tradeStatus", notifyUrlForm.getTradeStatus()) + IsNulls("backEnd", notifyUrlForm.getBackEnd()) + IsNulls("extPam", notifyUrlForm.getExtPam()) + BaseConfig.secret + ",newsign:\r\n");

                if (signs.equals(notifyUrlForm.getSign())) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        LOGGER.info("回调异常2： orderNo : " + notifyUrlForm.getOrderNo() + " payOrderNo : " + notifyUrlForm.getPayOrderNo() + " error :" + e.getStackTrace());

                    }
                    //订单缴费成功
                    System.out.println("成功了没有");
                    FmAliWeiChartPayLog fmAliWeiChartPayLog = fmFeeService.findById(notifyUrlForm.getOrderNo(), notifyUrlForm.getPayOrderNo());
                    if (null != fmAliWeiChartPayLog) {
                        VmVehicle vmVehicle = vmVehicleService.findById(fmAliWeiChartPayLog.getVehicleNo());

                        RbiQueryPayOrder rbiQueryPayOrder = new RbiQueryPayOrder();
                        try {
                            String result = rbiQueryPayOrder.queryOrder(notifyUrlForm.getOrderNo(), notifyUrlForm.getPayOrderNo());
                            QueryOrderResult queryOrderResult = JSONObject.parseObject(result, QueryOrderResult.class);
                            //判断是否需要过账
                            if (StringUtils.isNullBlank(fmAliWeiChartPayLog.getFeeID()) && fmAliWeiChartPayLog.getPayStatus().equals(0L)) {

                                List<FmFee> fmFees = fmFeeService.updateFmAliWeiChartPayLog("", vmVehicle, queryOrderResult.getPayType(), queryOrderResult, fmAliWeiChartPayLog, result);
                            }else{
                                FmAliWeiChartPayLog fmAliWeiChartPayLogNotify = fmFeeService.updateFmAliWeiChartPayLogNotify(result,"",fmAliWeiChartPayLog);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            LOGGER.info("回调异常： orderNo : " + notifyUrlForm.getOrderNo() + " payOrderNo : " + notifyUrlForm.getPayOrderNo() + " error :" + e.getStackTrace());
                        }
                    }

                    return "success";


                } else    if(notifyUrlForm.getTradeStatus().toUpperCase().contains("TRADE_WAIT")|| notifyUrlForm.getTradeStatus().toUpperCase().contains("TRADE_SCAN")||notifyUrlForm.getTradeStatus().toUpperCase().contains("TRADE_USERCANCEL")){
                    LOGGER.info("已经被扫码，单号orderNo ——{}：" ,notifyUrlForm.getOrderNo() );
                    return "success";
                }else {
                    return "false";
                }
            } else {
                return "false";
            }
        }
        return "false";
    }

    /**
     * 客户端主动轮询调接口查询订单状态
     * @param orderNO
     * @param payOrderNo
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOrderStates", method = RequestMethod.POST)
    public String queryOrderStates(String orderNO, String payOrderNo, Long userId) {
        String result=queryOrderStatesMethod(orderNO,payOrderNo);
        return result;
    }
    /**
     * 判断订单是否已经支付
     * @param vehicleNo
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryIsOrder", method = RequestMethod.POST)
    public String queryIsOrder(String vehicleNo, Long userId) {
        try {
            String result="";
            List<FmAliWeiChartPayLog> fmAliWeiChartPayLogs=fmFeeService.findFmAliWeiChartPayLogByVehicleNo(vehicleNo);
            if(fmAliWeiChartPayLogs.size()>0){
                if(null!=fmAliWeiChartPayLogs.get(0).getInputtime()) {
                    if (DateUtil.getDiffsecend(fmAliWeiChartPayLogs.get(0).getInputtime(), new Date()) >= 130000) {
                        //两分钟之内不让创建新的订单
                        if(fmAliWeiChartPayLogs.get(0).getEndSign()==0) {
                            result = queryOrderStatesMethod(fmAliWeiChartPayLogs.get(0).getOrderNo(), fmAliWeiChartPayLogs.get(0).getPayOrderNo());
                            if (result.equals("fail") || result.equals("error")) {
                                return "notpay";//未支付成功
                            } else {
                                return "ispay";//已经支付成功
                            }
                        }else{
                            return "notpay";
                        }

                    } else {
                        result = queryOrderStatesMethod(fmAliWeiChartPayLogs.get(0).getOrderNo(), fmAliWeiChartPayLogs.get(0).getPayOrderNo());
                        if (result.equals("fail") || result.equals("error")) {
                            return "timeless";//两分钟之内不让创建新的订单
                        } else {
                            return "ispay";//已经支付成功
                        }
                    }
                }else{
                    return "notpay";//未支付成功
                }
            }else{
                return "notpay";//未支付成功
            }
        }catch (Exception e){
            return "error";
        }

    }

    /**
     * 查询订单状态方法
     * @param orderNO
     * @param payOrderNo
     * @return
     */
    public String queryOrderStatesMethod(String orderNO,String payOrderNo){
        RbiQueryPayOrder rbiQueryPayOrder = new RbiQueryPayOrder();
        try {

            String result = rbiQueryPayOrder.queryOrder(orderNO, payOrderNo);
            QueryOrderResult queryOrderResult = JSONObject.parseObject(result, QueryOrderResult.class);
            FmAliWeiChartPayLog fmAliWeiChartPayLog = fmFeeService.findById(queryOrderResult.getOrderNo(), queryOrderResult.getPayOrderNo());
            if (null != queryOrderResult.getTradeStatus() && (queryOrderResult.getTradeStatus().toUpperCase().contains("TRADE_FINISHED") || queryOrderResult.getTradeStatus().toUpperCase().contains("TRADE_SUCCESS"))) {
                LOGGER.info(" WeiXinALI is SUCCESS queryOrder Result : "+ result);
                //获取交易信息以及车辆信息
                if (null != fmAliWeiChartPayLog) {
                    VmVehicle vmVehicle = vmVehicleService.findById(fmAliWeiChartPayLog.getVehicleNo());

                    if(StringUtils.isNullBlank(fmAliWeiChartPayLog.getFeeID())&&fmAliWeiChartPayLog.getPayStatus().equals(0L)) {
                        //判断是否需要过账
                        List<FmFee> fmFees = fmFeeService.updateFmAliWeiChartPayLog(result, vmVehicle, queryOrderResult.getPayType(), queryOrderResult, fmAliWeiChartPayLog,"");

                    }else{
                        FmAliWeiChartPayLog fmAliWeiChartPayLogNotify = fmFeeService.updateFmAliWeiChartPayLogNotify("",result,fmAliWeiChartPayLog);
                    }
                    return JsonMapper.nonEmptyMapper().toJson(new OrderQueryTurnForm(queryOrderResult.getPayOrderNo(), queryOrderResult.getPayType(), queryOrderResult.getTotalFee(),  vmVehicle.getPlateNo()));

                } else {
                    return JsonMapper.nonEmptyMapper().toJson(new OrderQueryTurnForm(queryOrderResult.getPayOrderNo(), queryOrderResult.getPayType(), queryOrderResult.getTotalFee()));
                }

            } else {
                LOGGER.info(" WeiXinALI is Fail queryOrder Result : "+ result);
                fmFeeService.updateFmAliWeiChartPayLog(result, null, "",queryOrderResult, fmAliWeiChartPayLog,"");
                return "fail";
            }
        } catch (Exception e) {
            LOGGER.error("支付出现异常 ："+e);
            return "error";
        }
    }



    /**
     * 判断保内换卡或者保外换卡
     * @param vehicleNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkInSurance", method = RequestMethod.POST)
    private String CheckVmVehicle(String vehicleNo) {
        VmVehicle vmVehicle = vmVehicleService.findById(vehicleNo);
        if (null!=vmVehicle) {
                    if(null!=vmVehicle.getInstallDate()){
                        Integer insureDays=Integer.valueOf(baseInformationService.findInSureYearByValue().getCfConfigValue());
                        if(DateUtils.getCurrDate().before( DateUtil.getDateAfterDays(vmVehicle.getInstallDate(), insureDays))){
                                List<FmAliWeiChartPayLog> fmAliWeiChartPayLogs = fmFeeService.findFmAliWeiChartPayLogByVehicleNo(vehicleNo);
                                if(fmAliWeiChartPayLogs.size()>0 ?  null!= fmAliWeiChartPayLogs.get(0).getInputtime() ? fmAliWeiChartPayLogs.get(0).getEndSign()==0 ? fmAliWeiChartPayLogs.get(0).getInstallType()==4 : false : false : false){
                                    return "4"; //人为损坏
                                }else {
                                    return "2";//保内换卡
                                }
                        }else{
                            return "3";//保外换卡
                        }
                    }else{
                        return "1";//新卡
                    }
        } else {
            return "false";//无此车辆
        }
    }

    /**
     * 保内换卡缴费记录提交
     * @param vehicleNo
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/inSureTimeFee", method = RequestMethod.POST)
    public String inSureTimeFee(String vehicleNo,Long userId){
        try {
            VmVehicle vmVehicle= vmVehicleService.findById(vehicleNo);
            Users users =usersService.findById(userId);
            if(null!=vmVehicle){
                FmFee fmFee= fmFeeService.saveInSureFmFee(vmVehicle,users);
                return "success";
            }else{
                return "fail";
            }
        }catch (Exception e){
            LOGGER.error("inSureTimeFee  保内换卡 异常{}",e);
            return "fail";
        }


    }



    public String IsNulls(String names, String values) {
        if (StringUtils.isNullBlank(values)) {
            return "";
        } else {
            return "&" + names + "=" + values;
        }
    }
    public String CalSign(String value) throws UnsupportedEncodingException {
        MD5 md5 = new MD5();
        return Base64Util.encode(md5.GetMD5Code(value).getBytes()).toUpperCase();
    }

}

