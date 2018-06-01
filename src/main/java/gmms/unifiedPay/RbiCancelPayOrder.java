package gmms.unifiedPay;

import com.alibaba.fastjson.JSONObject;
import com.xmrbi.parking.CheckCode;
import gmms.unifiedPay.payConfig.BaseConfig;
import gmms.unifiedPay.payEntity.BaseDataPay;
import gmms.unifiedPay.payEntity.CancelOrderEntity;
import gmms.unifiedPay.payEntity.QueryOrderPay;
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
public class RbiCancelPayOrder {
    private Logger LOGGER = LoggerFactory.getLogger(RbiCancelPayOrder.class);
    //private String rbi_cancel_url = "http://117.29.161.2:50080/uniform-pay-biz/cancelPay";//测试库
    //private String rbi_cancel_url = "https://demoapp.xmparking.net/cloud-pay-api/cancelPay";//测试库外网

     private String rbi_cancel_url="https://app.xmparking.net/cloud-pay-api/cancelPay";//正式库

    public String cancelOrder(String orderNO,String payOrderNo) throws Exception {
        BaseDataPay baseDataPay = initBaseDataPay();
        CancelOrderEntity cancelOrderEntity = initCancelOrder(orderNO,payOrderNo,baseDataPay.getTimestamp(),"撤销订单");
        String sign = getsign(baseDataPay,cancelOrderEntity);
       /* System.out.println("=======================sign  : "+sign);*/
        baseDataPay.setSign(sign);


        TreeMap<String, Object> params = new TreeMap<String, Object>();
        Map<String, Object> baseDataPayparams = ObjectUtil.objectToMap(baseDataPay);
        Map<String, Object> queryOrderPayparams = ObjectUtil.objectToMap(cancelOrderEntity);
        params.putAll(baseDataPayparams);
        params.putAll(queryOrderPayparams);

        String data = JSONObject.toJSONString(params, true);
        System.out.println("=======================json串1  : "+data);
        String result = "";
        result = WebUtil.postJson(rbi_cancel_url, data);
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
     * 取消订单的初始参数
     * @param orderNO
     * @param payOrderNo
     * @param timestamp
     * @param cancelReason
     * @return
     */
    public CancelOrderEntity initCancelOrder(String orderNO,String payOrderNo,String timestamp,String cancelReason) {
        CancelOrderEntity cancelOrderEntity = new CancelOrderEntity();
        cancelOrderEntity.setOrderNo(orderNO);
        cancelOrderEntity.setPayOrderNo(payOrderNo);
        cancelOrderEntity.setCancelReason(cancelReason);

        StringBuilder strb = new StringBuilder();
        strb.append("orderNo=" + orderNO);
        strb.append("&payOrderNo=" + payOrderNo);
        strb.append("&timestamp=" + timestamp);

        byte[] bytes = strb.toString().getBytes();
        byte[] checkCode = CheckCode.getCheckCode(bytes.length, bytes);
        cancelOrderEntity.setCheckCode(StringUtil.toHexString(checkCode));


        return cancelOrderEntity;
    }

    /**
     * 取消订单sign
     * @param baseDataPay
     * @param cancelOrderEntity
     * @return
     */
    public String getsign(BaseDataPay baseDataPay, CancelOrderEntity cancelOrderEntity) {
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
        if(StringUtils.isNotNullBlank(cancelOrderEntity.getOrderNo()))
        strb.append("&orderNo=" + cancelOrderEntity.getOrderNo());

        if(StringUtils.isNotNullBlank(cancelOrderEntity.getPayOrderNo()))
            strb.append("&payOrderNo=" + cancelOrderEntity.getPayOrderNo());

        if(StringUtils.isNotNullBlank(cancelOrderEntity.getCancelReason()))
            strb.append("&cancelReason=" + cancelOrderEntity.getCancelReason());

        strb.append("&checkCode=" + cancelOrderEntity.getCheckCode());
        strb.append(BaseConfig.secret);

        return strb.toString();
    }


}
