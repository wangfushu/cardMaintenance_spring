package gmms.dao;



import gmms.domain.db.SysPlaza;
import gmms.domain.db.Users;

import java.util.List;

/**
 * Created by yangjb on 2017/9/21.
 * hello
 */
public interface UsersDao extends BaseDao<Users, Long> {
    public Users findByUserNo(String userNo);

    public  List<Users> findBySysPlaza(SysPlaza sysPlaza);

    List<Users> findByTelphone(String telphone);

}
