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
        Service services = new Service();
        services.setServiceName(service.getServiceName());
        services.setServiceStatus(service.getServiceStatus());

        Category categorys =  categoryRepository.findById(category.getCategoryId()).get();

        services.setCategory(categorys);
        categorys.getService().add(services);
        serviceRepository.save(services);
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
    /**
     * findServicesById method is get all data in Services
     *
     * @param id
     * @return servicesById
     */
    public Iterable<Service> findServicesById(int id) {
        Iterable<Service> servicesById = serviceRepository.findAllByServiceId(id);
        return servicesById;
    }
}
