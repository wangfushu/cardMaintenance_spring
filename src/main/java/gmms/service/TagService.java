package gmms.service;


import com.google.common.collect.Lists;
import gmms.dao.*;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.*;
import gmms.domain.param.IssueTagYearParam;
import gmms.domain.param.TagInStoreParam;
import gmms.domain.param.TagOutStoreParam;
import gmms.domain.param.TmTagStoreParam;
import gmms.domain.query.IssueTagYearQueryForm;
import gmms.domain.vo.IssueTagExcelVO;
import gmms.domain.vo.TagInStoreExcelVO;
import gmms.domain.vo.TagOutStoreExcelVO;
import gmms.util.DateUtil;
import gmms.util.DateUtils;
import gmms.util.ExcelUtil;
import gmms.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfs on 2018-02-23 helloword.
 */
@Service
public class TagService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagService.class);
    @Autowired
    private TmTagTypeDao tmTagTypeDao;

    @Autowired
    private TmTagStoreDao tmTagStoreDao;

    @Autowired
    private TmTagInStoreDao tmTagInStoreDao;

    @Autowired
    private IssueTagDao issueTagDao;
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

    public Page<TmTagStore> listAllTmTagStorePage(TmTagStoreParam tmTagStoreParam,int pageNo,int pageSize){
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
        PageRequest page = new PageRequest(pageNo - 1, pageSize, purchaseDateDB);
        Page<TmTagStore> allTmTagStore = tmTagStoreDao.findAll(spec, page);
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

/**************************************************************  入库记录报表   ********************************************************************************************/
    public Page<TmTagInStore> TmTagInStorelistAllPage(TagInStoreParam param, int pageNo, int pageSize) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("inStoreTime", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("inStoreTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        Specification<TmTagInStore> spec = DynamicSpecifications.bySearchFilter(filters, TmTagInStore.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "inStoreTime");
        PageRequest page = new PageRequest(pageNo - 1, pageSize, purchaseDateDB);
        return tmTagInStoreDao.findAll(spec, page);
    }

    public List<TmTagInStore> TmTagInStorelistAll(TagInStoreParam param) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("inStoreTime", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("inStoreTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        Specification<TmTagInStore> spec = DynamicSpecifications.bySearchFilter(filters, TmTagInStore.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "inStoreTime");
        return tmTagInStoreDao.findAll(spec);
    }

    public byte[] getTmTagInStoreExcel(List<TmTagInStore> tmTagInStores, String fileName, String[] Title) {
        if (CollectionUtils.isEmpty(tmTagInStores)) {
            return null;
        }
        List<TagInStoreExcelVO> orderExcelVOS = turn2ExcelVo(tmTagInStores);
        return ExcelUtil.exportTagInStoreReportExcel(orderExcelVOS, fileName, Title);
    }
    private List<TagInStoreExcelVO> turn2ExcelVo(List<TmTagInStore> data) {
        List<TagInStoreExcelVO> resultList = Lists.newArrayList();
        for (TmTagInStore tmTagInStore : data) {
            TagInStoreExcelVO tagInStoreExcelVO = new TagInStoreExcelVO();
            if(tmTagInStore.getIsNewCardInStore()){
                tagInStoreExcelVO.setInStoreType("新卡入库");
            }else{
                tagInStoreExcelVO.setInStoreType("故障卡入库");
            }
            tagInStoreExcelVO.setCount(tmTagInStore.getCount());
            tagInStoreExcelVO.setInSendPlazaName(tmTagInStore.getInSendPlazaName());
            tagInStoreExcelVO.setInRecievedPlazaName(tmTagInStore.getInRecievedPlazaName());

            tagInStoreExcelVO.setRemark(tmTagInStore.getRemark());
            tagInStoreExcelVO.setUserName(tmTagInStore.getUserName());

            tagInStoreExcelVO.setInStoreTime(DateUtil.formatDate(tmTagInStore.getInStoreTime(), "yyyy-MM-dd"));
            resultList.add(tagInStoreExcelVO);
        }
        return resultList;
    }



    /**************************************************************  出库记录报表   ********************************************************************************************/
    public Page<TmTagOutStore> TmTagOutStorelistAllPage(TagOutStoreParam param, int pageNo, int pageSize) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L)){
            filters.add(new SearchFilter("outRecievedPlazaNo", SearchFilter.Operator.EQ,param.getPlaNo()));
        }
        Specification<TmTagOutStore> spec = DynamicSpecifications.bySearchFilter(filters, TmTagOutStore.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "outStoreTime");
        PageRequest page = new PageRequest(pageNo - 1, pageSize, purchaseDateDB);
        return tmTagOutStoreDao.findAll(spec, page);
    }

    public List<TmTagOutStore> TmTagOutStorelistAll(TagOutStoreParam param) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L)){
            filters.add(new SearchFilter("outRecievedPlazaNo", SearchFilter.Operator.EQ,param.getPlaNo()));
        }
        Specification<TmTagOutStore> spec = DynamicSpecifications.bySearchFilter(filters, TmTagOutStore.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "outStoreTime");
        return tmTagOutStoreDao.findAll(spec);
    }

    public byte[] getTmTagOutStoreExcel(List<TmTagOutStore> tmTagOutStores, String fileName, String[] Title) {
        if (CollectionUtils.isEmpty(tmTagOutStores)) {
            return null;
        }
        List<TagOutStoreExcelVO> tagInStoreExcelVOs = turnTmOutStore2ExcelVo(tmTagOutStores);
        return ExcelUtil.exportTagOutStoreReportExcel(tagInStoreExcelVOs, fileName, Title);
    }
    private List<TagOutStoreExcelVO> turnTmOutStore2ExcelVo(List<TmTagOutStore> data) {
        List<TagOutStoreExcelVO> resultList = Lists.newArrayList();
        for (TmTagOutStore tmTagOutStore : data) {
            TagOutStoreExcelVO tagOutStoreExcelVO = new TagOutStoreExcelVO();
            tagOutStoreExcelVO.setCount(tmTagOutStore.getCount());
            tagOutStoreExcelVO.setOutSendPlazaName(tmTagOutStore.getOutSendPlazaName());
            tagOutStoreExcelVO.setOutRecievedPlazaName(tmTagOutStore.getOutRecievedPlazaName());

            tagOutStoreExcelVO.setRemark(tmTagOutStore.getRemark());
            tagOutStoreExcelVO.setUserName(tmTagOutStore.getUserName());

            tagOutStoreExcelVO.setOutStoreTime(DateUtil.formatDate(tmTagOutStore.getOutStoreTime(), "yyyy-MM-dd"));
            resultList.add(tagOutStoreExcelVO);
        }
        return resultList;
    }

    /**************************************************************  发卡信息记录报表  year  ********************************************************************************************/
    public  List<IssueTagYearQueryForm> IssueTagYearlistAllPage(IssueTagYearParam param,Users users ,int pageNo, int pageSize) {
      /*  List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        Specification<IssueTag> spec = DynamicSpecifications.bySearchFilter(filters, IssueTag.class);*/
        //Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "outStoreTime");
        /*PageRequest page = new PageRequest(pageNo - 1, pageSize);
        return  issueTagDao.findAll(users.getId(), "2018", page);*/


        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT TOP "+pageSize+" *  " +
                "FROM   " +
                "    (select  ROW_NUMBER() OVER (ORDER BY convert(char(10),t.installDate,120)) AS RowNumber, convert(char(10),t.installDate,120) 统计日期,t.userNo 发卡账号,t.userName 发卡姓名,t.plazaName 网点名称," +
                "(select count(*) from IssueTag temp where temp.installType = 1 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 新发卡数量  ," +
                "(select count(*) from IssueTag temp where temp.installType = 2 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保内换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 3 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保外换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 4 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 人为损坏换卡数量," +
                "count(*) 总数量, SUM(fee) 实收金额" +
                " from IssueTag t  where  " ;
        String conditions="";
        if(users.getIsUser()){
            conditions=" userId="+users.getId();
        }
        if(!StringUtil.isEmpty(param.getYear())){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" convert(char(4),t.installDate,120)='"+param.getYear()+"' ";
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L) ){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.plazaNo = ' "+ param.getPlaNo()+" ' ";
        }
        sql+=conditions;
        sql+= " group by convert(char(10),t.installDate,120),t.userNo,t.userName,t.plazaNo,t.plazaName  )   as A  " +
               "WHERE RowNumber > "+pageSize+"*("+pageNo+"-1)    ";
        //创建原生SQL查询QUERY实例
        Query query = em.createNativeQuery(sql);
        //执行查询，返回的是对象数组(Object[])列表,
        //每一个对象数组存的是相应的实体属性
        List<Object[]>  objectList =query.getResultList();
        List<IssueTagYearQueryForm> issueTagYearParamList= Lists.newArrayList();
        for(Object[] obj : objectList){
            IssueTagYearQueryForm issueTagYearQueryForm =new IssueTagYearQueryForm();
            if(null!=obj[1])
            issueTagYearQueryForm.setStatisticDate(String.valueOf(obj[1]));
            if(null!=obj[2])
            issueTagYearQueryForm.setIssueUserNo(String.valueOf(obj[2]));
            if(null!=obj[3])
            issueTagYearQueryForm.setIssueUserName(String.valueOf(obj[3]));
            if(null!=obj[4])
                issueTagYearQueryForm.setPlazaName(String.valueOf(obj[4]));
            if(null!=obj[5])
            issueTagYearQueryForm.setNewCardIssueCount(Integer.valueOf(String.valueOf(obj[5])));
            if(null!=obj[6])
            issueTagYearQueryForm.setInsureCardIssueCount(Integer.valueOf(String.valueOf(obj[6])));
            if(null!=obj[7])
            issueTagYearQueryForm.setOutsureCardIssueCount(Integer.valueOf(String.valueOf(obj[7])));
            if(null!=obj[8])
                issueTagYearQueryForm.setInsureDamageCardIssueCount(Integer.valueOf(String.valueOf(obj[8])));

            if(null!=obj[9])
            issueTagYearQueryForm.setCount(Integer.valueOf(String.valueOf(obj[9])));
            if(null!=obj[10])
            issueTagYearQueryForm.setTotalfee(Double.valueOf(String.valueOf(obj[10])));
            issueTagYearParamList.add(issueTagYearQueryForm);
        }
        em.close();

        return issueTagYearParamList;
    }

    public  Integer IssueTagYearlistSize(IssueTagYearParam param,Users users) {
      /*  List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("outStoreTime", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        Specification<IssueTag> spec = DynamicSpecifications.bySearchFilter(filters, IssueTag.class);*/
        //Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "outStoreTime");
        /*PageRequest page = new PageRequest(pageNo - 1, pageSize);
        return  issueTagDao.findAll(users.getId(), "2018", page);*/


        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT count(*) " +
                "FROM   " +
                "    (select  ROW_NUMBER() OVER (ORDER BY convert(char(10),t.installDate,120)) AS RowNumber, convert(char(10),t.installDate,120) 统计日期,t.userNo 发卡账号,t.userName 发卡姓名,t.plazaName 网点名称," +
                "(select count(*) from IssueTag temp where temp.installType = 1 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 新发卡数量  ," +
                "(select count(*) from IssueTag temp where temp.installType = 2 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保内换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 3 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保外换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 4 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 人为损坏换卡数量," +
                "count(*) 总数量, SUM(fee) 实收金额" +
                " from IssueTag t  where  " ;
        String conditions="";
        if(users.getIsUser()){
            conditions=" userId="+users.getId();
        }
        if(!StringUtil.isEmpty(param.getYear())){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" convert(char(4),t.installDate,120)='"+param.getYear()+"' ";
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L) ){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.plazaNo = ' "+ param.getPlaNo()+" ' ";
        }
        sql+=conditions;
        sql+= " group by convert(char(10),t.installDate,120),t.userNo,t.userName,t.plazaNo,t.plazaName  )   as A  " ;
        //创建原生SQL查询QUERY实例
        Query query = em.createNativeQuery(sql);
        //执行查询，返回的是对象数组(Object[])列表,
        //每一个对象数组存的是相应的实体属性
        List<Object>  objecArraytList = query.getResultList();
        Integer count=0;
        for(Object obj : objecArraytList){
            if(null!=obj)
                count= Integer.valueOf(String.valueOf(obj));
        }
        em.close();

        return count;
    }


    public  List<IssueTagYearQueryForm> IssueTagYearlistAllList(IssueTagYearParam param,Users users) {

        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "select convert(char(10),t.installDate,120) 统计日期,t.userNo 发卡账号,t.userName 发卡姓名,t.plazaName 网点名称," +
                "(select count(*) from IssueTag temp where temp.installType = 1 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 新发卡数量  ," +
                "(select count(*) from IssueTag temp where temp.installType = 2 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保内换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 3 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保外换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 4 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 人为损坏换卡数量," +
                "count(*) 总数量, SUM(fee) 实收金额" +
                " from IssueTag t  where  " ;
        String conditions="";
        if(users.getIsUser()){
            conditions=" userId="+users.getId();
        }
        if(!StringUtil.isEmpty(param.getYear())){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" convert(char(4),t.installDate,120)='"+param.getYear()+"' ";
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L) ){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.plazaNo = ' "+ param.getPlaNo()+" ' ";
        }
        sql+=conditions;
        sql+= " group by convert(char(10),t.installDate,120),t.userNo,t.userName,t.plazaNo,t.plazaName ";
        //创建原生SQL查询QUERY实例
        Query query = em.createNativeQuery(sql);
        //执行查询，返回的是对象数组(Object[])列表,
        //每一个对象数组存的是相应的实体属性
        List<Object[]>  objectList =query.getResultList();
        List<IssueTagYearQueryForm> issueTagYearParamList= Lists.newArrayList();
        for(Object[] obj : objectList){
            IssueTagYearQueryForm issueTagYearQueryForm =new IssueTagYearQueryForm();
            if(null!=obj[0])
                issueTagYearQueryForm.setStatisticDate(String.valueOf(obj[0]));
            if(null!=obj[1])
                issueTagYearQueryForm.setIssueUserNo(String.valueOf(obj[1]));
            if(null!=obj[2])
                issueTagYearQueryForm.setIssueUserName(String.valueOf(obj[2]));
            if(null!=obj[3])
                issueTagYearQueryForm.setPlazaName(String.valueOf(obj[3]));
            if(null!=obj[4])
                issueTagYearQueryForm.setNewCardIssueCount(Integer.valueOf(String.valueOf(obj[4])));
            if(null!=obj[5])
                issueTagYearQueryForm.setInsureCardIssueCount(Integer.valueOf(String.valueOf(obj[5])));
            if(null!=obj[6])
                issueTagYearQueryForm.setOutsureCardIssueCount(Integer.valueOf(String.valueOf(obj[6])));
            if(null!=obj[7])
                issueTagYearQueryForm.setInsureDamageCardIssueCount(Integer.valueOf(String.valueOf(obj[7])));
            if(null!=obj[8])
                issueTagYearQueryForm.setCount(Integer.valueOf(String.valueOf(obj[8])));
            if(null!=obj[9])
                issueTagYearQueryForm.setTotalfee(Double.valueOf(String.valueOf(obj[9])));
            issueTagYearParamList.add(issueTagYearQueryForm);
        }
        em.close();

        return issueTagYearParamList;
    }


    public byte[] getIssueTagYearExcel(List<IssueTagYearQueryForm> issueTagYearQueryForms, String fileName, String[] Title) {
        if (CollectionUtils.isEmpty(issueTagYearQueryForms)) {
            return null;
        }
        return ExcelUtil.exportIssueTagYearReportExcel(issueTagYearQueryForms, fileName, Title);
    }


    /**************************************************************  发卡信息记录报表  Day  ********************************************************************************************/
    public  List<IssueTagYearQueryForm> IssueTagDaylistAllPage(IssueTagYearParam param,Users users,int pageNo, int pageSize) {
        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT TOP "+pageSize+" *  " +
                "FROM   " +
                "    (select  ROW_NUMBER() OVER (ORDER BY convert(char(10),t.installDate,120)) AS RowNumber, convert(char(10),t.installDate,120) 统计日期,t.userNo 发卡账号,t.userName 发卡姓名,t.plazaName 网点名称," +
                "(select count(*) from IssueTag temp where temp.installType = 1 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 新发卡数量  ," +
                "(select count(*) from IssueTag temp where temp.installType = 2 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保内换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 3 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保外换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 4 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 人为损坏换卡数量," +
                "count(*) 总数量, SUM(fee) 实收金额" +
                " from IssueTag t  where  " ;
        String conditions="";
        if(users.getIsUser()){
            conditions=" userId="+users.getId();
        }
        if(null!=param.getTimefrom() ){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.installDate >= '"+param.getTimefrom()+"' ";
        }
        if(null!=param.getTimeto()){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.installDate <= ' "+ DateUtils.getTimeStr(new Date(DateUtil.dayEndnTime(param.getTimetoFormat())), 2)+" ' ";
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L) ){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.plazaNo = ' "+ param.getPlaNo()+" ' ";
        }

        sql+=conditions;
        sql+= " group by convert(char(10),t.installDate,120),t.userNo,t.userName,t.plazaNo,t.plazaName  )   as A  " +
                "WHERE RowNumber > "+pageSize+"*("+pageNo+"-1)    ";
        //创建原生SQL查询QUERY实例
        Query query = em.createNativeQuery(sql);
        //执行查询，返回的是对象数组(Object[])列表,
        //每一个对象数组存的是相应的实体属性
        List<Object[]>  objectList =query.getResultList();
        List<IssueTagYearQueryForm> issueTagYearParamList= Lists.newArrayList();
        for(Object[] obj : objectList){
            IssueTagYearQueryForm issueTagYearQueryForm =new IssueTagYearQueryForm();
            if(null!=obj[1])
                issueTagYearQueryForm.setStatisticDate(String.valueOf(obj[1]));
            if(null!=obj[2])
                issueTagYearQueryForm.setIssueUserNo(String.valueOf(obj[2]));
            if(null!=obj[3])
                issueTagYearQueryForm.setIssueUserName(String.valueOf(obj[3]));
            if(null!=obj[4])
                issueTagYearQueryForm.setPlazaName(String.valueOf(obj[4]));
            if(null!=obj[5])
                issueTagYearQueryForm.setNewCardIssueCount(Integer.valueOf(String.valueOf(obj[5])));
            if(null!=obj[6])
                issueTagYearQueryForm.setInsureCardIssueCount(Integer.valueOf(String.valueOf(obj[6])));
            if(null!=obj[7])
                issueTagYearQueryForm.setOutsureCardIssueCount(Integer.valueOf(String.valueOf(obj[7])));
            if(null!=obj[8])
                issueTagYearQueryForm.setInsureDamageCardIssueCount(Integer.valueOf(String.valueOf(obj[8])));

            if(null!=obj[9])
                issueTagYearQueryForm.setCount(Integer.valueOf(String.valueOf(obj[9])));
            if(null!=obj[10])
                issueTagYearQueryForm.setTotalfee(Double.valueOf(String.valueOf(obj[10])));
            issueTagYearParamList.add(issueTagYearQueryForm);
        }
        em.close();

        return issueTagYearParamList;
    }
    public  Integer IssueTagDaylistSize(IssueTagYearParam param,Users users) {
        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT count(*) " +
                "FROM   " +
                "    (select  ROW_NUMBER() OVER (ORDER BY convert(char(10),t.installDate,120)) AS RowNumber, convert(char(10),t.installDate,120) 统计日期,t.userNo 发卡账号,t.userName 发卡姓名,t.plazaName 网点名称," +
                "(select count(*) from IssueTag temp where temp.installType = 1 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 新发卡数量  ," +
                "(select count(*) from IssueTag temp where temp.installType = 2 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保内换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 3 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保外换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 4 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 人为损坏换卡数量," +
                "count(*) 总数量, SUM(fee) 实收金额" +
                " from IssueTag t  where  " ;
        String conditions="";
        if(users.getIsUser()){
            conditions=" userId="+users.getId();
        }
        if(param.getTimefrom() != null){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.installDate >= '"+param.getTimefrom()+"' ";
        }
        if(param.getTimeto() != null){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.installDate <= ' "+ DateUtils.getTimeStr(new Date(DateUtil.dayEndnTime(param.getTimetoFormat())), 2)+" ' ";
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L) ){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.plazaNo = ' "+ param.getPlaNo()+" ' ";
        }
        sql+=conditions;
        sql+= " group by convert(char(10),t.installDate,120),t.userNo,t.userName,t.plazaNo,t.plazaName  )   as A  " ;

        //创建原生SQL查询QUERY实例
        Query query = em.createNativeQuery(sql);
        //执行查询，返回的是对象数组(Object[])列表,
        //每一个对象数组存的是相应的实体属性
        List<Object>  objecArraytList = query.getResultList();
        Integer count=0;
        for(Object obj : objecArraytList){
            if(null!=obj)
                count= Integer.valueOf(String.valueOf(obj));
        }
        em.close();

        return count;
    }
    public  List<IssueTagYearQueryForm> IssueTagDaylistAllList(IssueTagYearParam param,Users users) {
        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql =  "select convert(char(10),t.installDate,120) 统计日期,t.userNo 发卡账号,t.userName 发卡姓名,t.plazaName 网点名称," +
                "(select count(*) from IssueTag temp where temp.installType = 1 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 新发卡数量  ," +
                "(select count(*) from IssueTag temp where temp.installType = 2 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保内换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 3 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 保外换卡数量," +
                "(select count(*) from IssueTag temp where temp.installType = 4 and  temp.userName=t.userName and  temp.plazaNo=t.plazaNo and temp.plazaName=t.plazaName and convert(char(10),temp.installDate,120)=convert(char(10),t.installDate,120)) 人为损坏换卡数量," +
                "count(*) 总数量, SUM(fee) 实收金额" +
                " from IssueTag t  where  " ;
        String conditions="";
        if(users.getIsUser()){
            conditions=" userId="+users.getId();
        }
        if(param.getTimefrom() != null){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.installDate >= '"+param.getTimefrom()+"' ";
        }
        if(param.getTimeto() != null){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.installDate <= ' "+ DateUtils.getTimeStr(new Date(DateUtil.dayEndnTime(param.getTimetoFormat())), 2)+" ' ";
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L) ){
            if(!StringUtil.isEmpty(conditions)){
                conditions+=" and ";
            }
            conditions+=" t.plazaNo = ' "+ param.getPlaNo()+" ' ";
        }
        sql+=conditions;
        sql+= " group by convert(char(10),t.installDate,120),t.userNo,t.userName,t.plazaNo,t.plazaName ";
        //创建原生SQL查询QUERY实例
        Query query = em.createNativeQuery(sql);
        //执行查询，返回的是对象数组(Object[])列表,
        //每一个对象数组存的是相应的实体属性
        List<Object[]>  objectList =query.getResultList();
        List<IssueTagYearQueryForm> issueTagYearParamList= Lists.newArrayList();
        for(Object[] obj : objectList){
            IssueTagYearQueryForm issueTagYearQueryForm =new IssueTagYearQueryForm();
            if(null!=obj[0])
                issueTagYearQueryForm.setStatisticDate(String.valueOf(obj[0]));
            if(null!=obj[1])
                issueTagYearQueryForm.setIssueUserNo(String.valueOf(obj[1]));
            if(null!=obj[2])
                issueTagYearQueryForm.setIssueUserName(String.valueOf(obj[2]));
            if(null!=obj[3])
                issueTagYearQueryForm.setPlazaName(String.valueOf(obj[3]));
            if(null!=obj[4])
                issueTagYearQueryForm.setNewCardIssueCount(Integer.valueOf(String.valueOf(obj[4])));
            if(null!=obj[5])
                issueTagYearQueryForm.setInsureCardIssueCount(Integer.valueOf(String.valueOf(obj[5])));
            if(null!=obj[6])
                issueTagYearQueryForm.setOutsureCardIssueCount(Integer.valueOf(String.valueOf(obj[6])));
            if(null!=obj[7])
                issueTagYearQueryForm.setInsureDamageCardIssueCount(Integer.valueOf(String.valueOf(obj[7])));
            if(null!=obj[8])
                issueTagYearQueryForm.setCount(Integer.valueOf(String.valueOf(obj[8])));
            if(null!=obj[9])
                issueTagYearQueryForm.setTotalfee(Double.valueOf(String.valueOf(obj[9])));
            issueTagYearParamList.add(issueTagYearQueryForm);
        }
        em.close();

        return issueTagYearParamList;
    }


    public byte[] getIssueTagDayExcel(List<IssueTagYearQueryForm> issueTagYearQueryForms, String fileName, String[] Title) {
        if (CollectionUtils.isEmpty(issueTagYearQueryForms)) {
            return null;
        }
        return ExcelUtil.exportIssueTagYearReportExcel(issueTagYearQueryForms, fileName, Title);
    }

    /**************************************************************  发卡信息记录报表  detail详细报表  ********************************************************************************************/
    public Page<IssueTag> IssueTaglistAllPage(IssueTagYearParam param,Users users, int pageNo, int pageSize) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("installDate", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("installDate", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        if(null!=users&&users.getIsUser()){
            filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, users.getId()));
        }
        if(null!=param.getPlaNo()&&!param.getPlaNo().equals(0L)){
            filters.add(new SearchFilter("plazaNo", SearchFilter.Operator.EQ,  param.getPlaNo()));
        }
        Specification<IssueTag> spec = DynamicSpecifications.bySearchFilter(filters, IssueTag.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "installDate");
        PageRequest page = new PageRequest(pageNo - 1, pageSize, purchaseDateDB);
        return issueTagDao.findAll(spec, page);
    }

    public List<IssueTag> IssueTaglistAll(IssueTagYearParam param,Users users) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (param.getTimefromFormat() != null) {
            filters.add(new SearchFilter("installDate", SearchFilter.Operator.GTE, param.getTimefromFormat()));
        }
        if (param.getTimetoFormat() != null) {
            filters.add(new SearchFilter("installDate", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(param.getTimetoFormat()))));
        }
        if(null!=users&&users.getIsUser()){
            filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, users.getId()));
        }
        if(null!=param.getPlaNo()){
            filters.add(new SearchFilter("plazaNo", SearchFilter.Operator.EQ, param.getPlaNo()));
        }
        Specification<IssueTag> spec = DynamicSpecifications.bySearchFilter(filters, IssueTag.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "installDate");
        return issueTagDao.findAll(spec,purchaseDateDB);
    }

    public byte[] getIssueTagExcel(List<IssueTag> issueTags, String fileName, String[] Title) {
        if (CollectionUtils.isEmpty(issueTags)) {
            return null;
        }
        List<IssueTagExcelVO> issueTagExcelVOs = turn2IssueTagExcelVo(issueTags);
        return ExcelUtil.exportIssueTagReportExcel(issueTagExcelVOs, fileName, Title);
    }
    private List<IssueTagExcelVO> turn2IssueTagExcelVo(List<IssueTag> data) {
        List<IssueTagExcelVO> resultList = Lists.newArrayList();
        for (IssueTag issueTag : data) {
            IssueTagExcelVO issueTagExcelVO = new IssueTagExcelVO();
            issueTagExcelVO.setPlazaName(issueTag.getPlazaName());
            issueTagExcelVO.setUserNo(issueTag.getUserNo());
            issueTagExcelVO.setUserName(issueTag.getUserName());
            issueTagExcelVO.setPlateNo(issueTag.getPlateNo());
            issueTagExcelVO.setPlateColor(issueTag.getPlateColor());
            if(issueTag.getvKindNo().equals(1L)){
                issueTagExcelVO.setvKindNo("客车");
            }else if(issueTag.getvKindNo().equals(2L)){
                issueTagExcelVO.setvKindNo("货车");
            }else{
                issueTagExcelVO.setvKindNo("农用车");
            }
            issueTagExcelVO.setInstallDate(DateUtil.formatDate(issueTag.getInstallDate(), "yyyy-MM-dd HH:mm:ss"));
            if(issueTag.getInstallType().equals(1L)){
                issueTagExcelVO.setInstallType("新卡发放");
            }else if(issueTag.getInstallType().equals(2L)){
                issueTagExcelVO.setInstallType("保内换卡");
            }else{
                issueTagExcelVO.setInstallType("保外换卡");
            }
            issueTagExcelVO.setFee(issueTag.getFee());

            resultList.add(issueTagExcelVO);
        }
        return resultList;
    }






}
