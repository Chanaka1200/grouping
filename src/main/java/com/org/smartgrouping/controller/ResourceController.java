package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Resource;
import com.org.smartgrouping.service.ResourceService;
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
 * Date :2019-07-16. This class process the Resource Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@Controller
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
    @RequestMapping(value = "saveResource", method = RequestMethod.POST)
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
     * Date :2019-07-16. This method used for get all resources data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAllResources", method = RequestMethod.GET)
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
}
