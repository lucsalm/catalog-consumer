package com.lucsalmd.catalogconsumer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class Product {

    @Id
    private String productId;
    @Indexed
    private String ownerId;
    private String categoryId;
    private String title;
    private Integer price;
    private String description;

}
