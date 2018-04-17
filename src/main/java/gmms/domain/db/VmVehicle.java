package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Vm_Vehicle")
@DynamicInsert
public class VmVehicle {

	@Id
	@Column(name = "VEHICLENO")
	private String vehicleNo;//车辆编号生成规则：5位网点号  + 8位日期(YYYYMMDD) + 7位流水号;

	@Column(name = "PLATENO")
	private String plateNo;//车牌

	@Column(name = "PLATECOLOR")
	private String plateColor;//车牌颜色

	@Column(name = "VKINDNO")
	private Long vKindNo;//车型


	@Column(name = "SPEC")
	private Float spec;//载量

	@Column(name = "VREGDATE")
	private Date vRegDate;//注册日期
	@Column(name = "CUSTNAME")
	private String custName;//用户名
	@Column(name = "IDCARD")
	private String iDCard;//身份证ID

	@Column(name = "INCNAME")
	private String incName;//用户单位

	@Column(name = "HANDLENAME")
	private String handleName;//办理人姓名


	@Column(name = "FAX")
	private String fax;//传真

	@Column(name = "PHONE")
	private String phone;//用户手机

	@Column(name = "ADDRESS")
	private String address;//地址

	@Column(name = "ZIP")
	private String zip;//邮编


	@Column(name = "EMail")
	private String email;//邮箱


	@Column(name = "UPDATEMODE")
	private Long updateMode;//更新模式


	@Column(name = "UPDATETIME")
	private Date updateTime;//更新时间


	@Column(name = "AMEMO")
	private String aMemo;//备注

	@Column(name = "INSTALLDATE")
	private Date installDate;//发卡时间

	@Column(name = "TAGTYPE")
	private Long tagType;//发卡时间

	@Column(name = "TAGNO")
	private String tagNo;//卡号

	@Column(name = "OUTTAGNO")
	private String outTagNo;//外码

	@Column(name = "epc")
	private String epc;//epc码

	@Column(name = "tid")
	private String tid;//tid码


	@Column(name = "BORNSOURCE")
	private Long bornSource;//


	@Column(name = "NEWCARREGISTNODE")
	private Long newCarRegistNode;//注册节点

	@Column(name = "PICTUREDIRECTORY")
	private String pictureDirectory;//图片路径

	@Column(name = "IMAGEDIRECTORY")
	private String imageDirectory;//完整图片路径

	@Column(name = "USERNO")
	private String userNo;
	@Column(name = "USERNAME")
	private String userName;
	@Column(name = "USERID")
	private Long userId;

	public String getHandleName() {
		return handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImageDirectory() {
		return imageDirectory;
	}

	public void setImageDirectory(String imageDirectory) {
		this.imageDirectory = imageDirectory;
	}

	public String getPictureDirectory() {
		return pictureDirectory;
	}

	public void setPictureDirectory(String pictureDirectory) {
		this.pictureDirectory = pictureDirectory;
	}

	public Long getvKindNo() {
		return vKindNo;
	}

	public void setvKindNo(Long vKindNo) {
		this.vKindNo = vKindNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public Float getSpec() {
		return spec;
	}

	public void setSpec(Float spec) {
		this.spec = spec;
	}

	public Date getvRegDate() {
		return vRegDate;
	}

	public void setvRegDate(Date vRegDate) {
		this.vRegDate = vRegDate;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getiDCard() {
		return iDCard;
	}

	public void setiDCard(String iDCard) {
		this.iDCard = iDCard;
	}

	public String getIncName() {
		return incName;
	}

	public void setIncName(String incName) {
		this.incName = incName;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(Long updateMode) {
		this.updateMode = updateMode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getaMemo() {
		return aMemo;
	}

	public void setaMemo(String aMemo) {
		this.aMemo = aMemo;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public Long getTagType() {
		return tagType;
	}

	public void setTagType(Long tagType) {
		this.tagType = tagType;
	}

	public String getTagNo() {
		return tagNo;
	}

	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}

	public String getOutTagNo() {
		return outTagNo;
	}

	public void setOutTagNo(String outTagNo) {
		this.outTagNo = outTagNo;
	}

	public String getEpc() {
		return epc;
	}

	public void setEpc(String epc) {
		this.epc = epc;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Long getBornSource() {
		return bornSource;
	}

	public void setBornSource(Long bornSource) {
		this.bornSource = bornSource;
	}

	public Long getNewCarRegistNode() {
		return newCarRegistNode;
	}

	public void setNewCarRegistNode(Long newCarRegistNode) {
		this.newCarRegistNode = newCarRegistNode;
	}
}
