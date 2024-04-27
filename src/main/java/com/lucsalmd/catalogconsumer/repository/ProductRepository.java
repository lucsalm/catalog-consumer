package com.lucsalmd.catalogconsumer.repository;

import com.lucsalmd.catalogconsumer.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByOwnerIdAndCategoryId(String ownerId,String categoryId);
}
