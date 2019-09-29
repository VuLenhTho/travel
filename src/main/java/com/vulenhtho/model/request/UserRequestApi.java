package com.vulenhtho.model.request;

import java.util.HashSet;
import java.util.Set;

public class UserRequestApi {
    private String userName;
    private String password;
    private Set<RoleRequest> roles = new HashSet<>();

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

    public Set<RoleRequest> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleRequest> roles) {
        this.roles = roles;
    }
}
