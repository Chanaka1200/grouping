package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Role;
import com.org.smartgrouping.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Date :2019-07-16. This class process the crud operation Service class
 *
 * @author Chanaka Bandara
 */
@Component
public class RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private RoleRepository roleRepository;
    /**
     * saveRole method is get all data in Role updated data
     *
     * @param role
     * @return saveStatus
     */
    public Boolean saveRole(Role role) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("RoleService saveRole method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("RoleService saveRole method save new role to the database.");
        }
        roleRepository.save(role);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * deleteResource method is delete role data
     *
     * @param role
     * @return saveStatus
     */
    public Boolean deleteRole(Role role) {
        if (log.isDebugEnabled()) {
            log.debug("RoleService deleteRole method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("RoleService deleteRole method delete role to the database.");
        }
        roleRepository.delete(role);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * findAllRoles method is get all data in Roles
     *
     * @return allRoles
     */
    public Iterable<Role> findAllRoles() {
        Iterable<Role> allRoles = roleRepository.findAll();
        return allRoles;
    }
    /**
     * findRolesById method is get all data in Roles
     *
     * @param id
     * @return rolesById
     */
    public Iterable<Role> findRolesById(int id) {
        Iterable<Role> rolesById = roleRepository.findAllByRoleId(id);
        return rolesById;
    }
}
