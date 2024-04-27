package com.lucsalmd.catalogconsumer.service;

public interface S3Service {
    void putJsonToBucket(String fileName, String json);
}
