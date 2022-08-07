package com.infoshareacademy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infoshareacademy.entity.Entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T extends Entity> {
    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<ArrayList<T>> typeReference;
    protected List<T> objects;

    protected Repository(String filename, TypeReference<ArrayList<T>> typeReference) {
        this.path = Path.of("src", "main", "resources", filename);
        this.typeReference = typeReference;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());

        loadData();
    }

    /**
     * Find single element by id
     */
    public T find(int id) {
        return objects.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get all elements
     */
    public List<T> findAll() {
        return objects;
    }

    public void add(T object) {
        object.setId(objects.size() + 1);

        // add to collection
        objects.add(object);
    }

    public void add(T object, boolean unique) {
        object.setId(objects.size() + 1);

        if (!unique) {
            objects.add(object);
        } else {
            // add new object only if not exists in database
            boolean exists = objects.contains(object);
            if (!exists) {
                objects.add(object);
            }
        }
    }

    public void save() throws IOException {
        mapper.writeValue(path.toFile(), objects);
    }

    private void loadData() {
        objects = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                objects = mapper.readValue(path.toFile(), typeReference);
            } catch (IOException ignored) {
                // do nothing
            }
        }
    }
}
