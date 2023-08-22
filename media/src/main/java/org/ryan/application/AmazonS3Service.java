package org.ryan.application;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.exception.SocialMonoException;
import org.ryan.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonS3Service {
    private final AmazonS3 amazonS3;

    public String generatePresignedUrl(String fileName, String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            throw new SocialMonoException("Bucket not found");
        }
        return amazonS3.generatePresignedUrl(
                bucketName,
                fileName,
                DateTimeUtil.fromNow(ChronoUnit.HALF_DAYS),
                HttpMethod.PUT
        ).toString();
    }

    public List<Bucket> listBuckets() {
        return amazonS3.listBuckets();
    }
}
