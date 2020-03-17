package com.enigma.restservice.repositories.impl;
import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.repositories.UnitRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UnitRepositoryCustomImpl implements UnitRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Unit> findByNameLike(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> query = builder.createQuery(Unit.class);
        Root<Unit> root = query.from(Unit.class);
        query.where(builder.like(root.get("name"), "%" + name + "%"));
        return entityManager.createQuery(query).getResultList();
    }
}
