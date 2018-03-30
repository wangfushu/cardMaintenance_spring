package gmms.domain.db;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by yangjb on 2017/6/28.
 * helloWorld
 */
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class Users extends IdEntity {
    private String userName;
    private String password;
    private String telphone;
    private String realName;
    private Date gmtCreate;
    private Date gmtModify;
    private Date lastLoginTime;
    private Integer status;
    private String remark;
    private List<Role> roles;
    private List<Role> rolesNoLazy;
    private List<Msg> msgList;
    private List<LoginLog> loginLogList;

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Column(name = "last_login_time")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(targetEntity = Role.class)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id",
            referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",
            referencedColumnName = "id")})
    @Cascade(value = {CascadeType.LOCK})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public List<Role> getRolesNoLazy() {
        return rolesNoLazy;
    }

    public void setRolesNoLazy(List<Role> rolesNoLazy) {
        this.rolesNoLazy = rolesNoLazy;
    }

    @Transient
    public boolean getIsAdmin() {
        return isAdmin();
    }

    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }



    @Column(name = "gmt_create")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Column(name = "gmt_modify")
    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Transient
    public long getRoleId() {
        if (CollectionUtils.isEmpty(this.roles)) {
            return -1;
        }
        return this.roles.get(0).getId();
    }

    @Transient
    public String getRoleName() {
        if (CollectionUtils.isEmpty(this.roles)) {
            return "";
        }
        return this.roles.get(0).getRemark();
    }


    //是否是超级管理员
    @Transient
    public boolean isSuperAdmin() {
        List<Role> roles = this.roles;
        if (this.roles == null) {
            return false;
        }
        for (Role role : roles) {
            if (StringUtils.equalsIgnoreCase(role.getName(), "ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public boolean isAdmin() {
        List<Role> roles = this.roles;
        for (Role role : roles) {
            if (StringUtils.equalsIgnoreCase(role.getName(), "ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public boolean getIsUser() {
        List<Role> roles = this.roles;
        for (Role role : roles) {
            if (StringUtils.equalsIgnoreCase(role.getName(), "ROLE_USER")) {
                return true;
            }
        }
        return false;
    }


    @OneToMany(targetEntity = Msg.class, mappedBy = "users")
    @Cascade({CascadeType.ALL})
    public List<Msg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Msg> msgList) {
        this.msgList = msgList;
    }





    @OneToMany(targetEntity = LoginLog.class, mappedBy = "users")
    @Cascade({CascadeType.ALL})
    public List<LoginLog> getLoginLogList() {
        return loginLogList;
    }

    public void setLoginLogList(List<LoginLog> loginLogList) {
        this.loginLogList = loginLogList;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Users{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", telphone='").append(telphone).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", status=").append(status);
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }

  /*  public static int getRandomNum(int min, int max) {

        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    public static int ss(int areaId) {
        int randomNum = getRandomNum(1, 10);

        //90%投大小单双
        int biliId = 0;
        randomNum = getRandomNum(0, 10);
        if (randomNum >= 1) {
            //投注极大极小的概率降低
            biliId = getRandomNum(1, 10);
            if (biliId % 5 == 0) {
                if (getRandomNum(1, 10) > 3) {
                    biliId++;
                }
            }
        } else {
            //30%投数字
            biliId = getRandomNum(11, 38);
        }

        biliId = biliId + (areaId - 1) * 42;
        return biliId;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandomNum(1, 10));
        }
    }*/
}
