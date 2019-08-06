package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Resource;
import com.org.smartgrouping.model.Role;
import com.org.smartgrouping.repository.ResourceRepository;
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
public class ResourceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceService.class);
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleRepository roleRepository;
    /**
     * saveResource method is get all data in resource updated data
     *
     * @param resource
     * @return saveStatus
     */
    public Boolean saveResource(Resource resource) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("ResourceService saveResource method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("ResourceService saveResource method save new resource to the database.");
        }
        resourceRepository.save(resource);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * deleteResource method is delete category data
     *
     * @param resource
     * @return saveStatus
     */
    public Boolean deleteResource(Resource resource) {
        if (log.isDebugEnabled()) {
            log.debug("ResourceService deleteResource method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("ResourceService deleteResource method delete resource to the database.");
        }
        resourceRepository.delete(resource);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * assignResource method is assing resources data
     *
     * @param resource, role
     * @return saveStatus
     */
    public Boolean assignResource(Resource resource, Role role) {
        if (log.isDebugEnabled()) {
            log.debug("ResourceService assignResource method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("ResourceService assignResource method assign role to resource.");
        }

        Resource assingResource = new Resource();
        assingResource.setResourceName(resource.getResourceName());

        Role assingRole = roleRepository.findById(role.getRoleId()).get();

        assingResource.getRoleResources().add(assingRole);

        resourceRepository.save(assingResource);
        saveStatus = true;
        return saveStatus;

    }
    /**
     * findAllResources method is get all data in Resources
     *
     * @return allResources
     */
    public Iterable<Resource> findAllResources() {
        Iterable<Resource> allResources = resourceRepository.findAll();
        return allResources;
    }
    /**
     * findResourcesById method is get all data in Resources
     *
     * @param id
     * @return resourcesById
     */
    public Iterable<Resource> findResourcesById(int id) {
        Iterable<Resource> resourcesById = resourceRepository.findAllByResourceId(id);
        return resourcesById;
    }
}
