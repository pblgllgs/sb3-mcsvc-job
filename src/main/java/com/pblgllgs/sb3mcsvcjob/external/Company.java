package com.pblgllgs.sb3mcsvcjob.external;
/*
 *
 * @author pblgl
 * Created on 05-11-2024
 *
 */

public class Company {

    private Long id;
    private String name;
    private String description;


    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}