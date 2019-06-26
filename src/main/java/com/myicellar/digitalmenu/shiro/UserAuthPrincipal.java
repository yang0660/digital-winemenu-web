package com.myicellar.digitalmenu.shiro;

import com.myicellar.digitalmenu.enums.DeviceTypeEnum;
import com.myicellar.digitalmenu.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
public class UserAuthPrincipal implements Serializable {
    private static final long serialVersionUID = -9184247409709140766L; //do not modify!!!

    /**
     * 身份
     */
    private String principal;
    /**
     * 凭证
     */
    private transient String credentials;
    /**
     * 盐值
     */
    private transient String salt;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户类型 EMPLOYEE((byte) 1, "员工"), SUPPLIER((byte) 2, "供应商"), DEALER((byte) 3, "经销商")
     */
    private UserTypeEnum userType = UserTypeEnum.EMPLOYEE;
    /**
     * 设备类型
     */
    private DeviceTypeEnum deviceType;
    /**
     * 业务属性保留域
     */
    private String reserve;
    /**
     * 角色
     */
    private Set<String> roleNames;
}
