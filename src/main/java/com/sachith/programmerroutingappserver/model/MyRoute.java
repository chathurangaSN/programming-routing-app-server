package com.sachith.programmerroutingappserver.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


    @Entity
    @Table(name = "my_route")
    @Data
    public class MyRoute {

        @Id
        @GeneratedValue
        private Long id;

        private String name;

    }

