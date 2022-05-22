package com.infoshareacademy.model;

import java.time.LocalDate;

public class Task {

    private Integer taskId;
    private String[] typeRepairs;
    private String repairDescription;
    private String employee;
    private Integer initialCost;
    private LocalDate dateAcceptRepair;
    private LocalDate plannedRepair;
    private LocalDate dateOfRepair;
    private Integer repairCost;
    private String repairsPerformed;
    private LocalDate pickingUp;
    private Vehicle vehicle;
    private User user;

}
