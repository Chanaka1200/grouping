package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Role;
import com.org.smartgrouping.model.Team;
import com.org.smartgrouping.model.User;
import com.org.smartgrouping.model.UserRoleWrapper;
import com.org.smartgrouping.service.UserService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Date :2019-07-13. This class process the User Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@RestController
@RequestMapping(path = "/user/")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private JsonFileObjectUtil jsonFileObject;
    /**
     * Date :2019-07-13. This method used for save user data using CrudRepository
     * in springframework
     *
     * @param user
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@ModelAttribute User user){
        if (log.isDebugEnabled()) {
            log.debug("UserController saveUser method save and update user");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = userService.saveUser(user);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by saveUser in UserController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for delete user data using CrudRepository
     * in springframework
     *
     * @param user
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@ModelAttribute User user){
        if (log.isDebugEnabled()) {
            log.debug("UserController deleteUser method delete user");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = userService.deleteUser(user);
            if (saveStatus) {
                saveMsg = "Delete Success";
            } else if (!saveStatus) {
                saveMsg = "Delete error";
            }
        } catch (Exception e) {
            log.error("error  occurred by deleteUser in UserController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for assign team and user data using CrudRepository
     * in springframework
     *
     * @param user
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "subscribeTeam", method = RequestMethod.POST)
    @ResponseBody
    public String subscribeTeam(@ModelAttribute User user, @ModelAttribute Team team, @ModelAttribute Role role){
        if (log.isDebugEnabled()) {
            log.debug("UserController subscribeTeam method subscribe teams");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = userService.subscribeTeam(user, team, role);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by assignUser in UserController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for assign team and user data using CrudRepository
     * in springframework
     *
     * @param userRoleWrapper
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "subscribeRole", method = RequestMethod.POST)
    @ResponseBody
    public String subscribeRole(@RequestBody UserRoleWrapper userRoleWrapper){
        if (log.isDebugEnabled()) {
            log.debug("UserController subscribeTeam method subscribe teams");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = userService.subscribeRole(userRoleWrapper);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by assignUser in UserController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-13. This method used for get all save user data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public void findAllUsers(HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("UserController findAllUsers method  get all users data");
        }
        try {
            Iterable<User> allUsers = userService.findAllUser();
            String m = new Gson().toJson(allUsers);
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode = new ObjectNode(mapper.getNodeFactory());
            ArrayNode node = mapper.readValue(m, ArrayNode.class);
            objectNode.put("data", node);
            objectNode.put("success", true);
            jsonFileObject.writeJson(httpservletResponse, objectNode, mapper);
        } catch (Exception e) {
            jsonFileObject.writeJsonString(httpservletResponse,
                    "{\"message\":\"" + e.getMessage() + "\",\"success\":false}");
        }
    }
    /**
     * Date :2019-07-21. This method used for get all save user data by id
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findUsersById/{id}", method = RequestMethod.GET)
    public void findUsersById(@PathVariable int id, HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("UserController findAllUsers method  get all users data");
        }
        try {
            Iterable<User> allUsers = userService.findUsersById(id);
            String m = new Gson().toJson(allUsers);
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode = new ObjectNode(mapper.getNodeFactory());
            ArrayNode node = mapper.readValue(m, ArrayNode.class);
            objectNode.put("data", node);
            objectNode.put("success", true);
            jsonFileObject.writeJson(httpservletResponse, objectNode, mapper);
        } catch (Exception e) {
            jsonFileObject.writeJsonString(httpservletResponse,
                    "{\"message\":\"" + e.getMessage() + "\",\"success\":false}");
        }
    }
}
