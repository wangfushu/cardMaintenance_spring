package gmms.controller;



import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import gmms.domain.AjaxResponseBodyFactory;
import gmms.domain.db.*;
import gmms.domain.param.DataTablesParam;
import gmms.domain.param.TmTagStoreParam;
import gmms.service.BaseInformationService;
import gmms.service.TagService;
import gmms.util.DateUtil;
import gmms.util.JsonMapper;
import gmms.util.Pagination;
import gmms.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Users currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
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
        //List<TmTagType> tmTagTypes = tagService.TagTypelistAll();
        List<TmTagStore> tmTagStores = tagService.listAllTmTagStore(tmTagStoreParam);
        model.addAttribute("tagInStoreTypeList",tagService.findInStoreTypeByValue());
        //model.addAttribute("tmTagTypeList", tmTagTypes);
        model.addAttribute("tmTagStoreList", tmTagStores);
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", tmTagStoreParam);
        return "cardmaintenanceadmin/tags/plazatag-managelist";
    }

    @ResponseBody
    @RequestMapping(value = "/tagstorePage")
    public String tagstorePage(TmTagStoreParam tmTagStoreParam, Model model) {
       // List<TmTagType> tmTagTypes = tagService.TagTypelistAll();
        int pageNo = tmTagStoreParam.getStart() / tmTagStoreParam.getLength() + 1;
        Pagination<TmTagStore> pagination = new Pagination<TmTagStore>();
        Page<TmTagStore> tmTagStores = tagService.listAllTmTagStorePage(tmTagStoreParam,pageNo,tmTagStoreParam.getLength());
        pagination.setData(tmTagStores.getContent());
        pagination.setDraw(tmTagStoreParam.getDraw());
        pagination.setRecordsFiltered((int) tmTagStores.getTotalElements());
        pagination.setRecordsTotal((int) tmTagStores.getTotalElements());
       // pagination.setLength(tmTagStoreParam.getLength());
        if(tmTagStores.getContent().size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/inStore", method = RequestMethod.POST)
    public String inStore(TmTagInStore tmTagInStore) {
        //long inId = 0;
        Users users= getCurrentUser();
        tmTagInStore.setUserId(users.getId());
        tmTagInStore.setUserNo(users.getUserNo());
        tmTagInStore.setUserName(users.getUserName());
        tmTagInStore.setInStoreTime(new Date());
        if(!StringUtil.isEmpty(String.valueOf(tmTagInStore.getInRecievedPlazaNo()))){
            SysPlaza sysPlaza=baseInformationService.findSysPlaza(tmTagInStore.getInRecievedPlazaNo());
            tmTagInStore.setInRecievedPlazaName(sysPlaza.getPlaName());
        }
        if(!StringUtil.isEmpty(String.valueOf(tmTagInStore.getInSendPlazaNo()))){
            SysPlaza sysPlaza=baseInformationService.findSysPlaza(tmTagInStore.getInSendPlazaNo());
            tmTagInStore.setInSendPlazaName(sysPlaza.getPlaName());
        }
        if(tmTagInStore.getIsNewCardInStore()){
            tmTagInStore.setInSendPlazaNo(users.getSysPlaza().getPlaNo());
            tmTagInStore.setInSendPlazaName(users.getSysPlaza().getPlaName());
        }
        TmTagInStore save = tagService.inStoreTagAll(tmTagInStore);
        long inId = save.getInID();
        return AjaxResponseBodyFactory.createSuccessBody(true, inId);
    }


    @ResponseBody
    @RequestMapping(value = "checkBadCount")
    public String checkBadCount(Long inSendPlazaNo,Long inStoreType,int count){
        Preconditions.checkNotNull(count, "count 不能为空");
        if(inStoreType.equals(1L)){
            return "true";
        }else{
            TmTagStore tmTagStore=tagService.findTmTagStoreByPlazaNo(inSendPlazaNo);
            if(null!=tmTagStore){
                if(count>tmTagStore.getGoodTagCount()){
                    return "false";
                }
            }
            return "true";
        }
       // Users users = usersService.findByName(userNo);
       // return inStoreType.equals(1L)?"true":"false";
    }

    @ResponseBody
    @RequestMapping(value = "/outStore", method = RequestMethod.POST)
    public String outStore(TmTagOutStore temp,String outRecievedlazaNoListinput) {
        //long inId = 0;
        Users users= getCurrentUser();
        String[] split = outRecievedlazaNoListinput.split(",");
        for (String plaNoStr : split) {
            long id = Long.parseLong(plaNoStr);
            TmTagOutStore tmTagOutStore=new TmTagOutStore();
            SysPlaza sysPlaza1 =baseInformationService.findSysPlaza(id);
            if(!sysPlaza1.isAllPlazaNo()) {
                tmTagOutStore.setUserId(users.getId());
                tmTagOutStore.setUserNo(users.getUserNo());
                tmTagOutStore.setUserName(users.getUserName());
                tmTagOutStore.setOutStoreTime(new Date());

                tmTagOutStore.setOutRecievedPlazaName(sysPlaza1.getPlaName());
                tmTagOutStore.setOutRecievedPlazaNo(sysPlaza1.getPlaNo());
                if (!StringUtil.isEmpty(String.valueOf(temp.getOutSendPlazaNo()))) {
                    SysPlaza sysPlaza = baseInformationService.findSysPlaza(temp.getOutSendPlazaNo());
                    tmTagOutStore.setOutSendPlazaName(sysPlaza.getPlaName());
                    tmTagOutStore.setOutSendPlazaNo(sysPlaza.getPlaNo());
                }
                tmTagOutStore.setCount(temp.getCount());
                TmTagOutStore save = tagService.outStoreTagAll(tmTagOutStore);
            }
        }
        long outId = 0l;
        return AjaxResponseBodyFactory.createSuccessBody(true, outId);
    }

    @ResponseBody
    @RequestMapping(value = "checkOutCount")
    public String checkOutCount(String outRecievedlazaNoListinput,Long outSendPlazaNo, int count){
        Preconditions.checkNotNull(count, "count 不能为空");
        Preconditions.checkNotNull(outRecievedlazaNoListinput, "接收方网点不能为空");
        String[] split = outRecievedlazaNoListinput.split(",");
        List<String> plazaNos=Lists.newArrayList();
        for (String idStr : split) {
            if(!idStr.equals(outSendPlazaNo)){
                plazaNos.add(idStr);
            };
        }
        if(plazaNos.size()>0){
              TmTagStore tmTagStore= tagService.findTmTagStoreByPlazaNo(outSendPlazaNo);
            if((plazaNos.size()*count)<=tmTagStore.getGoodTagCount()){
                return "true";
            }

        }else{
            return "false";
        }

        return "false";
    }

    @ResponseBody
    @RequestMapping(value = "tagStoreCount")
    public String tagStoreCount(Long plazaNo){
   TmTagStore tmTagStore = tagService.findTmTagStoreByPlazaNo(plazaNo);
        if(null!=tmTagStore){
            return String.valueOf(tmTagStore.getGoodTagCount());
        }else{
            return "0";
        }// users.setStoreNum(tmTagStore.getGoodTagCount());
    }



}
