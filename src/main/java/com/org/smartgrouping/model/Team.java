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

  /*  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "team")
    private Set<User> users = new HashSet<>();*/

    @ManyToMany(mappedBy = "userTeams")
    Set<User> users = new HashSet<>();

    /*public void addUsers(User user) {
        this.users.add(user);
    }*/
   /* public Team(String teamName, Date createdAt, Boolean teamStatus){
        this.teamName = teamName;
        this.createdAt = createdAt;
        this.teamStatus = teamStatus;
    }*/

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
