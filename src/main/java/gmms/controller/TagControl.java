package gmms.controller;



import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import gmms.domain.AjaxResponseBodyFactory;
import gmms.domain.db.*;
import gmms.domain.param.TmTagStoreParam;
import gmms.service.BaseInformationService;
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

import javax.jws.soap.SOAPBinding;
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
    @Autowired
    private BaseInformationService baseInformationService;

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

    @ResponseBody
    @RequestMapping(value = "/tagTypeNotExist")
    public String tagTypeNotExist(String tagType) {
        Preconditions.checkNotNull(tagType, "tagType 不能为空");
        TmTagType tmTagType = tagService.findByName(tagType);
        return tmTagType == null ? "true" : "false";
    }


    @ResponseBody
    @RequestMapping(value = "/alterTagType")
    public String alterTagType(Long typeId,Long inUse){
        TmTagType tmTagType= tagService.getbyTypeId(typeId);
        tmTagType.setInUse(inUse);
        TmTagType save= tagService.saveOrUpdateTmTagType(tmTagType);
        return "ok";
    }


    @RequestMapping(value = "/tagstorelist")
    public String tagstorelist(TmTagStoreParam tmTagStoreParam, Model model) {
        List<TmTagType> tmTagTypes = tagService.TagTypelistAll();
        List<TmTagStore> tmTagStores = tagService.listAllTmTagStore(tmTagStoreParam);
        model.addAttribute("tagInStoreTypeList",tagService.findInStoreTypeByValue());
        model.addAttribute("tmTagTypeList", tmTagTypes);
        model.addAttribute("tmTagStoreList", tmTagStores);
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", tmTagStoreParam);
        return "cardmaintenanceadmin/tags/plazatag-managelist";
    }

    @ResponseBody
    @RequestMapping(value = "/inStore", method = RequestMethod.POST)
    public String inStore(TmTagInStore tmTagInStore) {
        long inId = 0;
        Users users= getCurrentUser();
        tmTagInStore.setUserId(users.getId());
        tmTagInStore.setUserNo(users.getUserNo());
        tmTagInStore.setUserName(users.getUserName());
        tmTagInStore.setInStoreTime(new Date());
        TmTagInStore save = tagService.inStoreTagAll(tmTagInStore);


        return AjaxResponseBodyFactory.createSuccessBody(true, inId);
    }
}
