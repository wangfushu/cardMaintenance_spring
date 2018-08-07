package gmms.unifiedPay;

import com.alibaba.fastjson.JSONObject;
import gmms.unifiedPay.payConfig.BaseConfig;
import gmms.unifiedPay.payEntity.BaseDataPay;
import gmms.unifiedPay.payEntity.QueryOrderPay;
import gmms.unifiedPay.payEntity.UnifiedPay;
import gmms.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wangfs on 2017-10-23 helloword.
 */
public class RbiQueryPayOrder {
    private Logger LOGGER = LoggerFactory.getLogger(RbiQueryPayOrder.class);
   // private String rbi_query_url = "";//测试库
    //private String rbi_query_url = "";//测试库（内网）

    //private String rbi_query_url = "";//测试库
     private String rbi_query_url="";//正式库

    public String queryOrder(String orderNO,String payOrderNo) throws Exception {
        BaseDataPay baseDataPay = initBaseDataPay();
        QueryOrderPay queryOrderPay = initQueryOrderPay(orderNO,payOrderNo);
        String sign = getsign(baseDataPay,queryOrderPay);
       /* System.out.println("=======================sign  : "+sign);*/
        baseDataPay.setSign(sign);


        TreeMap<String, Object> params = new TreeMap<String, Object>();
        Map<String, Object> baseDataPayparams = ObjectUtil.objectToMap(baseDataPay);
        Map<String, Object> queryOrderPayparams = ObjectUtil.objectToMap(queryOrderPay);
        params.putAll(baseDataPayparams);
        params.putAll(queryOrderPayparams);

        String data = JSONObject.toJSONString(params, true);
        System.out.println("=======================json串1  : "+data);
        String result = "";
        result = WebUtil.postJson(rbi_query_url, data);
        LOGGER.info(" WeiXinALI queryOrder Result : "+ result);
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
     * 查询订单的初始参数
     * @param orderNO
     * @param payOrderNo
     * @return
     */
    public QueryOrderPay initQueryOrderPay(String orderNO,String payOrderNo) {
        QueryOrderPay queryOrderPay = new QueryOrderPay();
        queryOrderPay.setOrderNo(orderNO);
        queryOrderPay.setPayOrderNo(payOrderNo);

        return queryOrderPay;
    }

    /**
     * 获取支付二维码sign
     * @param baseDataPay
     * @param queryOrderPay
     * @return
     */
    public String getsign(BaseDataPay baseDataPay, QueryOrderPay queryOrderPay) {
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

        if(StringUtils.isNotNullBlank(queryOrderPay.getOrderNo()))
             strb.append("&orderNo=" + queryOrderPay.getOrderNo());
        if(StringUtils.isNotNullBlank(queryOrderPay.getPayOrderNo()))
            strb.append("&payOrderNo=" + queryOrderPay.getPayOrderNo());

        strb.append(BaseConfig.secret);

        return strb.toString();
    }
}
