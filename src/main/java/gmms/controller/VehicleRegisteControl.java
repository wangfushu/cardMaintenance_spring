package gmms.controller;


import gmms.domain.AjaxResponseBodyFactory;
import gmms.domain.db.SysBaseInformation;
import gmms.domain.db.TmTagStore;
import gmms.domain.db.Users;
import gmms.domain.db.VmVehicle;
import gmms.domain.form.VmVehicleForm;
import gmms.domain.param.IssueForm;
import gmms.domain.query.VmVehicleQueryParam;
import gmms.service.BaseInformationService;
import gmms.service.TagService;
import gmms.service.UsersService;
import gmms.service.VmVehicleService;
import gmms.util.*;
import net.sf.json.JSONArray;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfs on 2017-10-10 helloword.
 * <p/>
 * 车辆信息控制层
 */

@RequestMapping("/vehicle")
@Controller
public class VehicleRegisteControl extends BaseControl {

    private Logger LOGGER = LoggerFactory.getLogger(VehicleRegisteControl.class);

    @Autowired
    private VmVehicleService vmVehicleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private BaseInformationService baseInformationService;
    @Value("${image_path}")
    private String file_path;
    @Value("${image_file_name}")
    private String file_name;


    @ResponseBody
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    private String VehicleRegiste(@RequestParam(value = "file1", required = false) MultipartFile file1, @RequestParam(value = "file2", required = false) MultipartFile file2, @RequestParam(value = "file3", required = false) MultipartFile file3,@RequestParam(value = "file4", required = false) MultipartFile file4,
                                  HttpServletRequest request) {
        try {
            //用户Id
            String userId = request.getParameter("userId");
            Users users = usersService.findById(Long.valueOf(userId));

            VmVehicleForm vmVehicleForm = new VmVehicleForm();
            //车辆编号
            String vehicleNo = request.getParameter("vehicleNo");
            if (!StringUtil.isEmpty(vehicleNo)) {
                vmVehicleForm.setVehicleNo(request.getParameter("vehicleNo"));
            } else {
                //流水单号生成
                String id = new String();
                // 车辆编号生成规则：4位网点号 + 3位计算机编号 + 8位日期(YYYYMMDD) + 5位流水号;(作废)
                ////车辆编号生成规则：5位网点号  + 8位日期(YYYYMMDD) + 7位流水号;
                int initNumber = 1;
                String formatNum = StringUtils.getFormat(5, initNumber);
                //employee
                String currDate =  StringUtils.getFormat(5,users.getSysPlaza().getPlaNo().intValue())  + DateUtils.getCurrTimeStr(6);

                vmVehicleForm.setVehicleNo(vmVehicleService.generate(currDate, "VmVehicle.vehicleNo"));
            }
            //车牌号
            String plateNo = request.getParameter("plateNo");
            if (StringUtils.isNotNullBlank(plateNo))
                vmVehicleForm.setPlateNo(request.getParameter("plateNo").toUpperCase());
            //车牌颜色
            String plateColor = request.getParameter("plateColor");
            if (StringUtils.isNotNullBlank(plateColor))
                vmVehicleForm.setPlateColor(request.getParameter("plateColor"));
            //车型
            String vKindNo = request.getParameter("vKindNo");
            if (StringUtils.isNotNullBlank(vKindNo))
                vmVehicleForm.setvKindNo(Long.valueOf(request.getParameter("vKindNo")));
            //载量
            String spec = request.getParameter("spec");
            if (StringUtils.isNotNullBlank(spec))
                vmVehicleForm.setSpec(Float.valueOf(request.getParameter("spec")));

            //用户名称
            String custName = request.getParameter("custName");
            if (StringUtils.isNotNullBlank(custName))
                vmVehicleForm.setCustName(request.getParameter("custName"));
            //办理人名称
            String handleName = request.getParameter("handleName");
            if (StringUtils.isNotNullBlank(handleName))
                vmVehicleForm.setHandleName(request.getParameter("handleName"));
            //用户单位
            String incName = request.getParameter("incName");
            if (StringUtils.isNotNullBlank(incName))
                vmVehicleForm.setIncName(request.getParameter("incName"));
            //联系人电话
            String phone = request.getParameter("phone");
            if (StringUtils.isNotNullBlank(phone))
                vmVehicleForm.setPhone(request.getParameter("phone"));
            //联系人地址
            String address = request.getParameter("address");
            if (StringUtils.isNotNullBlank(address))
                vmVehicleForm.setAddress(request.getParameter("address"));
            //联系人传真
            String fax = request.getParameter("fax");
            if (StringUtils.isNotNullBlank(fax))
                vmVehicleForm.setFax(request.getParameter("fax"));
            //联系人邮编
            String zip = request.getParameter("zip");
            if (StringUtils.isNotNullBlank(zip))
                vmVehicleForm.setZip(request.getParameter("zip"));
            //联系人邮箱
            String email = request.getParameter("email");
            if (StringUtils.isNotNullBlank(email))
                vmVehicleForm.setEmail(request.getParameter("email"));


            //身份证ID
            String iDCard = request.getParameter("iDCard");
            if (StringUtils.isNotNullBlank(iDCard))
                vmVehicleForm.setiDCard(request.getParameter("iDCard"));


            //备注说明
            String aMemo = request.getParameter("aMemo");
            if (StringUtils.isNotNullBlank(aMemo))
                vmVehicleForm.setaMemo(request.getParameter("aMemo"));


            vmVehicleForm.setNewCarRegistNode(0L);//新车注册节点或者修改都是节点 0状态待支付状态

            //注册时间
            String vRegDate = request.getParameter("vRegDate");
            if (StringUtils.isNotNullBlank(vRegDate))
                vmVehicleForm.setvRegDate(DateUtil.fromDateStringToYMDDate(vRegDate));

            List<MultipartFile> files = new ArrayList<MultipartFile>();
            if (null!=file1&&!file1.isEmpty()) {
                files.add(file1);
            }
            if (null!=file2&&!file2.isEmpty()) {
                files.add(file2);
            }
            if (null!=file3&&!file3.isEmpty()) {
                files.add(file3);
            }
            if (null!=file4&&!file4.isEmpty()) {
                files.add(file4);
            }
            VmVehicle isVmVehicleExit = vmVehicleService.findById(vmVehicleForm.getVehicleNo());

            if (null != files) {
                for (MultipartFile file : files) {
                    String imagePath = "";
                    String path = "";
                    //String imageName="20110000000";
                    if (!file.isEmpty()) {
                   /* 图片规则：
                    表Vm_Vehicle 字段 V_ImageDirectory 存 ftp路径yyyy\mm\dd\： 如2012\03\16\
                    图片
                    1-证件(正)图片，保存到ftp路径下的 名称  V_VehicleNo+D.jpg  如：00016220150109000352D.jpg
                    2-证件(副)图片，保存到ftp路径下的 名称  V_VehicleNo+I.jpg  如：00016220150109000352I.jpg
                    3-身份证图片，保存到ftp路径下的 名称  V_VehicleNo+IDCard.jpg  如：00016220150109000352IDCard.jpg
                    4-运营证图片，保存到ftp路径下的 名称  V_VehicleNo+ServiceCard.jpg  如：00016220150109000352ServiceCard.jpg
                    5-车卡合一图片，保存到ftp路径下的名称V_VehicleNo+RFID.JPG
                    */

                        //生成uuid作为文件名称
                        //String uuid = UUID.randomUUID().toString().replaceAll("-","");
                        //获得文件类型（可以判断如果不是图片，禁止上传）
                        String dateStr = DateUtils.getCurrDateStr(2);
                        String basePath = "";
                        for (String pathStr : dateStr.split("-")) {
                            basePath += pathStr + "/";
                        }
                        basePath = basePath + vmVehicleForm.getVehicleNo() + "/";
                        if (null != isVmVehicleExit) {
                            basePath = isVmVehicleExit.getPictureDirectory();
                            vmVehicleForm.setPictureDirectory(basePath);
                        }else{
                            vmVehicleForm.setPictureDirectory(file_name + basePath);
                        }
                        //图片存放路径
                        if(null != isVmVehicleExit){
                            imagePath = file_path + basePath;
                        }else {
                            imagePath = file_path + file_name + basePath;
                        }
                        vmVehicleForm.setImageDirectory(imagePath);
                        //vmVehicleForm.setvRIFDImageDirectory(imagePath);


                        String contentType = file.getContentType();
                        //获得文件名称
                        String fileName = file.getOriginalFilename();

                        String imageName = vmVehicleForm.getVehicleNo() + fileName;

                        //图片完整路径
                        path = imagePath + imageName;
                        // imagePath = imageName;
                        newFolder(imagePath);
                        //存放图片
                        file.transferTo(new File(path));

                    }
                    System.out.println(path);
                }
            }
            VmVehicle save = vmVehicleService.saveOrupdateVmVehicle(vmVehicleForm, users);
            return AjaxResponseBodyFactory.createSuccessBody(true, save.getVehicleNo());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    /**
     * 车辆信息列表
     *
     * @param queryParam
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryvehiclepage", method = RequestMethod.POST)
    public String queryVehiclePage(VmVehicleQueryParam queryParam, @RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        //  long startTime = System.currentTimeMillis();    //获取开始时间
        Users users;
        if (null != queryParam.getUserId() || !"".equals(String.valueOf(queryParam.getUserId()))) {
            users = this.usersService.findById(queryParam.getUserId());
        } else {
            users = getCurrentUser();
        }
        Page<VmVehicle> vmVehicles = vmVehicleService.androidlistAll(queryParam, users, pageNo, pageSize);

        if (vmVehicles.getContent().size() > 0) {
            return JsonMapper.nonEmptyMapper().toJson(vmVehicles);
        } else {
            return "null";
        }

    }


    /**
     * 车辆信息
     * @param vVehicleNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryvehicle", method = RequestMethod.POST)
    private String queryVehicleRegiste(String vVehicleNo) {
        VmVehicle vmVehicle = vmVehicleService.findById(vVehicleNo);
        return JsonMapper.nonEmptyMapper().toJson(vmVehicle);
    }

    /**
     * 车辆信息
     * @param plateNo
     * @param plateColor
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryvehicleByplateNo", method = RequestMethod.POST)
    private String queryvehicleByplateNo(String plateNo,String plateColor) {
        VmVehicle vmVehicle = vmVehicleService.findVmVehicleByPlateNoAndPlateColor(plateNo,plateColor);
        return vmVehicle.getVehicleNo();
    }

    /**
     * 查找车型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryvehicleKind")
    private String queryvehicleKind() {
        List<SysBaseInformation> vmVehicleKinds = baseInformationService.findVehicleKindByValue();
        return JsonMapper.nonEmptyMapper().toJson(vmVehicleKinds);
    }

    /**
     * 核对参数
     * @param vehicleNo
     * @param plateNo
     * @param plateColor
     * @param vKindNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkVmVehicle", method = RequestMethod.POST)
    private String CheckVmVehicle(String vehicleNo, String plateNo, String plateColor, Long vKindNo) {
        VmVehicle vmVehicle = vmVehicleService.queryVmVehicleIsExist(plateNo, plateColor, vKindNo);
        if (null!=vmVehicle) {
            if (StringUtils.isNotNullBlank(vehicleNo)) {
                if (vmVehicle.getVehicleNo().equals(vehicleNo)) {
                    return "true";//没重复
                } else {
                    return "false";//重复
                }
            } else {
                return "false";//重复
            }
        } else {
            return "true";//没重复
        }
    }



    /**
     * 贴卡
     *
     * @param file1
     * @param file2
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stickrfid", method = RequestMethod.POST)
    private String StickRfid(@RequestParam(value = "file1", required = false) MultipartFile file1, @RequestParam(value = "file2", required = false) MultipartFile file2,
                             HttpServletRequest request) {
        try {
            IssueForm issueForm = new IssueForm();
            //用户Id
            String userId = request.getParameter("userId");
            Users users = usersService.findById(Long.valueOf(userId));
            if(null!=users) {
                issueForm.setUserId(users.getId());
                issueForm.setUserNo(users.getUserNo());
                issueForm.setUserName(users.getUserName());
                issueForm.setPlazaNo(users.getSysPlaza().getPlaNo());
                issueForm.setPlazaName(users.getSysPlaza().getPlaName());
            }
            String vehicleNo = request.getParameter("vehicleNo");


            String tid = request.getParameter("tid");
            issueForm.setTid(tid);
            String epc = request.getParameter("epc");
            issueForm.setEpc(epc);
            List<MultipartFile> files = new ArrayList<MultipartFile>();
            if (!file1.isEmpty()) {
                files.add(file1);
            }
            if (!file2.isEmpty()) {
                files.add(file2);
            }
            VmVehicle isVmVehicleExit = vmVehicleService.findById(vehicleNo);

            if (null != files) {
                for (MultipartFile file : files) {
                    String imagePath = "";
                    String path = "";
                    if (!file.isEmpty()) {

                        //生成uuid作为文件名称
                        //String uuid = UUID.randomUUID().toString().replaceAll("-","");
                        //获得文件类型（可以判断如果不是图片，禁止上传）

                        String dateStr = DateUtils.getCurrDateStr(2);
                        String basePath = "";
                        for (String pathStr : dateStr.split("-")) {
                            basePath += pathStr + "/";
                        }
                        //2018/02/07/00010120180207000002/
                        basePath = file_path+file_name+basePath + vehicleNo + "/";
                        if (null != isVmVehicleExit) {
                            basePath = isVmVehicleExit.getImageDirectory();
                        }

                        //图片存放路径
                        imagePath = basePath;

                        String contentType = file.getContentType();
                        //获得文件名称
                        String fileName = file.getOriginalFilename();


                        String imageName =vehicleNo + fileName;
                        //图片完整路径
                        path = imagePath + imageName;
                        newFolder(imagePath);

                        //存放图片
                        file.transferTo(new File(path));

                    }
                    System.out.println(path);
                }
            }
            String result = vmVehicleService.updateIssueTagAndVmVehicle(vehicleNo,users,issueForm);
            if (result.equals("success")) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }


    /**
     * 卡库存判断
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkTmStore", method = RequestMethod.POST)
    private String checkTmStore(Long userId) {
        Users users = usersService.findById(userId);
        TmTagStore tmTagStore = tagService.findTmTagStoreByPlazaNo(users.getSysPlaza().getPlaNo());
        if(null == tmTagStore){
            return "empty";//无卡库存
        }else if(tmTagStore.getGoodTagCount() <= 0){
            return "empty";//无卡库存
        }else{
            return "success";
        }
    }


    public static void newFolder(String folderPath) {
        try {
            File myFilePath = new File(folderPath);
            if (!myFilePath.exists()) {
                //创建多级文件夹
                myFilePath.mkdirs();
                System.out.println("创建文件夹路径：" + folderPath);
            }
        } catch (Exception e) {
            System.out.println("新建文件夹操作出错");
            e.printStackTrace();
        }
    }




}
