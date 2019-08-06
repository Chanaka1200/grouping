package com.org.smartgrouping.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_team")
public class UserTeamRoleLink implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team linkTeam;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User linkUser;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role linkRole;

    public Team getLinkTeam() {
        return linkTeam;
    }

    public void setLinkTeam(Team linkTeam) {
        this.linkTeam = linkTeam;
    }

    public User getLinkUser() {
        return linkUser;
    }

    public void setLinkUser(User linkUser) {
        this.linkUser = linkUser;
    }

    public Role getLinkRole() {
        return linkRole;
    }

    public void setLinkRole(Role linkRole) {
        this.linkRole = linkRole;
    }
}
