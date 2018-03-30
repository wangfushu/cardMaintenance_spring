package gmms.util.CommonExceptions;

import org.springframework.dao.DataAccessException;

public class ResourceAccessException extends DataAccessException{

    public ResourceAccessException(String message) {
        super(message);
        // TODO 自动生成构造函数存根
    }
    
    
    public ResourceAccessException(String message,Throwable rootCause) {
        super(message,rootCause);
        // TODO 自动生成构造函数存根
    }
}
