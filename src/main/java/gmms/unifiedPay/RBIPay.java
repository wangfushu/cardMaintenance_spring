package gmms.unifiedPay;

import com.alibaba.fastjson.JSONObject;

import com.xmrbi.parking.CheckCode;
import gmms.unifiedPay.conn.Connection;
import gmms.unifiedPay.payConfig.BaseConfig;
import gmms.unifiedPay.payEntity.BaseDataPay;
import gmms.unifiedPay.payEntity.UnifiedPay;
import gmms.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wangfs on 2017-10-12 helloword.
 */
public class RBIPay extends Connection {

    // 接口地址
    private Logger LOGGER = LoggerFactory.getLogger(RBIPay.class);
    private String rbi_url = "http://172.16.52.39:50080/uniform-pay-biz/unifiedPay";//测试库内网
   // private String rbi_url = "http://117.29.161.2:50080/uniform-pay-biz/unifiedPay";//测试库外网
   //

   // private String rbi_url="http://172.23.0.2:58080/uniform-pay-biz/unifiedPay";//正式库

    //private String notify_url = "http://125.77.254.250:5083/upgradeFee/feepay/notify";
    //private String notify_url = "http://wangfushu.tunnel.qydev.com/upgradeFee/feepay/notify";
    private String notify_url = "http://172.16.54.205:8089/upgradeFee/feepay/notify";
    //private String notify_url = "http://192.168.14.111:8089/upgradeFee/feepay/notify";

    public String Pay(String payType,Double totalFee,String payYearString,String plateNo,String orderNo) throws Exception {

        //int allfee=totalFee.intValue();
      /*  int initNumber = 1;
        String formatNum = StringUtils.getFormat(5, initNumber);*/
        //    UnifiedPay unifiedPay=new UnifiedPay();
        Double fentotalFee=totalFee*100;//精确到分
        String totalFeeStr=String.valueOf(fentotalFee.intValue());

        BaseDataPay baseDataPay = initBaseDataPay();
        UnifiedPay unifiedPay = initUnifiedPay("91",totalFeeStr, baseDataPay.getTimestamp(),payYearString,plateNo,orderNo);


        String sign = getsign(baseDataPay, unifiedPay);
        //String sign="201701220001&version=1.0&osType=web&devType=testcase&devId=testcase001&timestamp=1511944861078&payType=52&optType=unionpay&title=年费缴交&orderNo=4SPAY20171129164101078&totalFee=1&timeout=2m&attach=1&checkCode=b1911cef793df6b64b44d4ee9d04c901xmrbi3967968@2017";
        System.out.println("==============================sign 未加密 :" +sign);
        baseDataPay.setSign(sign);

        TreeMap<String, Object> params = new TreeMap<String, Object>();
        Map<String, Object> baseDataPayparams = ObjectUtil.objectToMap(baseDataPay);
        Map<String, Object> unifiedPayparams = ObjectUtil.objectToMap(unifiedPay);
        params.putAll(baseDataPayparams);
        params.putAll(unifiedPayparams);

        String data = JSONObject.toJSONString(params, true);
        System.out.println("=======================json串1  : "+data);
        String result = "";
        result = WebUtil.postJson(rbi_url, data);
        LOGGER.info(" WeiXinALI Pay Result : "+ result);
        return result;

    }



    /**
     * 获取支付二维码的初始化参数
     * @param payType
     * @param totalFee
     * @param timestamp
     * @return
     */
    public UnifiedPay initUnifiedPay(String payType, String totalFee, String timestamp,String payYearString,String plateNo,String orderNo) {
        UnifiedPay unifiedPay = new UnifiedPay();
/*        String currDate = DateUtils.getCurrTimeStr(5);
        String orderNo = new String();
        orderNo = "4SPAY"+currDate;*/
        unifiedPay.setPayType(payType);
        unifiedPay.setOrderNo(orderNo);
        unifiedPay.setTotalFee(totalFee);

        StringBuilder strb = new StringBuilder();
        strb.append("orderNo=" + orderNo);
        strb.append("&totalFee=" + totalFee);
        strb.append("&timestamp=" + timestamp);
        System.out.println("========================= : "+strb.toString());

        byte[] bytes = strb.toString().getBytes();
        byte[] checkCode = CheckCode.getCheckCode(bytes.length, bytes);
        unifiedPay.setCheckCode(StringUtil.toHexString(checkCode));


        unifiedPay.setTitle("年费缴交["+payYearString+"]("+plateNo+")");
      /*  unifiedPay.setNotifyUrl("http://wangfushu.tunnel.qydev.com/upgradeFee/feepay/notify");*/
       unifiedPay.setNotifyUrl(notify_url);
        unifiedPay.setOptType("Mobile4S");

       /* unifiedPay.setOptType("MobileDev4S");*/
        unifiedPay.setAttach("1");
        unifiedPay.setTimeout("2m");
       // unifiedPay.setUserId("a");
       // unifiedPay.setBody("0");
        return unifiedPay;
    }


    /**
     * 初始化BaseDataPay
     * @return
     */
    public BaseDataPay initBaseDataPay() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        BaseDataPay _baseDataPay = new BaseDataPay();

        _baseDataPay.setAppId(BaseConfig.appId);
        _baseDataPay.setDevId(BaseConfig.devId);
        _baseDataPay.setDevType(BaseConfig.devType);
        _baseDataPay.setOsType(BaseConfig.osType);
        _baseDataPay.setSignType(BaseConfig.signType);
        _baseDataPay.setTimestamp(timestamp);
        _baseDataPay.setVersion(BaseConfig.version);


        return _baseDataPay;
    }

    /**
     * 获取支付二维码sign
     * @param baseDataPay
     * @param unifiedPay
     * @return
     */
    public String getsign(BaseDataPay baseDataPay, UnifiedPay unifiedPay) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        StringBuilder strb = new StringBuilder();
        strb.append(baseDataPay.getAppId());
        strb.append("&version=" + baseDataPay.getVersion());
        strb.append("&osType=" + baseDataPay.getOsType());

        if(StringUtils.isNotNullBlank(baseDataPay.getDevType()))
        strb.append("&devType=" + baseDataPay.getDevType());

        if(StringUtils.isNotNullBlank(baseDataPay.getDevId()))
        strb.append("&devId=" + baseDataPay.getDevId());


        strb.append("&timestamp=" + baseDataPay.getTimestamp());
        strb.append("&payType=" + unifiedPay.getPayType());

        if(StringUtils.isNotNullBlank(unifiedPay.getOptType()))
        strb.append("&optType=" + unifiedPay.getOptType());

        if(StringUtils.isNotNullBlank(unifiedPay.getNotifyUrl()))
        strb.append("&notifyUrl=" + unifiedPay.getNotifyUrl());

        if(StringUtils.isNotNullBlank( unifiedPay.getTitle()))
        strb.append("&title=" + unifiedPay.getTitle());

        if(StringUtils.isNotNullBlank(unifiedPay.getBody()))
        strb.append("&body=" + unifiedPay.getBody());

        strb.append("&orderNo=" + unifiedPay.getOrderNo());
        strb.append("&totalFee=" + unifiedPay.getTotalFee());

        if(StringUtils.isNotNullBlank(unifiedPay.getTimeout()))
        strb.append("&timeout=" + unifiedPay.getTimeout());
        if(StringUtils.isNotNullBlank(unifiedPay.getAttach()))
        strb.append("&attach=" + unifiedPay.getAttach());

        strb.append("&checkCode=" + unifiedPay.getCheckCode());
        strb.append(BaseConfig.secret);

        return strb.toString();
    }

}
