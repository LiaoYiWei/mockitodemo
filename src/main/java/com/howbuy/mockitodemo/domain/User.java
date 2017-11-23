package com.howbuy.mockitodemo.domain;

import lombok.Data;

import java.util.Objects;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
@Data
public class User {
    private Long id;
    private String name;
    private String mobile;
    private String password;
    private String identity;

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
