package org.learning.application;

import org.learning.application.dto.CreatePresignedUrlDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amazon/internal")
public class AmazonController {
    private final AmazonS3Service amazonS3Service;

    public AmazonController(AmazonS3Service service) {
        this.amazonS3Service = service;
    }

    @PostMapping("/presigned-url/{bucket}")
    public String getPresignedUrlWithBucket(@PathVariable String bucket,
                                            @RequestBody CreatePresignedUrlDto dto) {
        return amazonS3Service.generatePresignedUrl(dto.fileName(), bucket);
    }
}
