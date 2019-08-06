package com.org.smartgrouping.service;

import com.org.smartgrouping.model.*;
import com.org.smartgrouping.repository.RoleRepository;
import com.org.smartgrouping.repository.TeamRepository;
import com.org.smartgrouping.repository.UserRepository;
import org.hibernate.annotations.NaturalId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date :2019-07-13. This class process the crud operation Service class
 *
 * @author Chanaka Bandara
 */
@Component
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RoleRepository roleRepository;
    /**
     * saveUser method is get all data in User updated data
     *
     * @param user
     * @return userList
     */
    public Boolean saveUser(User user) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("UserService saveUser method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("UserService saveUser method save new User.Existing User to the database.");
        }

        userRepository.save(user);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * subscribeTeam method is subscribe teams
     *
     * @param user, team
     * @return userList
     */
    public Boolean subscribeTeam(User user, Team team, Role role) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("UserService subscribeTeam method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("UserService subscribeTeam method save new User.Subscribe team.");
        }

        User users = new User();
        users.setUserName(user.getUserName());
        users.setUserAddress(user.getUserAddress());
        users.setUserEmail(user.getUserEmail());
        users.setCreatedDate(user.getCreatedDate());
        users.setUserStatus(user.getUserStatus());

/*        Team teams = new Team();
        teams.setTeamName(team.getTeamName());
        teams.setCreatedAt(team.getCreatedAt());
        teams.setTeamStatus(team.getTeamStatus());*/
        Team subscribeTeam = teamRepository.findById(team.getTeamId()).get();

        Role relatedRole = roleRepository.findById(role.getRoleId()).get();

        UserTeamRoleLink userTeamRoleLink = new UserTeamRoleLink();
        userTeamRoleLink.setLinkUser(users);
        userTeamRoleLink.setLinkTeam(subscribeTeam);
        userTeamRoleLink.setLinkRole(relatedRole);

        users.getUserTeamRoleLink().add(userTeamRoleLink);
        //users.getUserTeams().add(team1);
        userRepository.save(users);

        saveStatus = true;
        return saveStatus;
    }
    /**
     * subscribeRole method is subscribe roles
     *
     * @param userRoleWrapper
     * @return userList
     */
    public Boolean subscribeRole(UserRoleWrapper userRoleWrapper) {
        if (log.isDebugEnabled()) {
            log.debug("UserService subscribeRole method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("UserService subscribeRole method save new User. And Subscribe roles.");
        }
        User user = userRoleWrapper.getUser();
        List<Role> roles = userRoleWrapper.getRoles();
        for (Role roleArray : roles){
            Role role1 = roleRepository.findById(roleArray.getRoleId()).get();
            user.getUserRoles().add(role1);
        }

        userRepository.save(user);

        saveStatus = true;
        return saveStatus;
    }
    /**
     * deleteResource method is delete user data
     *
     * @param user
     * @return saveStatus
     */
    public Boolean deleteUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("UserService deleteUser method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("UserService deleteUser method delete User.Existing User to the database.");
        }

        userRepository.delete(user);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * findAllUser method is find all data in User
     *
     * @return allUser
     */
    public Iterable<User> findAllUser() {
        Iterable<User> allUser = userRepository.findAll();
        return allUser;
    }
    /**
     * findAllUser method is find all data in User
     *
     * @param id
     * @return userById
     */
    public Iterable<User> findUsersById(int id) {
        Iterable<User> userById = userRepository.findAllByUserId(id);
        return userById;
    }
}
