package gmms.dao;

import gmms.domain.db.FmFee;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface FmFeeDao extends BaseDao<FmFee, String> {
	//VmVehicle findByVVehicleNo(String vVehicleNo);

	@Query(value = "select max(fId) from FmFee cr where fId like ?")
	String findAllNo(String fId);

	/*List<Fee> findByFVehicleNoAndFFeeYear(String vehicleNo, Long fFeeYear);

	List<FmFee> findByFVehicleNo(String vehicleNo);
	Fee findByFVehicleNoAndFFeeYearAndFFeeReasonAndFIsCancel(String vehicleNo, Long fFeeYear, Long fFeeReason, Long fIsCancel);

	Fee findByFId(String fId);

	@Query(value = "select sum(fSubmitFee) from FmFee cr where  fId like ? and fBillPrintTime  Between ? and ?")
	Double findTodaySumFee(String fid, Date startDate, Date endDate);

	@Query(value = "select sum(fSubmitFee) from FmFee cr where fId like ?")
	Double findSumFee(String fid);
*/


}
