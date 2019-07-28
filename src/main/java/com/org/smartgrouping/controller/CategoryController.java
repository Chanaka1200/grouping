package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Category;
import com.org.smartgrouping.service.CategoryService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date :2019-07-16. This class process the Category Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@RestController
@RequestMapping(path = "/category/")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JsonFileObjectUtil jsonFileObject;
    /**
     * Date :2019-07-16. This method used for save category data using CrudRepository
     * in springframework
     *
     * @param category
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String saveCategory(@ModelAttribute Category category){
        if (log.isDebugEnabled()) {
            log.debug("CategoryController saveCategory method save and update category");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = categoryService.saveCategory(category);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by saveCategory in CategoryController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for delete category data using CrudRepository
     * in springframework
     *
     * @param category
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCategory(@ModelAttribute Category category){
        if (log.isDebugEnabled()) {
            log.debug("CategoryController deleteCategory method delete category");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = categoryService.deleteCategory(category);
            if (saveStatus) {
                saveMsg = "Delete Success";
            } else if (!saveStatus) {
                saveMsg = "Delete error";
            }
        } catch (Exception e) {
            log.error("error  occurred by deleteCategory in CategoryController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-16. This method used for get all categories data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public void findAllCategories(HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("CategoryController findAllCategories method  get all categories data");
        }
        try {
            Iterable<Category> allCategories = categoryService.findAllCategories();
            String m = new Gson().toJson(allCategories);
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
     * Date :2019-07-21. This method used for get all categories data by id
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findCategoryById/{id}", method = RequestMethod.GET)
    public void findCategoriesById(@PathVariable int id, HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("CategoryController findCategoriesById method  get all categories data by id");
        }
        try {
            Iterable<Category> allCategories = categoryService.findCategoriesById(id);
            String m = new Gson().toJson(allCategories);
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
