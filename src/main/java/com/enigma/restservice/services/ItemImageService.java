package com.enigma.restservice.services;


import com.enigma.restservice.entities.Item;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ItemImageService {

    public Path save(Item id, MultipartFile file) throws IOException;

    public Resource load(Item id, String filename) throws IOException;

    public List<Path> list(Item id) throws IOException;

    public void delete(Item id, String filename) throws IOException;

}
