package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.ServiceTeamWrapper;
import com.org.smartgrouping.model.Team;
import com.org.smartgrouping.model.TeamRoleWrapper;
import com.org.smartgrouping.service.TeamService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Date :2019-07-16. This class process the Team Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@RestController
@RequestMapping(path = "/team/")
public class TeamController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private TeamService teamService;
    @Autowired
    private JsonFileObjectUtil jsonFileObject;
    /**
     * Date :2019-07-16. This method used for save team data using CrudRepository
     * in springframework
     *
     * @param team
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String saveTeam(@ModelAttribute Team team){
        if (log.isDebugEnabled()) {
            log.debug("TeamController saveTeam method save and update team");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = teamService.saveTeam(team);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by saveTeam in TeamController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-16. This method used for save team data using CrudRepository
     * in springframework
     *
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "subscribeService", method = RequestMethod.POST)
    @ResponseBody
    public String subscribeService(@RequestBody ServiceTeamWrapper serviceTeamWrapper){
        if (log.isDebugEnabled()) {
            log.debug("TeamController subscribeService method subscribe services for team");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = teamService.subscribeService(serviceTeamWrapper);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by subscribeService in TeamController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-16. This method used for save team data using CrudRepository
     * in springframework
     *
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "subscribeTeamRole", method = RequestMethod.POST)
    @ResponseBody
    public String subscribeRole(@RequestBody TeamRoleWrapper teamRoleWrapper){
        if (log.isDebugEnabled()) {
            log.debug("TeamController subscribeRole method subscribe roles for team");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = teamService.subscribeRole(teamRoleWrapper);
            if (saveStatus) {
                saveMsg = "Save Success";
            } else if (!saveStatus) {
                saveMsg = "Save error";
            }
        } catch (Exception e) {
            log.error("error  occurred by subscribeRole in TeamController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-18. This method used for delete team data using CrudRepository
     * in springframework
     *
     * @param team
     * @return saveMsg
     * @author Chanaka Bandara
     *
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTeam(@ModelAttribute Team team){
        if (log.isDebugEnabled()) {
            log.debug("TeamController deleteTeam method delete team");
        }
        String saveMsg = "";
        Boolean saveStatus = false;
        try {
            saveStatus = teamService.deleteTeam(team);
            if (saveStatus) {
                saveMsg = "Delete Success";
            } else if (!saveStatus) {
                saveMsg = "Delete error";
            }
        } catch (Exception e) {
            log.error("error  occurred by deleteTeam in TeamController " + e);
            saveMsg = "error occurred by" + e;
        }
        return saveMsg;
    }
    /**
     * Date :2019-07-16. This method used for get all teams data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public void findAllTeams(HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("TeamController findAllTeams method  get all teams data");
        }
        try {
            Iterable<Team> allTeams = teamService.findAllTeam();
            String m = new Gson().toJson(allTeams);
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
     * Date :2019-07-21. This method used for get all teams data by id
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findTeamsById/{id}", method = RequestMethod.GET)
    public void findTeamsById(@PathVariable int id, HttpServletResponse httpservletResponse, HttpServletRequest httpServletRequest) {
        if (log.isDebugEnabled()) {
            log.debug("TeamController findAllTeams method  get all teams data");
        }
        try {
            Iterable<Team> allTeams = teamService.findTeamById(id);
            String m = new Gson().toJson(allTeams);
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
