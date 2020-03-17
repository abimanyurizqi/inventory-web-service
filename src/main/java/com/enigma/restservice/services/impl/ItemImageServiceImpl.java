package com.enigma.restservice.services.impl;

import com.enigma.restservice.configs.ApplicationProperties;
import com.enigma.restservice.entities.Item;
import com.enigma.restservice.services.ItemImageService;
import org.springframework.core.io.Resource;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ItemImageServiceImpl implements ItemImageService {

    @Autowired
    private ApplicationProperties properties;

    private Path parentDir;

    @PostConstruct
    private void init () throws IOException {
        parentDir = Paths.get(properties.getDataDir(), "item")
                .toAbsolutePath().normalize();

        Files.createDirectories(parentDir);
    }


    @Override
    public Path save(Item id, MultipartFile file) throws IOException {
        Path dir = parentDir.resolve(id.getId().toString());
        Files.createDirectories(dir);

        Path target = dir.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return target;
    }

    @Override
    public Resource load(Item id, String filename) throws IOException {
        Path target = parentDir.resolve(id.getId().toString()).resolve(filename);
        Resource resource = new UrlResource(target.toUri());
        return resource;
    }

    @Override
    public List<Path> list(Item id) throws IOException {
        Path dir = parentDir.resolve(id.getId().toString());
        return Files.isDirectory(dir) ?
                Files.list(dir).collect(Collectors.toList()) :
        Collections.EMPTY_LIST;
    }

    @Override
    public void delete(Item id, String filename) throws IOException {
        Path target = parentDir.resolve(id.getId().toString()).resolve(filename);
        Files.delete(target);
    }
}
