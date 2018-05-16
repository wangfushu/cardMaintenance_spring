package gmms.dao;

import gmms.domain.db.SysBaseInformation;
import gmms.domain.db.SysBaseInformationPK;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface SysBaseInformationDao extends BaseDao<SysBaseInformation, SysBaseInformationPK> {
	SysBaseInformation findByBiTypeAndBiValue(String biType, String biValue);

	@Query(value = "from SysBaseInformation cr where biType = ? order by sort desc")
	List<SysBaseInformation> querybybiType(String biType);
}
