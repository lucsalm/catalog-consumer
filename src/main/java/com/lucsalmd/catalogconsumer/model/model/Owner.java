package com.lucsalmd.catalogconsumer.model.model;

import java.util.List;

public record Owner(String owner, List<Catalog> catalog) {
}
