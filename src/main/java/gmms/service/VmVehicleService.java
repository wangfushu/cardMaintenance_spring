package gmms.service;


import com.google.common.collect.Lists;
import gmms.dao.VmVehicleDao;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.Users;
import gmms.domain.db.VmVehicle;
import gmms.domain.form.VmVehicleForm;
import gmms.domain.query.VmVehicleQueryParam;
import gmms.util.DateUtil;
import gmms.util.DomainCopyUtil;
import gmms.util.StringUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangfs on 2017/6/28. helloWorld
 */
@Service
public class VmVehicleService {
    private Logger LOGGER = LoggerFactory.getLogger(VmVehicleService.class);

    @Autowired
    private VmVehicleDao vmVehicleDao;


    @PersistenceUnit
    private EntityManagerFactory emf;

    public VmVehicle findById(String vVehicleNo){
        return vmVehicleDao.findOne(vVehicleNo);
    }

    public VmVehicle queryVmVehicleIsExist(String plateNo, String plateColor, Long vKindNo){
        return  vmVehicleDao.findByPlateNoAndPlateColorAndVKindNo(plateNo,plateColor,vKindNo);
    }

    public VmVehicle saveOrupdateVmVehicle(VmVehicleForm vmVehicleForm, Users users) {
        VmVehicle vmVehicle = DomainCopyUtil.map(vmVehicleForm, VmVehicle.class);
        VmVehicle oldVmVehicle = vmVehicleDao.findOne(vmVehicle.getVehicleNo());
        if(null == oldVmVehicle){
            //add
            vmVehicle.setUserId(users.getId());
            vmVehicle.setUserNo(users.getUserNo());
            vmVehicle.setUserName(users.getUserName());
            vmVehicle.setUpdateTime(new Date());
            vmVehicle.setBornSource(1L);
            return vmVehicleDao.save(vmVehicle);
        }else{
            if (StringUtils.isNotNullBlank( vmVehicle.getPlateNo()))
                oldVmVehicle.setPlateNo(vmVehicle.getPlateNo());
            if (StringUtils.isNotNullBlank( vmVehicle.getPlateColor()))
                oldVmVehicle.setPlateColor(vmVehicle.getPlateColor());
            if (null!=vmVehicle.getvKindNo())
                oldVmVehicle.setvKindNo(vmVehicle.getvKindNo());
            if (null!=vmVehicle.getSpec())
                oldVmVehicle.setSpec(vmVehicle.getSpec());
            if (null!=vmVehicle.getvRegDate())
                oldVmVehicle.setvRegDate(vmVehicle.getvRegDate());


            if (StringUtils.isNotNullBlank( vmVehicle.getCustName()))
                oldVmVehicle.setCustName(vmVehicle.getCustName());

            if (StringUtils.isNotNullBlank( vmVehicle.getHandleName()))
                oldVmVehicle.setHandleName(vmVehicle.getHandleName());

            if (StringUtils.isNotNullBlank( vmVehicle.getiDCard()))
                oldVmVehicle.setiDCard(vmVehicle.getiDCard());

            if (StringUtils.isNotNullBlank( vmVehicle.getIncName()))
                oldVmVehicle.setIncName(vmVehicle.getIncName());

            if (StringUtils.isNotNullBlank( vmVehicle.getFax()))
                oldVmVehicle.setFax(vmVehicle.getFax());

            if (StringUtils.isNotNullBlank( vmVehicle.getPhone()))
                oldVmVehicle.setPhone(vmVehicle.getPhone());

            if (StringUtils.isNotNullBlank( vmVehicle.getAddress()))
                oldVmVehicle.setAddress(vmVehicle.getAddress());

            if (StringUtils.isNotNullBlank( vmVehicle.getZip()))
                oldVmVehicle.setZip(vmVehicle.getZip());

            if (StringUtils.isNotNullBlank( vmVehicle.getEmail()))
                oldVmVehicle.setEmail(vmVehicle.getEmail());

            if (StringUtils.isNotNullBlank( vmVehicle.getZip()))
                oldVmVehicle.setZip(vmVehicle.getZip());

            if (StringUtils.isNotNullBlank( vmVehicle.getaMemo()))
                oldVmVehicle.setaMemo(vmVehicle.getaMemo());

            if (null!= vmVehicle.getNewCarRegistNode())
                oldVmVehicle.setNewCarRegistNode(vmVehicle.getNewCarRegistNode());

            if (StringUtils.isNotNullBlank( vmVehicle.getPictureDirectory()))
                oldVmVehicle.setPictureDirectory(vmVehicle.getPictureDirectory());
            if (StringUtils.isNotNullBlank( vmVehicle.getImageDirectory()))
                oldVmVehicle.setImageDirectory(vmVehicle.getImageDirectory());
            return vmVehicleDao.save(oldVmVehicle);
        }
        //return null;
    }

    /**
     * 手机端车辆注册信息报表
     *
     * @param queryParam
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<VmVehicle> androidlistAll(VmVehicleQueryParam queryParam, Users users, int pageNo, int pageSize) {

        List<SearchFilter> filters = Lists.newArrayList();

        if (null != queryParam.getPlateNo() && !"".equals(queryParam.getPlateNo())) {
            filters.add(new SearchFilter("plateNo", SearchFilter.Operator.LIKE, queryParam.getPlateNo()));
        }
        if (null != queryParam.getPlateColor() && !"".equals(queryParam.getPlateColor())) {
            filters.add(new SearchFilter("plateColor", SearchFilter.Operator.EQ, queryParam.getPlateColor()));

        }
        if (null != queryParam.getvKindNo() && !"".equals(String.valueOf(queryParam.getvKindNo()))) {
            filters.add(new SearchFilter("vKindNo", SearchFilter.Operator.EQ, queryParam.getvKindNo()));
        }

        if (null != users) {

                 filters.add(new SearchFilter("vVehicleNo", SearchFilter.Operator.TLIKE, StringUtils.getFormat(5,users.getSysPlaza().getPlaNo().intValue()) ));
        }

        if (null != queryParam.getStartPassTime() && !"".equals(queryParam.getStartPassTime())) {
            filters.add(new SearchFilter("updateTime", SearchFilter.Operator.GTE, DateUtil.getStartDate(DateUtil.fromDateStringToYMDDate(queryParam.getStartPassTime()))));
        }

        if (null != queryParam.getEndPassTime() && !"".equals(queryParam.getEndPassTime())) {
            filters.add(new SearchFilter("updateTime", SearchFilter.Operator.LTE, DateUtil.getEndDate(DateUtil.fromDateStringToYMDDate(queryParam.getEndPassTime()))));
        }

        if (null != queryParam.getvNewCarRegistNode() && !"".equals(queryParam.getvNewCarRegistNode())) {

            filters.add(new SearchFilter("newCarRegistNode", SearchFilter.Operator.EQ, Long.valueOf(queryParam.getvNewCarRegistNode())));
        }
        Specification<VmVehicle> spec = DynamicSpecifications.bySearchFilter(filters, VmVehicle.class);

        PageRequest page = new PageRequest(pageNo - 1, pageSize, new Sort(Sort.Direction.DESC, "updateTime"));
        Page<VmVehicle> vmVehicles = vmVehicleDao.findAll(spec, page);
        return vmVehicles;
    }



    /*********************************************数据库操作*********************************************************************/

    private static final Map<String, IdPool> ID_MAP = new ConcurrentHashMap<String, IdPool>();
    private int poosize=2;
    public synchronized String generate( String serialNumber,String objString) throws HibernateException {

        // String dayStr= DateUtils.getCurrDateStr(1);
        String entityName = objString+serialNumber;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("需要获取主键的实体名:[" + entityName + "]");
        }
        if (ID_MAP.get(entityName) == null) {
            initalize(entityName);
        }
        String id = serialNumber+ID_MAP.get(entityName).getNextId();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("线程:[" + Thread.currentThread().getName() + "] " +
                    "实体:[" + entityName + "] 生成的主键为:[" + id + "]");
        }
        return id;
    }
    /**
     * 初始化对象的主键
     *
     * @param entityName 对象名称
     */
    private void initalize(String entityName) {
        IdPool pool = new IdPool(entityName, poosize);
        ID_MAP.put(entityName, pool);
    }

/*********************************************************************************************************************/
}
