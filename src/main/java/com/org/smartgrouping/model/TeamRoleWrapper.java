package com.org.smartgrouping.model;

import java.io.Serializable;
import java.util.List;

public class TeamRoleWrapper implements Serializable {
    Team team;
    List<Role> roles;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
