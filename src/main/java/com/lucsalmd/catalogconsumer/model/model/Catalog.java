package com.lucsalmd.catalogconsumer.model.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Catalog(@JsonProperty("category_title") String categoryTitle,
                      @JsonProperty("category_title") String categoryDescription, List<Item> items) {
}
