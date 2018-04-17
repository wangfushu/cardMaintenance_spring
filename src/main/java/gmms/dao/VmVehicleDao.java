package gmms.dao;

import gmms.domain.db.VmVehicle;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface VmVehicleDao extends BaseDao<VmVehicle, String> {

	VmVehicle findByPlateNoAndPlateColorAndVKindNo(String vPlateNo, String vPlateColor, Long vKind);

/*	@Query(value = "select vVehicleNo from VmVehicle cr where vVehicleNo like ? order by vVehicleNo desc")
	List<String> findAllNo(String vVehicleNo);

	List<VmVehicle> findByVPlateNoAndVPlateColorAndVKind(String vPlateNo, String vPlateColor, Long vKind);

	@Query(value="select count(*) from Vm_Vehicle p1 where p1.V_VEHICLENO like ? and p1.V_KIND=? and v_NewCarRegistNode=4 and convert(varchar(7),p1.V_MODIFYTIME,25) =? ",nativeQuery=true)
	Integer findCountVehicle(String vVehicleNo, Long vkind, String date);

	@Query(value="select count(*) from Vm_Vehicle p1 where p1.V_VEHICLENO like ? and p1.V_KIND=? and v_NewCarRegistNode=4 and convert(varchar(10),p1.V_MODIFYTIME,25) =? ",nativeQuery=true)
	Integer findCountVehicleToday(String vVehicleNo, Long vkind, String date);



	@Query(value="select count(*) from VmVehicle p1 where vVehicleNo like ? and( vNewCarRegistNode=? or vAuditState=? ) ")
	Long findCountVehicleByAndvVehicleNoOrvNewCarRegistNodeOrvAuditState(String vVehicleNo, Long vNewCarRegistNode, Long vAuditState);*/



}
