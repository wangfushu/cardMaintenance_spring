package gmms.service;

import com.google.common.collect.Lists;

import gmms.dao.MsgDao;
import gmms.dao.util.DynamicSpecifications;
import gmms.dao.util.SearchFilter;
import gmms.domain.db.Msg;
import gmms.domain.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangjb on 2017/7/16.
 * helloWorld
 */
@Service
public class MsgService {
    @Autowired
    private MsgDao msgDao;

    public List<Msg> listByStatus() {
        return listByStatus(null, null);
    }

    public List<Msg> listByStatus(Integer status) {
        return listByStatus(status, null);
    }

    public List<Msg> listByStatus(Integer status, Long userId) {
        List<SearchFilter> filters = Lists.newArrayList();
        if (status != null) {
            filters.add(new SearchFilter("status", SearchFilter.Operator.EQ, status));
        }
        if (userId != null && userId != -1) {
            filters.add(new SearchFilter("users.id", SearchFilter.Operator.EQ, userId));
        }
        Specification<Msg> spec = DynamicSpecifications.bySearchFilter(filters, Msg.class);
//        PageRequest page = new PageRequest(pageNo - 1, pageSize, new Sort(Sort.Direction.DESC, "id"));
//        rentHouseDao.findAll(spec, page);
        return msgDao.findAll(spec, new Sort(Sort.Direction.DESC, "gmtCreate"));
    }

    public long countByStatus(Integer status, Users ownUser) {
        List<SearchFilter> filters = Lists.newArrayList(new SearchFilter("users", SearchFilter.Operator.EQ, ownUser));
        if (status != null) {
            filters.add(new SearchFilter("status", SearchFilter.Operator.EQ, status));
        }
        Specification<Msg> spec = DynamicSpecifications.bySearchFilter(filters, Msg.class);
        return msgDao.count(spec);
    }

    public void saveList(List<Msg> msgList) {
        msgDao.save(msgList);
    }

    public void addMsg(String content, Users users) {
        Msg newMsg = new Msg();
        newMsg.setContent(content);
        newMsg.setUsers(users);
        msgDao.save(newMsg);
    }

}
