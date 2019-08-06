package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Resource;
import com.org.smartgrouping.model.Role;
import com.org.smartgrouping.service.ResourceService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date :2019-07-16. This class process the Resource Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@RestController
@RequestMapping(path = "/resource/")
public class ResourceController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private JsonFileObjectUtil jsonFileObject;
    /**
     * Date :2019-07-16. This method used for save resource data using CrudRepository
     * in springframework
     *
     * @param resource
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String saveResource(@ModelAttribute Resource resource){
        if (log.isDebugEnabled()) {
            log.debug("ResourceController saveResource method save and update resource");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = resourceService.saveResource(resource);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by saveResource in ResourceController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for assign role data using CrudRepository
     * in springframework
     *
     * @param resource, role
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "assignRole", method = RequestMethod.POST)
    @ResponseBody
    public String assignResource(@ModelAttribute Resource resource, @ModelAttribute Role role){
        if (log.isDebugEnabled()) {
            log.debug("ResourceController assignResource method assign role to resource");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = resourceService.assignResource(resource, role);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by saveResource in ResourceController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for delete resource data using CrudRepository
     * in springframework
     *
     * @param resource
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteResource(@ModelAttribute Resource resource){
        if (log.isDebugEnabled()) {
            log.debug("ResourceController deleteResource method delete resource");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = resourceService.deleteResource(resource);
            if (saveStatus) {
                saveMsg = "Delete Success";
            } else if (!saveStatus) {
                saveMsg = "Delete error";
            }
        } catch (Exception e) {
            log.error("error  occurred by deleteResource in ResourceController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-16. This method used for get all resources data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public void findAllResources(HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("ResourceController findAllResources method  get all role data");
        }
        try {
            Iterable<Resource> allResources = resourceService.findAllResources();
            String m = new Gson().toJson(allResources);
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
     * Date :2019-07-21. This method used for get all resources data by id
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findResourcesById/{id}", method = RequestMethod.GET)
    public void findResourcesById(@PathVariable int id, HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("ResourceController findAllResources method  get all role data");
        }
        try {
            Iterable<Resource> allResources = resourceService.findResourcesById(id);
            String m = new Gson().toJson(allResources);
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
