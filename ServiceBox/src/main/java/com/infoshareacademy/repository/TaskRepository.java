package com.infoshareacademy.repository;

import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.model.Task;

import java.util.ArrayList;

public class TaskRepository {
    private final DatabaseInterface db;

    public TaskRepository(DatabaseInterface db) {
        this.db = db;
    }

    public Task findById(int id) throws Exception {
        for (Task task : db.getTasks()) {
            if (id == task.getId()) {
                return task;
            }
        }

        throw new Exception("Nie znaleziono zlecenia o podanym numerze (" + id + ")");
    }

    public ArrayList<Task> findAll() {
        return db.getTasks();
    }

    public int getNextId() {
        return db.getTasks().size() + 1;
    }
}
