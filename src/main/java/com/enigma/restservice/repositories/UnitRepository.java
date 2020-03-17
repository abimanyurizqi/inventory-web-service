package com.enigma.restservice.repositories;

import com.enigma.restservice.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Integer>, UnitRepositoryCustom {
    public List<Unit> findByNameContainingIgnoreCase(String name);
}
