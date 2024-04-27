package com.lucsalmd.catalogconsumer.repository;

import com.lucsalmd.catalogconsumer.model.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByOwnerId(String ownerId);
}
