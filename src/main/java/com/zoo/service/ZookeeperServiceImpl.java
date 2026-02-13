package com.zoo.service;

import com.zoo.domain.Zookeeper;
import com.zoo.exception.EntityAlreadyExistsException;
import com.zoo.exception.EntityNotFoundException;
import com.zoo.repository.ZookeeperRepositoryImpl;

import java.util.List;

public class ZookeeperServiceImpl {

    private final ZookeeperRepositoryImpl repo;

    public ZookeeperServiceImpl(ZookeeperRepositoryImpl repo) {
        this.repo = repo;
    }

    public Zookeeper create(Zookeeper zk) {
        if (repo.existsByEmail(zk.getEmail())) {
            throw new EntityAlreadyExistsException("Zookeeper with email '" + zk.getEmail() + "' already exists");
        }
        return repo.save(zk);
    }

    public List<Zookeeper> getAll() {
        return repo.findAll();
    }

    public Zookeeper getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zookeeper with id " + id + " not found"));
    }

    public Zookeeper update(Zookeeper zk) {
        getById(zk.getId());
        return repo.update(zk);
    }

    public void delete(int id) {
        getById(id);
        repo.delete(id);
    }
}
