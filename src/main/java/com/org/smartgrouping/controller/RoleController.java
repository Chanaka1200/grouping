package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Role;
import com.org.smartgrouping.service.RoleService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Date :2019-07-16. This class process the Role Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@Controller
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private JsonFileObjectUtil jsonFileObject;
    /**
     * Date :2019-07-16. This method used for save role data using CrudRepository
     * in springframework
     *
     * @param role
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "saveRole", method = RequestMethod.POST)
    @ResponseBody
    public String saveRole(@ModelAttribute Role role){
        if (log.isDebugEnabled()) {
            log.debug("RoleController saveRole method save and update role");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = roleService.saveRole(role);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by saveRole in RoleController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for delete role data using CrudRepository
     * in springframework
     *
     * @param role
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole(@ModelAttribute Role role){
        if (log.isDebugEnabled()) {
            log.debug("RoleController deleteRole method delete role");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = roleService.deleteRole(role);
            if (saveStatus) {
                saveMsg = "Delete Success";
            } else if (!saveStatus) {
                saveMsg = "Delete error";
            }
        } catch (Exception e) {
            log.error("error  occurred by deleteRole in RoleController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-16. This method used for get all roles data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAllRoles", method = RequestMethod.GET)
    public void findAllRoles(HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("RoleController findAllRoles method  get all role data");
        }
        try {
            Iterable<Role> allRoles = roleService.findAllRoles();
            String m = new Gson().toJson(allRoles);
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
