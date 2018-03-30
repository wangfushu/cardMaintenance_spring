package gmms.util.CommonExceptions;
/**
 * <p>Title: ����ʡ���ٹ�·����������ϵͳ</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: ������·����Ϣ�������޹�˾</p>
 *
 * @author 
 * @version 2.0
 */
public class InvalidException extends Exception{

  private String msg = "";
  public InvalidException(String message) {
    super();
    this.msg = message;
  }

  public String getMessage() {
    return msg;
  }

}
