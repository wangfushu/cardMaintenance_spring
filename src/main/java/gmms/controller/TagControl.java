package gmms.controller;



import com.google.common.collect.Lists;
import gmms.domain.AjaxResponseBodyFactory;
import gmms.domain.db.Role;
import gmms.domain.db.TmTagType;
import gmms.domain.db.Users;
import gmms.service.TagService;
import gmms.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * Created by wangfs on 2017-10-10 helloword.
 * <p/>
 * 卡控制层
 */

@RequestMapping("/tag")
@Controller
public class TagControl extends BaseControl {
    private Logger LOGGER = LoggerFactory.getLogger(TagControl.class);
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/list")
    public String list( Model model) {
        List<TmTagType> tmTagTypes = tagService.listAllTagType();
        model.addAttribute("tmTagTypeList", tmTagTypes);
        return "cardmaintenanceadmin/tags/tagtype-list";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(TmTagType tmTagType) {
        long typeId = 0;
        if (tmTagType.getTypeId() != null) {
            typeId = tmTagType.getTypeId();
            TmTagType oldTagType=tagService.getbyTypeId(tmTagType.getTypeId());
            oldTagType.setTagType(tmTagType.getTagType());
            oldTagType.setCommunicateRate(tmTagType.getCommunicateRate());
            oldTagType.setFactory(tmTagType.getFactory());

            oldTagType.setInUse(tmTagType.getInUse());
            tagService.saveOrUpdateTmTagType(oldTagType);
        } else {
            ;
            TmTagType save = tagService.saveOrUpdateTmTagType(tmTagType);
            typeId = save.getTypeId();
        }
        return AjaxResponseBodyFactory.createSuccessBody(true, typeId);
    }

}
