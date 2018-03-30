package gmms.service;

import com.google.common.collect.Lists;
import gmms.dao.RoleDao;
import gmms.dao.UsersDao;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.Role;
import gmms.domain.db.Users;
import gmms.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangjb on 2017/7/16.
 * helloWorld
 */
@Service
public class UsersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private RoleDao roleDao;
/*    @Value("${ui_real_image_path}")
    private String uiRealPath;*/

/*    public List<Role> listAllRole() {
        return Lists.newArrayList(roleDao.findAll());
    }

    public Role getByRoleId(long id) {
        return roleDao.findOne(id);
    }

    public Users saveOrUpdate(Users users) {
        return usersDao.save(users);
    }*/

  /*  public List<Users> listAllUser(UserParam userParam) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (userParam.getTimefromFormat() != null) {
            if (userParam.getTimeType() == 0) {
                filters.add(new SearchFilter("lastLoginTime", SearchFilter.Operator.GTE, userParam.getTimefromFormat()));
            } else {
                filters.add(new SearchFilter("gmtCreate", SearchFilter.Operator.GTE, userParam.getTimefromFormat()));
            }

        }
        if (userParam.getTimetoFormat() != null) {
            if (userParam.getTimeType() == 0) {
                filters.add(new SearchFilter("lastLoginTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(userParam.getTimetoFormat()))));
            } else {
                filters.add(new SearchFilter("gmtCreate", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(userParam.getTimetoFormat()))));
            }
        }

        Specification<Users> spec = DynamicSpecifications.bySearchFilter(filters, Users.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "gmtCreate");
        List<Users> allUsers = usersDao.findAll(spec, purchaseDateDB);
        if (userParam.getRoleId() != null && userParam.getRoleId() != -1) {
            Role role = roleDao.findOne(userParam.getRoleId());
            Iterator<Users> iterator = allUsers.iterator();
            while (iterator.hasNext()) {
                Users next = iterator.next();
                if (!next.getRoles().contains(role)) {
                    iterator.remove();
                }
            }
        }
        return allUsers;
    }*/

   /* public List<Users> listUiUser() {
        Role uiRole = roleDao.findOne(4L);
        return uiRole.getUsers();
    }*/

    /**
     * 获取所有客户
     *
     * @return
     */
   /* public List<Users> listUser() {
        Role uiRole = roleDao.findOne(3L);
        return uiRole.getUsers();
    }*/

   /* public Users getById(long id) {
        return usersDao.findOne(id);
    }



    public List<Users> findByTelphone(String telphone) {
        return usersDao.findByTelphone(telphone);
    }*/

   /* public void delete(Users operatorUser, Users deleteUser) {
        if (deleteUser == null) {
            return;
        }
        LOGGER.info("users {} has delete,operator user id is {},name is ", deleteUser, operatorUser.getId(), operatorUser.getRealName());
        usersDao.delete(deleteUser);
    }

    public void delete(Users operatorUser, List<Users> deleteUserList) {
        if (deleteUserList == null) {
            return;
        }
        LOGGER.info("users {} has delete,operator user id is {},name is ", deleteUserList, operatorUser.getId(), operatorUser.getRealName());
        usersDao.delete(deleteUserList);
    }*/
    public Users findByName(String name) {
        return usersDao.findByUserName(name);
    }
}
