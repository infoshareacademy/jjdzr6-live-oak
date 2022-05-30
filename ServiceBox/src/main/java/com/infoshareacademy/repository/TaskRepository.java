package com.infoshareacademy.repository;

import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.model.Task;

import java.util.ArrayList;

public class TaskRepository {
    private DatabaseInterface db;

    public TaskRepository(DatabaseInterface db) {
        this.db = db;
    }

    public Task findById(int id) {
        // TODO
        return null;
    }

    public ArrayList<Task> findAll() {
        // TODO
        return null;
    }
}
