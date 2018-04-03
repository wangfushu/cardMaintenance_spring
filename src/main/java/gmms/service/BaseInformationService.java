package gmms.service;

import gmms.dao.RoleDao;
import gmms.dao.SysPlazaDao;
import gmms.dao.UsersDao;
import gmms.domain.db.SysPlaza;
import gmms.domain.db.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangfs on 2018-04-02 helloword.
 */
@Service
public class BaseInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInformationService.class);
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SysPlazaDao sysPlazaDao;

    public SysPlaza saveOrUpdate(SysPlaza sysPlaza) {
        return sysPlazaDao.save(sysPlaza);
    }
    public SysPlaza findSysPlaza(String  plazaNo) {
        return sysPlazaDao.findByPlaNo(plazaNo);
    }
}
