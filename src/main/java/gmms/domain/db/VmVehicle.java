package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Vm_Vehicle")
@DynamicInsert
public class VmVehicle {

	@Id
	@Column(name = "V_VEHICLENO")
	private String vVehicleNo;//车辆编号生成规则：4位网点号 + 3位计算机编号（旧的是计算机编号） + 8位日期(YYYYMMDD) + 5位流水号;

	@Column(name = "V_PLATENO")
	private String vPlateNo;//车牌


	@Column(name = "V_PLATECOLOR")
	private String vPlateColor;//车牌颜色


	@Column(name = "V_VEHICLEKINDNO")
	private String vVehicleKindNo;
	
	@Column(name = "V_KIND")
	private Long vKind;//车型
	
	@Column(name = "V_MODECODE")
	private Long vModeCode;//运营模式 1-营运0-非营运

	@Column(name = "V_TYPE")
	private Long vType;//车辆类别
	
	@Column(name = "V_CAPACITY")
	private Float vCapacity;//载量
	
	@Column(name = "V_CALCCAPACITY")
	private Float vCalcCapacity;//计征载量
	
	@Column(name = "V_REMARK")
	private String vRemark;//备注说明
	
	@Column(name = "V_TAGNO")
	private String vTagNo;
	
	@Column(name = "V_OUTTAGNO")
	private String vOutTagNo;
	
	@Column(name = "V_TAGTYPE")
	private Long vTagType;
	
	@Column(name = "V_TAGDATE")
	private Date vTagDate;
	
	@Column(name = "V_UPDATESIGN")
	private Long vUpdateSign;
	
	@Column(name = "V_CALCMONTHFEE")
	private Double vCalcMonthFee;//应缴年费标准
	
	@Column(name = "V_MONTHFEE")
	private Double vMonthFee;//年费标准
	
	@Column(name = "V_FEESTATE")
	private String vFeeState;//缴费状态
	
	@Column(name = "V_MASTERTYPE")
	private Long vMasterType;//车主类别1-私人2-单位

	@Column(name = "V_REGISTERTIME")
	private Date vRegisterTime;
	
	@Column(name = "V_FEESTARTTIME")
	private Date vFeeStartTime;
	
	@Column(name = "V_INPUTTIME")
	private Date vInputTime;
	
	@Column(name = "V_MODIFYTIME")
	private Date vModifyTime;
	
	@Column(name = "V_CUSTOMERNAME")
	private String vCustomerName;//用户名称
	
	@Column(name = "V_CUSTOMERFAX")
	private String vCustomerFax;//传真
	
	@Column(name = "V_TRANSANCTOR")
	private String vTransanctor;//联系人
	
	@Column(name = "V_TRANSACTORPHONE")
	private String vTransactorPhone;//联系电话
	
	@Column(name = "V_TRANSACTORADDRESS")
	private String vTransactorAddress;//联系地址


	@Column(name = "V_TRANSACTORZIP")
	private String vTransactorZip; //邮编
	
	@Column(name = "V_TRANSACTOREMAIL")
	private String vTransactorEmail;
	
	@Column(name = "V_IMAGEDIRECTORY")
	private String vImageDirectory;
	
	@Column(name = "V_VEHICLESOURCE")
	private Long vVehicleSource;
	
	@Column(name = "V_ISLOCAL")
	private Long vIsLocal;
	
	@Column(name = "V_ISCONSIGN")
	private Long vIsConsign;
	
	@Column(name = "V_BANKNO")
	private String vBankNo;
	
	@Column(name = "V_USERID")
	private Long vUserId;
	
	@Column(name = "V_USERNO")
	private String vUserNo;
	
	@Column(name = "V_USERNAME")
	private String vUserName;
	
	@Column(name = "V_IDCARD")
	private String vIDCard;//身份证号
	
	@Column(name = "V_PASSSTATE")
	private Long vPassState;
	
	@Column(name = "V_TAGSOURCE")
	private Long vTagSource;
	
	@Column(name = "V_VBODYCOLOR")
	private String vVBodyColor;//车身颜色
	
	@Column(name = "V_BRANK")
	private String vBrank;//品牌
	
	@Column(name = "V_OFFSETFEE")
	private BigDecimal vOffsetFee;//差额
	
	@Column(name = "V_CHANGETIME")
	private Date vChangeTime;
	
	@Column(name = "V_CALCEDITTIME")
	private Date vCalcEditTime;
	
	@Column(name = "V_CALCFEETIME")
	private Date vCalcFeeTime;

	@Column(name = "V_NEWCARREGISTNODE")
	private Long vNewCarRegistNode;//注册节点标识 0--待支付 1--待贴卡 2--完成


	@Column(name = "V_RFIDIMAGEDIRECTORY")
	private String vRFIDImageDirectory;//贴卡后车卡图像路径

	@Column(name = "V_AUDITSTATE")
	private Long vAuditState;//审核状态  0--待审核1--审核中 2--未通过3--审核通过

	@Column(name = "V_ISNEWENERGY")
	private Long vIsNewEnergy;//  0-传统内燃机  ,   1-新能源

	@Column(name = "V_PICTUREDIRECTORY")
	private String vPictureDirectory;//图片路径

	@Column(name = "V_RFIDSTATE")
	private Long  vRFIDState;//年费卡翻转 0--未完成1--完成

	@Column(name = "V_ISDEFEAT")
	private Long vIsDefeat;//是否作废 0--正常 1--作废


	@Column(name = "V_CERTIFICATE")
	private String vCertificate;//发动机后六位


	@Column(name = "V_POWERTYPE")
	private String vPowerType;//能源车类型 D1 小型车-纯电 、 D2 大型车-纯电 、F1 小型车-电混、 F2大型车-电混

	@Column(name = "V_CHECKOPTION")
	private String vCheckOption;//审核意见




	public String getvCheckOption() {
		return vCheckOption;
	}

	public void setvCheckOption(String vCheckOption) {
		this.vCheckOption = vCheckOption;
	}

	public String getvPowerType() {
		return vPowerType;
	}

	public void setvPowerType(String vPowerType) {
		this.vPowerType = vPowerType;
	}

	public String getvCertificate() {
		return vCertificate;
	}

	public void setvCertificate(String vCertificate) {
		this.vCertificate = vCertificate;
	}

	public Long getvAuditState() {
		return vAuditState;
	}

	public void setvAuditState(Long vAuditState) {
		this.vAuditState = vAuditState;
	}

	public String getvPictureDirectory() {
		return vPictureDirectory;
	}

	public void setvPictureDirectory(String vPictureDirectory) {
		this.vPictureDirectory = vPictureDirectory;
	}

	public Long getvRFIDState() {
		return vRFIDState;
	}

	public void setvRFIDState(Long vRFIDState) {
		this.vRFIDState = vRFIDState;
	}


	public String getvVehicleNo() {
		return vVehicleNo;
	}

	public void setvVehicleNo(String vVehicleNo) {
		this.vVehicleNo = vVehicleNo;
	}

	public String getvPlateNo() {
		return vPlateNo;
	}

	public void setvPlateNo(String vPlateNo) {
		this.vPlateNo = vPlateNo;
	}

	public String getvPlateColor() {
		return vPlateColor;
	}

	public void setvPlateColor(String vPlateColor) {
		this.vPlateColor = vPlateColor;
	}

	public String getvVehicleKindNo() {
		return vVehicleKindNo;
	}

	public void setvVehicleKindNo(String vVehicleKindNo) {
		this.vVehicleKindNo = vVehicleKindNo;
	}

	public Long getvKind() {
		return vKind;
	}

	public void setvKind(Long vKind) {
		this.vKind = vKind;
	}

	public Long getvModeCode() {
		return vModeCode;
	}

	public void setvModeCode(Long vModeCode) {
		this.vModeCode = vModeCode;
	}

	public Float getvCapacity() {
		return vCapacity;
	}

	public void setvCapacity(Float vCapacity) {
		this.vCapacity = vCapacity;
	}

	public Float getvCalcCapacity() {
		return vCalcCapacity;
	}

	public void setvCalcCapacity(Float vCalcCapacity) {
		this.vCalcCapacity = vCalcCapacity;
	}

	public String getvRemark() {
		return vRemark;
	}

	public void setvRemark(String vRemark) {
		this.vRemark = vRemark;
	}

	public String getvTagNo() {
		return vTagNo;
	}

	public void setvTagNo(String vTagNo) {
		this.vTagNo = vTagNo;
	}

	public String getvOutTagNo() {
		return vOutTagNo;
	}

	public void setvOutTagNo(String vOutTagNo) {
		this.vOutTagNo = vOutTagNo;
	}

	public Long getvTagType() {
		return vTagType;
	}

	public void setvTagType(Long vTagType) {
		this.vTagType = vTagType;
	}

	public Date getvTagDate() {
		return vTagDate;
	}

	public void setvTagDate(Date vTagDate) {
		this.vTagDate = vTagDate;
	}

	public Long getvUpdateSign() {
		return vUpdateSign;
	}

	public void setvUpdateSign(Long vUpdateSign) {
		this.vUpdateSign = vUpdateSign;
	}

	public Double getvCalcMonthFee() {
		return vCalcMonthFee;
	}

	public void setvCalcMonthFee(Double vCalcMonthFee) {
		this.vCalcMonthFee = vCalcMonthFee;
	}

	public Double getvMonthFee() {
		return vMonthFee;
	}

	public void setvMonthFee(Double vMonthFee) {
		this.vMonthFee = vMonthFee;
	}

	public String getvFeeState() {
		return vFeeState;
	}

	public void setvFeeState(String vFeeState) {
		this.vFeeState = vFeeState;
	}

	public Long getvMasterType() {
		return vMasterType;
	}

	public void setvMasterType(Long vMasterType) {
		this.vMasterType = vMasterType;
	}

	public Date getvRegisterTime() {
		return vRegisterTime;
	}

	public void setvRegisterTime(Date vRegisterTime) {
		this.vRegisterTime = vRegisterTime;
	}

	public Date getvFeeStartTime() {
		return vFeeStartTime;
	}

	public void setvFeeStartTime(Date vFeeStartTime) {
		this.vFeeStartTime = vFeeStartTime;
	}

	public Date getvInputTime() {
		return vInputTime;
	}

	public void setvInputTime(Date vInputTime) {
		this.vInputTime = vInputTime;
	}

	public Date getvModifyTime() {
		return vModifyTime;
	}

	public void setvModifyTime(Date vModifyTime) {
		this.vModifyTime = vModifyTime;
	}

	public String getvCustomerName() {
		return vCustomerName;
	}

	public void setvCustomerName(String vCustomerName) {
		this.vCustomerName = vCustomerName;
	}

	public String getvCustomerFax() {
		return vCustomerFax;
	}

	public void setvCustomerFax(String vCustomerFax) {
		this.vCustomerFax = vCustomerFax;
	}

	public String getvTransanctor() {
		return vTransanctor;
	}

	public void setvTransanctor(String vTransanctor) {
		this.vTransanctor = vTransanctor;
	}

	public String getvTransactorPhone() {
		return vTransactorPhone;
	}

	public void setvTransactorPhone(String vTransactorPhone) {
		this.vTransactorPhone = vTransactorPhone;
	}

	public String getvTransactorAddress() {
		return vTransactorAddress;
	}

	public void setvTransactorAddress(String vTransactorAddress) {
		this.vTransactorAddress = vTransactorAddress;
	}

	public String getvTransactorZip() {
		return vTransactorZip;
	}

	public void setvTransactorZip(String vTransactorZip) {
		this.vTransactorZip = vTransactorZip;
	}

	public String getvTransactorEmail() {
		return vTransactorEmail;
	}

	public void setvTransactorEmail(String vTransactorEmail) {
		this.vTransactorEmail = vTransactorEmail;
	}

	public String getvImageDirectory() {
		return vImageDirectory;
	}

	public void setvImageDirectory(String vImageDirectory) {
		this.vImageDirectory = vImageDirectory;
	}

	public Long getvVehicleSource() {
		return vVehicleSource;
	}

	public void setvVehicleSource(Long vVehicleSource) {
		this.vVehicleSource = vVehicleSource;
	}

	public Long getvIsLocal() {
		return vIsLocal;
	}

	public void setvIsLocal(Long vIsLocal) {
		this.vIsLocal = vIsLocal;
	}

	public Long getvIsConsign() {
		return vIsConsign;
	}

	public void setvIsConsign(Long vIsConsign) {
		this.vIsConsign = vIsConsign;
	}

	public String getvBankNo() {
		return vBankNo;
	}

	public void setvBankNo(String vBankNo) {
		this.vBankNo = vBankNo;
	}

	public Long getvUserId() {
		return vUserId;
	}

	public void setvUserId(Long vUserId) {
		this.vUserId = vUserId;
	}

	public String getvUserNo() {
		return vUserNo;
	}

	public void setvUserNo(String vUserNo) {
		this.vUserNo = vUserNo;
	}

	public String getvUserName() {
		return vUserName;
	}

	public void setvUserName(String vUserName) {
		this.vUserName = vUserName;
	}

	public String getvIDCard() {
		return vIDCard;
	}

	public void setvIDCard(String vIDCard) {
		this.vIDCard = vIDCard;
	}

	public Long getvPassState() {
		return vPassState;
	}

	public void setvPassState(Long vPassState) {
		this.vPassState = vPassState;
	}

	public Long getvTagSource() {
		return vTagSource;
	}

	public void setvTagSource(Long vTagSource) {
		this.vTagSource = vTagSource;
	}

	public String getvVBodyColor() {
		return vVBodyColor;
	}

	public void setvVBodyColor(String vVBodyColor) {
		this.vVBodyColor = vVBodyColor;
	}

	public String getvBrank() {
		return vBrank;
	}

	public void setvBrank(String vBrank) {
		this.vBrank = vBrank;
	}

	public BigDecimal getvOffsetFee() {
		return vOffsetFee;
	}

	public void setvOffsetFee(BigDecimal vOffsetFee) {
		this.vOffsetFee = vOffsetFee;
	}

	public Date getvChangeTime() {
		return vChangeTime;
	}

	public void setvChangeTime(Date vChangeTime) {
		this.vChangeTime = vChangeTime;
	}

	public Date getvCalcEditTime() {
		return vCalcEditTime;
	}

	public void setvCalcEditTime(Date vCalcEditTime) {
		this.vCalcEditTime = vCalcEditTime;
	}

	public Date getvCalcFeeTime() {
		return vCalcFeeTime;
	}

	public void setvCalcFeeTime(Date vCalcFeeTime) {
		this.vCalcFeeTime = vCalcFeeTime;
	}



	public Long getvNewCarRegistNode() {
		return vNewCarRegistNode;
	}

	public void setvNewCarRegistNode(Long vNewCarRegistNode) {
		this.vNewCarRegistNode = vNewCarRegistNode;
	}

	public String getvRFIDImageDirectory() {
		return vRFIDImageDirectory;
	}

	public void setvRFIDImageDirectory(String vRFIDImageDirectory) {
		this.vRFIDImageDirectory = vRFIDImageDirectory;
	}

	public Long getvType() {
		return vType;
	}

	public void setvType(Long vType) {
		this.vType = vType;
	}




	public Long getvIsNewEnergy() {
		return vIsNewEnergy;
	}

	public void setvIsNewEnergy(Long vIsNewEnergy) {
		this.vIsNewEnergy = vIsNewEnergy;
	}

	public Long getvIsDefeat() {
		return vIsDefeat;
	}

	public void setvIsDefeat(Long vIsDefeat) {
		this.vIsDefeat = vIsDefeat;
	}

}
