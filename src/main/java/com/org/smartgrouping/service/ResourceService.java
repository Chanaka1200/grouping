package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Resource;
import com.org.smartgrouping.repository.ResourceRepository;
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
     * findAllResources method is get all data in Resources
     *
     * @return allResources
     */
    public Iterable<Resource> findAllResources() {
        Iterable<Resource> allResources = resourceRepository.findAll();
        return allResources;
    }

}
