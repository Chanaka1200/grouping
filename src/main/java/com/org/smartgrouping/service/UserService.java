package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Team;
import com.org.smartgrouping.model.User;
import com.org.smartgrouping.repository.TeamRepository;
import com.org.smartgrouping.repository.UserRepository;
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
    public Boolean assignUser(User user, Team team) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("UserService saveUser method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("UserService saveUser method save new User.Existing User to the database.");
        }

        User users = new User();
        users.setUserName(user.getUserName());
        users.setUserAddress(user.getUserAddress());
        users.setUserEmail(user.getUserEmail());
        users.setCreatedDate(user.getCreatedDate());
        users.setUserStatus(user.getUserStatus());

        Team teams = new Team();
        teams.setTeamName(team.getTeamName());
        teams.setCreatedAt(team.getCreatedAt());
        teams.setTeamStatus(team.getTeamStatus());

        users.getUserTeams().add(teams);
        userRepository.save(users);

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
}
