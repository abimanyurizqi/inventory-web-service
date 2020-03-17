package com.enigma.restservice.models;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ItemImageModel {
    private String name;
    private String url;
    private String contentType;
    private Long size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public static ItemImageModel from(Integer id, Path path) throws IOException {
        String name = path.getFileName().toString();
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/items/" + id + "/images/")
                .path(path.getFileName().toString())
                .toUriString();
        ItemImageModel model = new ItemImageModel();
        model.setName(name);
        model.setUrl(uri);
        model.setContentType(Files.probeContentType(path));
        model.setSize(Files.size(path));
        return model;
    }
}
