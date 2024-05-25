package com.lucsalmd.catalogconsumer.service.impl;

import com.lucsalmd.catalogconsumer.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private static final String JSON_FILE_EXTENSION = ".json";

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    public void putJsonToBucket(String name, String jsonValue) {
        final String fileName = name.concat(JSON_FILE_EXTENSION);

        final PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(APPLICATION_JSON_VALUE)
                .build();

        final PutObjectResponse response = s3Client.putObject(request, RequestBody.fromString(jsonValue));
        log.info("File: {} was put to S3 with status: {}", fileName, response.sdkHttpResponse().statusCode());
    }
}
