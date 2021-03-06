package com.org.smartgrouping.repository;

import com.org.smartgrouping.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    Iterable<Resource> findAllByResourceId(int id);
}
