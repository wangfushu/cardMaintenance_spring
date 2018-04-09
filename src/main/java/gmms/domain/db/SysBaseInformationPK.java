package gmms.domain.db;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by wangfs on 2017-09-05 helloword.
 */


public class SysBaseInformationPK implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BI_TYPE")
    private String biType;

    @Id
    @Column(name = "BI_VALUE")
    private String biValue;


    public SysBaseInformationPK(String biType, String biValue) {
        this.biType = biType;
        this.biValue = biValue;
    }

    public SysBaseInformationPK() {
    }
}
