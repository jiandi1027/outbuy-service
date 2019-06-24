/*
* SysTest.java
* Copyright(C) cjd 
* All right Reserved
* 2019-06-24 created
*/
package com.cjdjyf.outbuyservice.pojo.sys;

import com.cjdjyf.outbuyservice.base.BaseEntity;
import java.io.Serializable;

/**
* @author cjd
* @version 1.0 2019-06-24
 */
public class SysTest extends BaseEntity implements Serializable {
    /** */
    private String username;

    /** */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}
