package com.org.smartgrouping.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resource")
public class Resource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resources_id")
    private int resourceId;

    @Column(name = "resources_name")
    private String resourceName;

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
