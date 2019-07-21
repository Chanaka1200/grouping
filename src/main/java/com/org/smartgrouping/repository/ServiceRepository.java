package com.org.smartgrouping.repository;

import com.org.smartgrouping.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Iterable<Service> findAllByServiceId(int id);
}
