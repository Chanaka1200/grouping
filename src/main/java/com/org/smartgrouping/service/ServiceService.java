package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Service;
import com.org.smartgrouping.repository.ServiceRepository;
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
public class ServiceService {
    private static final Logger log = LoggerFactory.getLogger(ServiceService.class);
    @Autowired
    private ServiceRepository serviceRepository;
    /**
     * saveService method is get all data in Services updated data
     *
     * @param service
     * @return saveStatus
     */
    public Boolean saveService(Service service) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("ServiceService saveService method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("ServiceService saveService method save new service to the database.");
        }
        serviceRepository.save(service);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * findAllServices method is get all data in Services
     *
     * @return allServices
     */
    public Iterable<Service> findAllServices() {
        Iterable<Service> allServices = serviceRepository.findAll();
        return allServices;
    }
}
