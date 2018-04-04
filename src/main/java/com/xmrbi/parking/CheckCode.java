package com.xmrbi.parking;

import gmms.util.StringUtil;
import org.apache.commons.lang3.SystemUtils;

import java.lang.reflect.Field;

public class CheckCode {
    static {
        String libraryPath = System.getProperty("java.library.path");
        System.setProperty("java.library.path",
                "D:/CheckCode/x64/Release/" + SystemUtils.PATH_SEPARATOR + libraryPath);
        try {
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (Exception e) {
            //throw new BusinessException();
        }
        System.loadLibrary("JCheckCode");
    }

    public native static byte[] getCheckCode(int srcLen, byte[] src);

    public static void main(String[] args) {
        //String src = "0x8003|SvHBZSF0dxjMTaIZ2ksRjWHgrB9JAQmH|1468829709185|1.1|RC39|RC39|ec9189bdf1bb3cdd89ee5ed42f18498d|123456789|987654321||取消没有原因";
        String src = "orderNo=4SPAY20171102164628776&totalFee=1&timestamp=1509612388771";
        //byte[] bytes = StringUtil.toByteArray(src);
        byte[] bytes = src.getBytes();
        byte[] checkCode = CheckCode.getCheckCode(bytes.length, bytes);
        System.out.println(StringUtil.toHexString(checkCode));
    }
}
