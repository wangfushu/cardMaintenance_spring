package gmms.unifiedPay;

import com.alibaba.fastjson.JSONObject;
import com.xmrbi.parking.CheckCode;
import gmms.unifiedPay.payConfig.BaseConfig;
import gmms.unifiedPay.payEntity.BaseDataPay;
import gmms.unifiedPay.payEntity.CancelOrderEntity;
import gmms.unifiedPay.payEntity.RefundPayEntity;
import gmms.unifiedPay.payEntity.UnifiedPay;
import gmms.util.ObjectUtil;
import gmms.util.StringUtil;
import gmms.util.StringUtils;
import gmms.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wangfs on 2017-12-26 helloword.
 */
public class RefundPay {
    // 接口地址
    private Logger LOGGER = LoggerFactory.getLogger(RefundPay.class);
    private String rbi_refund_url = "";//测试库外网
   // private String rbi_refund_url = "";//测试库外网

    // private String rbi_refund_url="";//正式库


    public String refundPay(String orderNO,String payOrderNo,Double totalFee) throws Exception {
        Double fentotalFee=totalFee*100;//精确到分
        String totalFeeStr=String.valueOf(fentotalFee.intValue());
        BaseDataPay baseDataPay = initBaseDataPay();
        RefundPayEntity refundPayEntity = initRefundPay(orderNO, payOrderNo, baseDataPay.getTimestamp(), "用户退款", totalFeeStr);
        String sign = getsign(baseDataPay, refundPayEntity);
       /* System.out.println("=======================sign  : "+sign);*/
        baseDataPay.setSign(sign);


        TreeMap<String, Object> params = new TreeMap<String, Object>();
        Map<String, Object> baseDataPayparams = ObjectUtil.objectToMap(baseDataPay);
        Map<String, Object> queryOrderPayparams = ObjectUtil.objectToMap(refundPayEntity);
        params.putAll(baseDataPayparams);
        params.putAll(queryOrderPayparams);

        String data = JSONObject.toJSONString(params, true);
        System.out.println("=======================json串1  : "+data);
        String result = "";
        result = WebUtil.postJson(rbi_refund_url, data);
        LOGGER.info(" WeiXinALI cancelOrder Result : "+ result);
        return result;
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
     * 退款的初始参数
     * @param orderNO
     * @param payOrderNo
     * @return
     */
    public RefundPayEntity initRefundPay(String orderNO,String payOrderNo,String timestamp,String refundReason,String refundFee) {
        RefundPayEntity refundPayEntity = new RefundPayEntity();
        refundPayEntity.setOrderNo(orderNO);
        refundPayEntity.setPayOrderNo(payOrderNo);
        refundPayEntity.setRefundReason(refundReason);
        refundPayEntity.setRefundFee(refundFee);

        StringBuilder strb = new StringBuilder();
        strb.append("orderNo=" + orderNO);
        strb.append("&payOrderNo=" + payOrderNo);
        strb.append("&refundFee=" + refundFee);
        strb.append("&timestamp=" + timestamp);

        byte[] bytes = strb.toString().getBytes();
        byte[] checkCode = CheckCode.getCheckCode(bytes.length, bytes);
        refundPayEntity.setCheckCode(StringUtil.toHexString(checkCode));


        return refundPayEntity;
    }


    /**
     * sign
     * @param baseDataPay
     * @param refundPayEntity
     * @return
     */
    public String getsign(BaseDataPay baseDataPay, RefundPayEntity refundPayEntity) {
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
        if(StringUtils.isNotNullBlank(refundPayEntity.getOrderNo()))
        strb.append("&orderNo=" + refundPayEntity.getOrderNo());

        if(StringUtils.isNotNullBlank(refundPayEntity.getPayOrderNo()))
            strb.append("&payOrderNo=" + refundPayEntity.getPayOrderNo());

        if(StringUtils.isNotNullBlank(refundPayEntity.getRefundReason()))
            strb.append("&refundReason=" +refundPayEntity.getRefundReason());

        strb.append("&refundFee=" + refundPayEntity.getRefundFee());
        strb.append("&checkCode=" + refundPayEntity.getCheckCode());
        strb.append(BaseConfig.secret);

        return strb.toString();
    }


}
