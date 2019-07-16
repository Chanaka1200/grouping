package com.org.smartgrouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.org.smartgrouping.model.Team;
import com.org.smartgrouping.service.TeamService;
import com.org.smartgrouping.util.JsonFileObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Date :2019-07-16. This class process the Team Live crud operation
 * controller class
 *
 * @author Chanaka Banadra
 */
@Controller
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
    @RequestMapping(value = "saveTeam", method = RequestMethod.POST)
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
     * Date :2019-07-16. This method used for get all teams data
     * CrudRepository using springframework
     *
     * @author Chanaka Bandara
     */
    @RequestMapping(value = "findAllTeams", method = RequestMethod.GET)
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

}
