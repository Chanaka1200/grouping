package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Category;
import com.org.smartgrouping.model.Service;
import com.org.smartgrouping.repository.CategoryRepository;
import com.org.smartgrouping.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

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
    @Autowired
    private CategoryRepository categoryRepository;
    /**
     * saveService method is get all data in Services updated data
     *
     * @param service
     * @return saveStatus
     */
    public Boolean saveService(Service service, Category category) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("ServiceService saveService method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("ServiceService saveService method save new service to the database.");
        }
        Service service1 = new Service();
        service1.setServiceName(service.getServiceName());
        service1.setServiceStatus(service.getServiceStatus());

        Category category1 = new Category();
        category1.setCategoryName(category.getCategoryName());

        service1.setCategory(category1);
        category1.getService().add(service1);
        serviceRepository.save(service1);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * deleteResource method is delete service data
     *
     * @param service
     * @return saveStatus
     */
    public Boolean deleteService(Service service) {
        if (log.isDebugEnabled()) {
            log.debug("ServiceService deleteService method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("ServiceService deleteService method delete service to the database.");
        }
        serviceRepository.delete(service);
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
