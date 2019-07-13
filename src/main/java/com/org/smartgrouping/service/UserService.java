package com.org.smartgrouping.service;

import com.org.smartgrouping.model.User;
import com.org.smartgrouping.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
     * findAllUser method is find all data in User
     *
     * @return allUser
     */
    public Iterable<User> findAllUser() {
        Iterable<User> allUser = userRepository.findAll();
        return allUser;
    }
}
