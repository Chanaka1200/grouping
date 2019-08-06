package com.org.smartgrouping.model;

import org.omg.CORBA.UserException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roleResources")
    Set<Resource> resources = new HashSet<>();

    @ManyToMany(mappedBy = "userRoles")
    Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "teamRoles")
    Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "linkRole", cascade = CascadeType.ALL)
    private Set<UserTeamRoleLink> userTeamRoleLink;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<UserTeamRoleLink> getUserTeamRoleLink() {
        return userTeamRoleLink;
    }

    public void setUserTeamRoleLink(Set<UserTeamRoleLink> userTeamRoleLink) {
        this.userTeamRoleLink = userTeamRoleLink;
    }
}
