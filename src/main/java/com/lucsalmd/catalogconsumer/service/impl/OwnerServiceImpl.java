package com.lucsalmd.catalogconsumer.service.impl;

import com.google.gson.Gson;
import com.lucsalmd.catalogconsumer.model.model.Catalog;
import com.lucsalmd.catalogconsumer.model.model.Item;
import com.lucsalmd.catalogconsumer.model.model.Owner;
import com.lucsalmd.catalogconsumer.model.entity.Category;
import com.lucsalmd.catalogconsumer.model.entity.Product;
import com.lucsalmd.catalogconsumer.repository.CategoryRepository;
import com.lucsalmd.catalogconsumer.repository.ProductRepository;
import com.lucsalmd.catalogconsumer.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private S3ServiceImpl s3Service;

    public void saveOwner(String ownerId) {
        final Owner owner = mountOwner(ownerId);
        final String ownerJson = new Gson().toJson(owner);
        s3Service.putJsonToBucket(ownerId, ownerJson);
    }

    private Owner mountOwner(String ownerId) {
        final List<Category> categories = categoryRepository.findByOwnerId(ownerId);

        List<Catalog> catalog = categories.stream().map(category -> {
            final List<Item> items = productRepository.findByOwnerIdAndCategoryId(ownerId, category.getCategoryId()).stream()
                    .map(product -> new Item(product.getTitle(), product.getDescription(), product.getPrice()))
                    .toList();
            return new Catalog(category.getTitle(), category.getDescription(), items);
        }).toList();


        return new Owner(ownerId, catalog);
    }
}
