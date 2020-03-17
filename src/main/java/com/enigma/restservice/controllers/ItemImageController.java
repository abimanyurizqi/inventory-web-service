package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.models.ItemImageModel;
import com.enigma.restservice.models.ResponseMessage;
import com.enigma.restservice.services.ItemImageService;
import com.enigma.restservice.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/items/{id}/images")
@RestController
public class ItemImageController {

    @Autowired
    private Service<Item> service;

    @Autowired
    private ItemImageService itemImageService;

    @PostMapping
    public ResponseMessage<ItemImageModel> upload(@PathVariable Integer id,
                                                  @RequestParam MultipartFile file) throws IOException {
        Item entity = service.findById(id);
        Path path = itemImageService.save(entity, file);

        ItemImageModel model = ItemImageModel.from(id, path);
        return ResponseMessage.success(model);
    }

    @GetMapping
    public ResponseMessage<List<ItemImageModel>> list(@PathVariable Integer id) throws IOException {

        Item entity = service.findById(id);
        List<Path> paths = itemImageService.list(entity);

        List<ItemImageModel> models = new ArrayList<>();
        for (Path path : paths
        ) {
            ItemImageModel model = ItemImageModel.from(id, path);
            models.add(model);
        }
        return ResponseMessage.success(models);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> download(@PathVariable Integer id,
                                             @PathVariable String filename) throws IOException {
        Item entity = service.findById(id);

        Resource resource = itemImageService.load(entity, filename);

        String mediaTypes = URLConnection.guessContentTypeFromName(resource.getFilename());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaTypes))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{filename}")
    public ResponseMessage<Boolean> delete(@PathVariable Integer id,
                                           @PathVariable String filename) throws IOException{
        Item entity = service.findById(id);
        itemImageService.delete(entity, filename);
        return ResponseMessage.success(Boolean.TRUE);
    }
}
