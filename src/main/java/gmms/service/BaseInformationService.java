package gmms.service;

import com.google.common.collect.Lists;
import gmms.dao.RoleDao;
import gmms.dao.SysPlazaDao;
import gmms.dao.UsersDao;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.Role;
import gmms.domain.db.SysPlaza;
import gmms.domain.db.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<SysPlaza> sysPlazaListAll(){
        List<SearchFilter> filters = Lists.newArrayList();
        Specification<SysPlaza> spec = DynamicSpecifications.bySearchFilter(filters, SysPlaza.class);
        List<SysPlaza> sysPlazas= sysPlazaDao.findAll(spec,new Sort(Sort.Direction.ASC,"plaModifyTime"));
        return sysPlazas;
    }

    public List<SysPlaza> listAllPlaza() {
        return Lists.newArrayList(sysPlazaDao.findAll());
    }



    public void delete(Users operatorUser, SysPlaza sysPlaza) {
        if (sysPlaza == null) {
            return;
        }
        LOGGER.info("sysPlaza {} has delete,operator user id is {},name is ", sysPlaza, operatorUser.getId(), operatorUser.getUserName());
        sysPlazaDao.delete(sysPlaza);
    }

    public void delete(Users operatorUser, List<SysPlaza> sysPlazaList) {
        if (sysPlazaList == null) {
            return;
        }
        LOGGER.info("sysPlazaList {} has delete,operator user id is {},name is ", sysPlazaList, operatorUser.getId(), operatorUser.getUserName());
        sysPlazaDao.delete(sysPlazaList);
    }

}
