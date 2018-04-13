package gmms.dao;

import gmms.domain.db.SysPlaza;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface SysPlazaDao extends BaseDao<SysPlaza, Long> {
	SysPlaza findByPlaNo(Long plaNo);
}
