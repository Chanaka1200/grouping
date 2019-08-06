package com.org.smartgrouping.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "team_status")
    private Boolean teamStatus;

   /* @ManyToMany(mappedBy = "userTeams")
    Set<User> users = new HashSet<>();*/

    @OneToMany(mappedBy = "linkTeam", cascade = CascadeType.ALL)
    private Set<UserTeamRoleLink> userTeamRoleLink;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "team_service",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"))
    private Set<Service> teamServices = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "team_role",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> teamRoles = new HashSet<>();

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Boolean teamStatus) {
        this.teamStatus = teamStatus;
    }

    /*public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }*/

    public Set<UserTeamRoleLink> getUserTeamRoleLink() {
        return userTeamRoleLink;
    }

    public void setUserTeamRoleLink(Set<UserTeamRoleLink> userTeamRoleLink) {
        this.userTeamRoleLink = userTeamRoleLink;
    }

    public Set<Service> getTeamServices() {
        return teamServices;
    }

    public void setTeamServices(Set<Service> teamServices) {
        this.teamServices = teamServices;
    }

    public Set<Role> getTeamRoles() {
        return teamRoles;
    }

    public void setTeamRoles(Set<Role> teamRoles) {
        this.teamRoles = teamRoles;
    }
}
