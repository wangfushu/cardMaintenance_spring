package gmms.domain.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yangjb on 2017/8/6.
 * helloWorld
 */
@Entity
@Table(name = "login_log")
@DynamicInsert
public class LoginLog {
    protected Long id;
    private String loginIp;
    private Date gmtCreate;
    private Users users;

    @Id
    @TableGenerator(name="ID_GENERATOR",
            table="sys_SeqTable",
            pkColumnName="ST_SeqName",
            pkColumnValue="LoginLog.id",
            valueColumnName="ST_SeqValue",
            allocationSize=1,
            initialValue=1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "login_ip")
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Column(name = "gmt_create")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = true)
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
