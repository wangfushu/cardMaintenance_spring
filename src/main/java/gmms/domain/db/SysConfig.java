package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangfs on 2017-09-05 helloword.
 */

@Entity
@Table(name = "SYS_CONFIG")
@DynamicInsert
@DynamicUpdate
public class SysConfig {


    @Id
    @Column(name = "CF_CONFIGNAME")
    private String cfConfigName;


    @Column(name = "CF_CONFIGVALUE")
    private String cfConfigValue;

    @Column(name = "CF_CONFIGDESCRIPTION")
    private String cfConfigDescription;


    @Column(name = "CF_UPDATETIME")
    private Date cfUpdateTime;



    public String getCfConfigName() {
        return cfConfigName;
    }

    public void setCfConfigName(String cfConfigName) {
        this.cfConfigName = cfConfigName;
    }

    public String getCfConfigValue() {
        return cfConfigValue;
    }

    public void setCfConfigValue(String cfConfigValue) {
        this.cfConfigValue = cfConfigValue;
    }

    public String getCfConfigDescription() {
        return cfConfigDescription;
    }

    public void setCfConfigDescription(String cfConfigDescription) {
        this.cfConfigDescription = cfConfigDescription;
    }


    public Date getCfUpdateTime() {
        return cfUpdateTime;
    }

    public void setCfUpdateTime(Date cfUpdateTime) {
        this.cfUpdateTime = cfUpdateTime;
    }
}
