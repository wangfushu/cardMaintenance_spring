package gmms.service;


import com.google.common.collect.Lists;
import gmms.dao.TmTagTypeDao;
import gmms.domain.db.TmTagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by wangfs on 2018-02-23 helloword.
 */
@Service
public class TagService {
    @Autowired
    private TmTagTypeDao tmTagTypeDao;
    @PersistenceUnit
    private EntityManagerFactory emf;


   public List<TmTagType> listAllTagType(){
       return Lists.newArrayList(tmTagTypeDao.findAll());
   }

    public TmTagType getbyTypeId(Long typeId){
        return tmTagTypeDao.findOne(typeId);
    }
    public TmTagType saveOrUpdateTmTagType(TmTagType tmTagType){
        return tmTagTypeDao.save(tmTagType);
    }
}
