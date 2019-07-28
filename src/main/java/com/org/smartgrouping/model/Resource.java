package com.org.smartgrouping.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "resource")
public class Resource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int resourceId;

    @Column(name = "resource_name")
    private String resourceName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_resource",
            joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roleResources = new HashSet<>();

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

    public Set<Role> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(Set<Role> roleResources) {
        this.roleResources = roleResources;
    }
}
