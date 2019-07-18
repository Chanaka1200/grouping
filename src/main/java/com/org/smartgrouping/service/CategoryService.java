package com.org.smartgrouping.service;

import com.org.smartgrouping.model.Category;
import com.org.smartgrouping.repository.CategoryRepository;
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
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;
    /**
     * saveCategory method is get all data in category updated data
     *
     * @param category
     * @return saveStatus
     */
    public Boolean saveCategory(Category category) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("CategoryService saveCategory method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("CategoryService saveCategory method save new category to the database.");
        }
        categoryRepository.save(category);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * deleteCategory method is delete category data
     *
     * @param category
     * @return saveStatus
     */
    public Boolean deleteCategory(Category category) {
        if (log.isDebugEnabled()) {
            log.debug("CategoryService deleteCategory method calling.");
        }
        Boolean saveStatus = false;
        if (log.isDebugEnabled()) {
            log.debug("CategoryService deleteCategory method delete category to the database.");
        }
        categoryRepository.delete(category);
        saveStatus = true;
        return saveStatus;
    }
    /**
     * findAllCategories method is get all data in Categories
     *
     * @return allCategories
     */
    public Iterable<Category> findAllCategories() {
        Iterable<Category> allCategories = categoryRepository.findAll();
        return allCategories;
    }
}
