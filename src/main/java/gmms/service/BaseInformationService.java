package gmms.service;

import com.google.common.collect.Lists;
import gmms.dao.RoleDao;
import gmms.dao.SysConfigDao;
import gmms.dao.SysPlazaDao;
import gmms.dao.UsersDao;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.*;
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

    @Autowired
    private SysConfigDao sysConfigDao;

    public SysPlaza saveOrUpdateSysPlaza(SysPlaza sysPlaza ,Users operateUsers) {
        LOGGER.info("sysPlaza {} has saveAndUpdate,operator user id is {},name is{} ", sysPlaza, operateUsers.getId(), operateUsers.getUserName());
        return sysPlazaDao.save(sysPlaza);
    }
    public SysPlaza findSysPlaza(Long  plazaNo) {
        return sysPlazaDao.findByPlaNo(plazaNo);
    }


    public List<SysPlaza> sysPlazaListAll(){
        List<SearchFilter> filters = Lists.newArrayList();
        Specification<SysPlaza> spec = DynamicSpecifications.bySearchFilter(filters, SysPlaza.class);
        List<SysPlaza> sysPlazas= sysPlazaDao.findAll(spec,new Sort(Sort.Direction.ASC,"plaModifyTime"));
        return sysPlazas;
    }

    public List<SysPlaza> listAllPlaza() {

        List<SearchFilter> filters = Lists.newArrayList();
        filters.add(new SearchFilter("plaInUse", SearchFilter.Operator.EQ, 0l));
        Specification<SysPlaza> spec = DynamicSpecifications.bySearchFilter(filters, SysPlaza.class);
        List<SysPlaza> sysPlazas= sysPlazaDao.findAll(spec,new Sort(Sort.Direction.ASC,"plaNo"));
        return sysPlazas;
    }



    public void delete(Users operatorUser, SysPlaza sysPlaza) {
        if (sysPlaza == null) {
            return;
        }
        LOGGER.info("sysPlaza {} has delete,operator user id is {},name is{} ", sysPlaza, operatorUser.getId(), operatorUser.getUserName());
        sysPlazaDao.delete(sysPlaza);
    }

    public void delete(Users operatorUser, List<SysPlaza> sysPlazaList) {
        if (sysPlazaList == null) {
            return;
        }
        LOGGER.info("sysPlazaList {} has delete,operator user id is {},name is ", sysPlazaList, operatorUser.getId(), operatorUser.getUserName());
        sysPlazaDao.delete(sysPlazaList);
    }

    /**
     * 质保期
     * @return
     */
    public SysConfig findInSureYearByValue(){
        return sysConfigDao.findByCfConfigName("InSureYear");
    }

    /**
     * 超级密码
     * @return
     */
    public SysConfig findSuperAdminPassWordByValue(){
        return sysConfigDao.findByCfConfigName("SuperAdminPassWord");
    }

    /**
     * 工本费
     * @return
     */
    public SysConfig findCardExpenseByValue(){
        return sysConfigDao.findByCfConfigName("CardExpense");
    }

    public List<SysConfig> SysConfigListAll(){
        return Lists.newArrayList(sysConfigDao.findAll());
    }

    public SysConfig saveOrUpdateSysConfig(SysConfig sysConfig ,Users operatorUser){
        SysConfig save=sysConfigDao.save(sysConfig);
        LOGGER.info("SysConfig {} has update,operator user id is {},name is {} ", sysConfig, operatorUser.getId(), operatorUser.getUserName());
        return null;
    }

    public SysConfig findSysConfigById(String name){
        return sysConfigDao.findOne(name);
    }

}
