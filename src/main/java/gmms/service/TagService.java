package gmms.service;


import com.google.common.collect.Lists;
import gmms.dao.*;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.*;
import gmms.domain.param.TmTagStoreParam;
import gmms.util.DateUtil;
import gmms.util.StringUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfs on 2018-02-23 helloword.
 */
@Service
public class TagService {
    @Autowired
    private TmTagTypeDao tmTagTypeDao;

    @Autowired
    private TmTagStoreDao tmTagStoreDao;

    @Autowired
    private TmTagInStoreDao tmTagInStoreDao;

    @Autowired
    private TmTagOutStoreDao tmTagOutStoreDao;
    @Autowired
    private SysBaseInformationDao sysBaseInformationDao;


    @PersistenceUnit
    private EntityManagerFactory emf;


   public List<TmTagType> listAllTagType(){
       return Lists.newArrayList(tmTagTypeDao.findAll());
   }

    public TmTagType getbyTypeId(Long typeId){
        return tmTagTypeDao.findOne(typeId);
    }

    public TmTagType findByName(String name) {
        return tmTagTypeDao.findByTagType(name);
    }

    public TmTagType saveOrUpdateTmTagType(TmTagType tmTagType){
        return tmTagTypeDao.save(tmTagType);
    }


    public List<TmTagType> TagTypelistAll(){
        List<SearchFilter> filters = Lists.newArrayList();
        filters.add(new SearchFilter("inUse", SearchFilter.Operator.EQ,1l));
        Specification<TmTagType> spec = DynamicSpecifications.bySearchFilter(filters, TmTagType.class);
        List<TmTagType> tmTagTypeList= tmTagTypeDao.findAll(spec,new Sort(Sort.Direction.ASC,"typeId"));
        return tmTagTypeList;
    }

    public List<TmTagStore> listAllTmTagStore(TmTagStoreParam tmTagStoreParam){
        List<SearchFilter> filters = Lists.newArrayList();
        if (tmTagStoreParam.getTimefromFormat() != null) {
                filters.add(new SearchFilter("updateTime", SearchFilter.Operator.GTE, tmTagStoreParam.getTimefromFormat()));
        }
        if (tmTagStoreParam.getTimetoFormat() != null) {
                filters.add(new SearchFilter("updateTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(tmTagStoreParam.getTimetoFormat()))));
        }
        if (tmTagStoreParam.getTypeId() != null&&tmTagStoreParam.getTypeId()!=-1) {
            filters.add(new SearchFilter("tagType.typeId", SearchFilter.Operator.EQ,  tmTagStoreParam.getTypeId()));
        }

        if (tmTagStoreParam.getPlaNo() != null&&!tmTagStoreParam.getPlaNo().equals(0l)) {
            filters.add(new SearchFilter("plazaNo", SearchFilter.Operator.EQ, tmTagStoreParam.getPlaNo()));
        }
        Specification<TmTagStore> spec = DynamicSpecifications.bySearchFilter(filters, TmTagStore.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "updateTime");
        List<TmTagStore> allTmTagStore = tmTagStoreDao.findAll(spec, purchaseDateDB);
        return allTmTagStore;
    }


    public List<SysBaseInformation> findInStoreTypeByValue(){
        return sysBaseInformationDao.querybybiType("TagInStoreType");
    }


    @Transactional(rollbackFor = Exception.class)
    public TmTagInStore inStoreTagAll(TmTagInStore tmTagInStore){
        TmTagStore tmTagStore=null;
        TmTagInStore inStore=tmTagInStoreDao.save(tmTagInStore);
        tmTagStore= tmTagStoreDao.findOne(inStore.getInRecievedPlazaNo());
        if(null!= tmTagStore&& !StringUtil.isEmpty(String.valueOf(tmTagStore.getPlazaNo()))){
            //update
            if(inStore.getInStoreType().equals(1L)) {
                tmTagStore.setGoodTagCount(tmTagStore.getGoodTagCount()+inStore.getCount());
            }else{
                TmTagStore sendtmTagStore= tmTagStoreDao.findOne(inStore.getInSendPlazaNo());//发送方库存扣除
                sendtmTagStore.setGoodTagCount(sendtmTagStore.getGoodTagCount()-inStore.getCount());
                sendtmTagStore.setBadTagCount(sendtmTagStore.getBadTagCount() + inStore.getCount());
                tmTagStoreDao.save(sendtmTagStore);

                tmTagStore.setBadTagCount(tmTagStore.getBadTagCount()+inStore.getCount());
            }
            tmTagStore.setUpdateTime(new Date());
        }else{
            //add
            tmTagStore=new TmTagStore();
            tmTagStore.setPlazaNo(inStore.getInRecievedPlazaNo());
            tmTagStore.setPlazaName(inStore.getInRecievedPlazaName());
            if(inStore.getInStoreType().equals(1L)) {
                tmTagStore.setGoodTagCount(inStore.getCount());
            }else{
                TmTagStore sendtmTagStore= tmTagStoreDao.findOne(inStore.getInSendPlazaNo());//发送方库存扣除
                sendtmTagStore.setGoodTagCount(sendtmTagStore.getGoodTagCount()-inStore.getCount());
                sendtmTagStore.setBadTagCount(sendtmTagStore.getBadTagCount()+inStore.getCount());
                tmTagStoreDao.save(sendtmTagStore);

                tmTagStore.setBadTagCount(inStore.getCount());
            }
            tmTagStore.setUpdateTime(new Date());
        }
        TmTagStore tmTagStoreSave=tmTagStoreDao.save(tmTagStore);
        return inStore;
    }

    @Transactional(rollbackFor = Exception.class)
    public TmTagOutStore outStoreTagAll(TmTagOutStore tmTagOutStore){
        TmTagStore tmTagStore=null;
        TmTagOutStore outStore=tmTagOutStoreDao.save(tmTagOutStore);
        tmTagStore= tmTagStoreDao.findOne(outStore.getOutRecievedPlazaNo());
        if(null!= tmTagStore&& !StringUtil.isEmpty(String.valueOf(tmTagStore.getPlazaNo()))){
            //update
            tmTagStore.setGoodTagCount(tmTagStore.getGoodTagCount()+outStore.getCount());

            TmTagStore sendtmTagStore= tmTagStoreDao.findOne(outStore.getOutSendPlazaNo());//发送方库存扣除
            sendtmTagStore.setGoodTagCount(sendtmTagStore.getGoodTagCount()-outStore.getCount());
            tmTagStoreDao.save(sendtmTagStore);

            tmTagStore.setUpdateTime(new Date());
        }else{
            //add
            tmTagStore=new TmTagStore();
            tmTagStore.setPlazaNo(outStore.getOutRecievedPlazaNo());
            tmTagStore.setPlazaName(outStore.getOutRecievedPlazaName());
            TmTagStore sendtmTagStore= tmTagStoreDao.findOne(outStore.getOutSendPlazaNo());//发送方库存扣除
            sendtmTagStore.setGoodTagCount(sendtmTagStore.getGoodTagCount()-outStore.getCount());
            tmTagStoreDao.save(sendtmTagStore);
            tmTagStore.setGoodTagCount(outStore.getCount());
            tmTagStore.setUpdateTime(new Date());
        }
        TmTagStore tmTagStoreSave=tmTagStoreDao.save(tmTagStore);
        return outStore;
    }


    public TmTagStore findTmTagStoreByPlazaNo(Long plazaNo){
        return tmTagStoreDao.findOne(plazaNo);
    }

}
