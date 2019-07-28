package com.org.smartgrouping.service;

import com.org.smartgrouping.model.*;
import com.org.smartgrouping.repository.RoleRepository;
import com.org.smartgrouping.repository.ServiceRepository;
import com.org.smartgrouping.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

/**
 * Date :2019-07-16. This class process the crud operation Service class
 *
 * @author Chanaka Bandara
 */
@Component
public class TeamService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private RoleRepository roleRepository;
    /**
     * saveTeam method is get all data in Team updated data
     *
     * @param team
     * @return saveStatus
     */
    public Boolean saveTeam(Team team) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("TeamService saveTeam method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("TeamService saveTeam method save new team to the database.");
        }
        teamRepository.save(team);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * subscribeService method is team subscribe for each services
     *
     * @param serviceTeamWrapper
     * @return userList
     */
    public Boolean subscribeService(ServiceTeamWrapper serviceTeamWrapper) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("subscribeService subscribeService method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("subscribeService subscribeService method save new Team. And Subscribe services.");
        }
        Team team = serviceTeamWrapper.getTeam();
        List<Service> service = serviceTeamWrapper.getServices();
        for (Service serviceArray : service){
            Service service1 = serviceRepository.findById(serviceArray.getServiceId()).get();
            team.getTeamServices().add(service1);
        }
        teamRepository.save(team);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * subscribeRole method is team subscribe for each roles
     *
     * @param teamRoleWrapper
     * @return userList
     */
    public Boolean subscribeRole(TeamRoleWrapper teamRoleWrapper) {
        if (log.isDebugEnabled()) {
            log.debug("subscribeService subscribeRole method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("subscribeService subscribeRole method save new Team. And Subscribe roles.");
        }
        Team team = teamRoleWrapper.getTeam();
        List<Role> roles = teamRoleWrapper.getRoles();
        for (Role roleArray : roles){
            Role role = roleRepository.findById(roleArray.getRoleId()).get();
            team.getTeamRoles().add(role);
        }
        teamRepository.save(team);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * deleteResource method is delete team data
     *
     * @param team
     * @return saveStatus
     */
    public Boolean deleteTeam(Team team) {
        if (log.isDebugEnabled()) {
            log.debug("TeamService deleteTeam method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("TeamService deleteTeam method delete team to the database.");
        }
        teamRepository.delete(team);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * findAllTeam method is get all data in Team
     *
     * @return allTeams
     */
    public Iterable<Team> findAllTeam() {
        Iterable<Team> allTeams = teamRepository.findAll();
        return allTeams;
    }
    /**
     * findTeamById method is get all data in team by id
     *
     * @param id
     * @return allTeams
     */
    public Iterable<Team> findTeamById(int id) {
        Iterable<Team> teamsById = teamRepository.findAllByTeamId(id);
        return teamsById;
    }
}
