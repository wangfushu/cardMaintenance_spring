package gmms.task;

import com.xmrbi.parking.CheckCode;
import gmms.util.StringUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wangfs on 2017/7/30.
 * helloWorld
 */
@Component
public class TestTask {
/*    @Scheduled(cron = "0/5 * *  * * ? ")   //每5秒执行一次
    public void run() {
        System.out.println("hello");
        String src = "orderNo=201710160905571841&totalFee=1&timestamp=1508115957182";
        //byte[] bytes = StringUtil.toByteArray(src);
        byte[] bytes = src.getBytes();
        byte[] checkCode = CheckCode.getCheckCode(bytes.length, bytes);
        System.out.println(StringUtil.toHexString(checkCode));
    }*/
}
