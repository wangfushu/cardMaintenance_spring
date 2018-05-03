package gmms.service;

import gmms.domain.db.FmAliWeiChartPayLog;
import gmms.domain.db.Users;
import gmms.domain.db.VmVehicle;
import gmms.domain.query.VmVehicleQueryParam;
import gmms.unifiedPay.RefundPay;
import gmms.util.JsonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.List;


/**
 * Created by wangfs on 2017/6/28. helloWorld
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersServiceTest {
/*	@Autowired
	private UsersService usersService;
	@Autowired
	private TestUserService testUserService;*/
	@Autowired
	private UsersService usersService;
	@Autowired
	private VmVehicleService vmVehicleService;
	@Autowired
	private FmFeeService fmFeeService;
	@Autowired
	private BaseInformationService baseInformationService;
	@Test
	public void findById() throws Exception {
		VmVehicle vmVehicle = new VmVehicle();
		System.out.println(JsonMapper.nonEmptyMapper().toJson(vmVehicle));
	//System.out.println(baseInformationService.findCardExpenseByValue().getCfConfigValue());

	/*	Users users =usersService.findById(4L);
		Page<VmVehicle> vmVehicles = vmVehicleService.androidlistAll(new VmVehicleQueryParam(),users, 1, 10);
		System.out.println(vmVehicles.getContent().size());*/

		/*int a=5;
		System.out.println("value is :"+( (a<5) ? 10.9 : 9));
		*/
/*		VmVehicle vmVehicle=vmVehicleService.findById("00001201804190000002");
		Users users = usersService.findById(4L);
		fmFeeService.saveInSureFmFee(vmVehicle,users);*/
		//System.out.println(	Float.valueOf(baseInformationService.findCardExpenseByValue().getCfConfigValue()));


		/*List<FmAliWeiChartPayLog> list=fmFeeService.getALLFmAliWeiChartPayLogHasPay();
		for(FmAliWeiChartPayLog a:list) {
			//退款订单
			RefundPay refundPay = new RefundPay();
			String testresult = refundPay.refundPay(a.getOrderNo(),a.getPayOrderNo(), Double.valueOf(0.01));
			System.out.println("+++++++++++++++++++++++++: " + testresult);
		}
*/

	}



}