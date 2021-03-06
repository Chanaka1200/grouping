package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Category;
import com.org.smartgrouping.model.Service;
import com.org.smartgrouping.service.ServiceService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date :2019-07-13. This class process the Service Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@RestController
@RequestMapping(path = "/service/")
public class ServiceController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private JsonFileObjectUtil jsonFileObject;
    /**
     * Date :2019-07-16. This method used for save service data using CrudRepository
     * in springframework
     *
     * @param service
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String saveService(@ModelAttribute Service service, @ModelAttribute Category category){
        if (log.isDebugEnabled()) {
            log.debug("ServiceController saveService method save and update service");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = serviceService.saveService(service, category);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by saveService in ServiceController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for delete service data using CrudRepository
     * in springframework
     *
     * @param service
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteService(@ModelAttribute Service service){
        if (log.isDebugEnabled()) {
            log.debug("ServiceController deleteService method delete service");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = serviceService.deleteService(service);
            if (saveStatus) {
                saveMsg = "Delete Success";
            } else if (!saveStatus) {
                saveMsg = "Delete error";
            }
        } catch (Exception e) {
            log.error("error  occurred by deleteService in ServiceController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-16. This method used for get all services data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public void findAllServices(HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("ServiceController findAllServices method  get all service data");
        }
        try {
            Iterable<Service> allServices = serviceService.findAllServices();
            String m = new Gson().toJson(allServices);
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
     * Date :2019-07-21. This method used for get all services data by id
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findServiceById/{id}", method = RequestMethod.GET)
    public void findServicesById(@PathVariable int id, HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("ServiceController findServicesById method  get all service data by id");
        }
        try {
            Iterable<Service> allServices = serviceService.findServicesById(id);
            String m = new Gson().toJson(allServices);
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
