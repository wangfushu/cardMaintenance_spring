package gmms.util.CommonExceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

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
public class BaseException extends Exception {
	protected String message = "";

	protected String messageKey = null;

	protected Object[] messageArgs = null;

	protected Throwable rootCause = null;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super();
		this.message = message;
	}

	public BaseException(Throwable rootCause) {
		this.rootCause = rootCause;
		this.message = rootCause.getMessage();
	}

	public String getMessage() {
		return this.message;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public void setRootCause(Throwable rootCause) {
		this.rootCause = rootCause;
	}

	public void printStackTrace() {
		this.printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream outStream) {
		this.printStackTrace(new PrintWriter(outStream));
	}

	public void printStackTrace(PrintWriter writer) {
		super.printStackTrace(writer);
		if (this.rootCause != null) {
			rootCause.printStackTrace();
		}
	}

}
