package gmms.dao;



import gmms.domain.db.Users;

import java.util.List;

/**
 * Created by yangjb on 2017/9/21.
 * hello
 */
public interface UsersDao extends BaseDao<Users, Long> {
    public Users findByUserNo(String userNo);

    List<Users> findByTelphone(String telphone);

}
