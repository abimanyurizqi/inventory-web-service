package com.enigma.restservice.repositories;

import com.enigma.restservice.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>, ItemRepositoryCustom {
    public List<Item> findByNameContainingIgnoreCase(String name);
}
