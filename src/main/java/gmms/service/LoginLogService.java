package gmms.service;

import com.google.common.collect.Lists;

import gmms.dao.LoginLogDao;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.LoginLog;
import gmms.domain.db.Users;
import gmms.domain.param.DataTablesParam;
import gmms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yangjb on 2017/8/6.
 * helloWorld
 */
@Service
public class LoginLogService {
    @Autowired
    private LoginLogDao loginLogDao;

    public void addLog(String ip, Users users) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginIp(ip);
        loginLog.setUsers(users);
        loginLogDao.save(loginLog);
    }

    public List<LoginLog> list(DataTablesParam dataTablesParam) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (dataTablesParam.getTimefromFormat() != null) {
            filters.add(new SearchFilter("gmtCreate", SearchFilter.Operator.GTE, dataTablesParam.getTimefromFormat()));
        }
        if (dataTablesParam.getTimetoFormat() != null) {
            filters.add(new SearchFilter("gmtCreate", SearchFilter.Operator.LTE, new Date(DateUtil.dayEndnTime(dataTablesParam.getTimetoFormat()))));
        }

        Specification<LoginLog> spec = DynamicSpecifications.bySearchFilter(filters, LoginLog.class);
        Sort purchaseDateDB = new Sort(Sort.Direction.DESC, "gmtCreate");

        return loginLogDao.findAll(spec, purchaseDateDB);
    }
}
