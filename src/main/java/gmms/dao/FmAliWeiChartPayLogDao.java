package gmms.dao;



import gmms.domain.db.FmAliWeiChartPayLog;

import java.util.List;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface FmAliWeiChartPayLogDao extends BaseDao<FmAliWeiChartPayLog, Long> {

    FmAliWeiChartPayLog findByOrderNoAndPayOrderNo(String orderNo, String payOrderNo);
    FmAliWeiChartPayLog findByOrderNo(String orderNo);

    List<FmAliWeiChartPayLog> findByVehicleNo(String vehicleNo);

}
