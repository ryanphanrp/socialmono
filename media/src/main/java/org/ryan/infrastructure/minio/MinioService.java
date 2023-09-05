package org.ryan.infrastructure.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.ryan.application.exception.BucketNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MinioService {
    private final MinioClient minioClient;

    @Value("${spring.minio.bucket.post}")
    private String postBucket;

    @Value("${spring.minio.bucket.avatar}")
    private String avatarBucket;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String generatePresignedUrl(String fileName, String bucket) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        log.info("File name: {}", fileName);
        var bucketArgs = BucketExistsArgs.builder().bucket(bucket).build();
        if (!minioClient.bucketExists(bucketArgs)) {
            throw new BucketNotFoundException(bucket);
        }
        var params = Map.of("response-content-type", "application/json");
        var args = GetPresignedObjectUrlArgs.builder()
                                            .method(Method.PUT)
                                            .bucket(bucket)
                                            .object(fileName)
                                            .expiry(2, TimeUnit.HOURS)
                                            .extraQueryParams(params)
                                            .build();
        return minioClient.getPresignedObjectUrl(args);
    }

    public String getPresignedAvatarUrl(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return generatePresignedUrl(fileName, avatarBucket);
    }

    public String getPresignedPostUrl(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return generatePresignedUrl(fileName, postBucket);
    }
}
