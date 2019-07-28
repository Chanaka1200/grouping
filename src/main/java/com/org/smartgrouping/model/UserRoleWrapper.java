package com.org.smartgrouping.model;

import java.io.Serializable;
import java.util.List;

public class UserRoleWrapper implements Serializable {

    User user;
    List<Role> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
