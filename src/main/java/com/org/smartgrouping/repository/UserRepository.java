package com.org.smartgrouping.repository;

import com.org.smartgrouping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Date :2019-07-13. This class process the crud operation extends
 * JPARepository using springframework
 *
 * @author Chanaka Bandara
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
