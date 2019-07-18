package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Team;
import com.org.smartgrouping.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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
}
