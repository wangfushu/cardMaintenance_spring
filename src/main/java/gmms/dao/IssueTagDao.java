package gmms.dao;


import gmms.domain.db.IssueTag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface IssueTagDao extends BaseDao<IssueTag, Long> {

  /*  @Query(nativeQuery =true ,value = "select convert(char(7),installDate,120) 统计日期,userNo 发卡账号,userName 发卡姓名,plazaName 网点名称," +
            "(select count(*) from IssueTag temp where temp.installType = 1 group by convert(char(7),installDate,120),userName,plazaNo,plazaName) 新发卡数量  ," +
            "(select count(*) from IssueTag temp where temp.installType = 2 group by convert(char(7),installDate,120),userName,plazaNo,plazaName) 保内换卡数量," +
            "(select count(*) from IssueTag temp where temp.installType = 3 group by convert(char(7),installDate,120),userName,plazaNo,plazaName) 保外换卡数量," +
            "count(*) 总数量, SUM(fee) 实收金额" +
            "from IssueTag  t where userId=? and convert(char(4),installDate,120)='?'" +
            "group by convert(char(7),installDate,120),userNo,userName,plazaNo,plazaName")
    Page<Object[]> findAll(Long userId, String year, Pageable pageRequest);*/

}
