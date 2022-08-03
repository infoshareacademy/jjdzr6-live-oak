package com.infoshareacademy.core;

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

public abstract class JsonFileHandler<T extends Entity> {
    private Path path;
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<ArrayList<T>> typeReference;

    public JsonFileHandler(String filename, TypeReference<ArrayList<T>> typeReference) {
        this.path = buildPath(filename);
        this.typeReference = typeReference;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
    }

    public List<T> findAll() throws IOException {
        if (Files.exists(path)) {
            return mapper.readValue(path.toFile(), typeReference);
        }

        return new ArrayList<>();
    }

    public void add(T object) throws IOException {
        List<T> objects = findAll();
        object.setId(objects.size() + 1);

        objects.add(object);
        mapper.writeValue(path.toFile(), objects);
    }

    public void add(T object, boolean unique) throws IOException {
        List<T> objects = findAll();
        object.setId(objects.size() + 1);

        if (!unique) {
            objects.add(object);
        } else {
            boolean exists = objects.contains(object);
            if (!exists) {
                objects.add(object);
            }
        }

        mapper.writeValue(path.toFile(), objects);
    }

    private Path buildPath(String filename) {
        return Path.of("src", "main", "resources", filename);
    }
}