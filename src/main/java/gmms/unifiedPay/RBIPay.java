package gmms.unifiedPay;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.xmrbi.parking.CheckCode;
import gmms.domain.param.BodyParam;
import gmms.unifiedPay.conn.Connection;
import gmms.unifiedPay.payConfig.BaseConfig;
import gmms.unifiedPay.payEntity.BaseDataPay;
import gmms.unifiedPay.payEntity.UnifiedPay;
import gmms.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wangfs on 2017-10-12 helloword.
 */
public class RBIPay extends Connection {

    // 接口地址
    private Logger LOGGER = LoggerFactory.getLogger(RBIPay.class);
    //private String rbi_url = 
   //private String rbi_url =


   // private String rbi_url=

    private String rbi_url=

    //private String notify_url = 
    //private String notify_url = 
    private String notify_url = 
   // private String notify_url = 

    public String Pay(String payType,Double totalFee,String plateNo,String orderNo) throws Exception {


        Double fentotalFee=totalFee*100;//精确到分
        String totalFeeStr=String.valueOf(fentotalFee.intValue());

        List<BodyParam> bodyParams= Lists.newArrayList();
        //id 535 正式id
        BodyParam bodyParam=new BodyParam("492",totalFeeStr);
        bodyParams.add(bodyParam);
        String bodyStr = JSONObject.toJSONString(bodyParams);

        BaseDataPay baseDataPay = initBaseDataPay();
        System.out.println("-------------------bodysty  " + bodyStr);
        UnifiedPay unifiedPay = initUnifiedPay("91",totalFeeStr, baseDataPay.getTimestamp(),plateNo,orderNo,bodyStr);
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
    public UnifiedPay initUnifiedPay(String payType, String totalFee, String timestamp,String plateNo,String orderNo,String bodyStr) {
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


        unifiedPay.setTitle("二维卡工本费缴交("+plateNo+")");
      /*  unifiedPay.setNotifyUrl("http://wangfushu.tunnel.qydev.com/upgradeFee/feepay/notify");*/
       unifiedPay.setNotifyUrl(notify_url);
        unifiedPay.setOptType("Mobile4S");

       /* unifiedPay.setOptType("MobileDev4S");*/
        unifiedPay.setAttach("1");
        unifiedPay.setTimeout("2m");
       // unifiedPay.setUserId("a");
        unifiedPay.setBody(bodyStr);
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
