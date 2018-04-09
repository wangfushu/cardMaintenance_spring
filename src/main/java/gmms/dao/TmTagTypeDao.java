package gmms.dao;

import gmms.domain.db.TmTagType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface TmTagTypeDao extends BaseDao<TmTagType, Long> {

    TmTagType findByTagType(String tagType);

}
