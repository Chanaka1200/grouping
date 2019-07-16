package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Team;
import com.org.smartgrouping.model.User;
import com.org.smartgrouping.service.UserService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Date :2019-07-13. This class process the User Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@Controller
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
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
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
     * Date :2019-07-16. This method used for save user data wih team data composite table using CrudRepository
     * in springframework
     *
     * @param user team
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "assignUser", method = RequestMethod.POST)
    @ResponseBody
    public String assignUser(@ModelAttribute User user, @ModelAttribute Team team){
        if (log.isDebugEnabled()) {
            log.debug("UserController assignUser method save and update user and team composite");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = userService.assignUser(user, team);
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
    @RequestMapping(value = "findAllUsers", method = RequestMethod.GET)
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
}
