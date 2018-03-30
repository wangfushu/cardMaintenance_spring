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
/**
 * @author Administrator
 * 
 * TODO Ҫ��Ĵ���ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable rootCause) {
		super(rootCause);
	}

}
