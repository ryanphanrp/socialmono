package org.ryan.application;

import io.minio.errors.*;
import org.ryan.application.dto.UploadAvatarDto;
import org.ryan.application.dto.UploadMediaDto;
import org.ryan.application.exception.MediaServiceException;
import org.ryan.dto.ResponseDto;
import org.ryan.exception.SocialMonoException;
import org.ryan.infrastructure.minio.MinioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/minio/")
public class MinioController {
    private final MinioService minioService;

    public MinioController(MinioService service) {
        this.minioService = service;
    }

    @PostMapping("/upload-avatar")
    public ResponseDto<String> getPresignedUrlWithBucket(@RequestBody UploadAvatarDto dto) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        var presignedAvatarUrl = minioService.getPresignedAvatarUrl(dto.fileName());
        return ResponseDto.ok(presignedAvatarUrl);
    }

    @PostMapping("/upload-post")
    public ResponseDto<List<String>> getUploadUrls(@RequestBody UploadMediaDto dto) {
        return ResponseDto.ok(getImageUploadUrls(dto));
    }

    private List<String> getImageUploadUrls(UploadMediaDto dto) {
        return dto.images().stream().map(fileName -> {
            try {
                return minioService.getPresignedPostUrl(fileName);
            } catch (ServerException | InvalidKeyException | InsufficientDataException | ErrorResponseException |
                     IOException | NoSuchAlgorithmException | InvalidResponseException | XmlParserException |
                     InternalException e) {
                throw new MediaServiceException(e.getMessage());
            }
        }).toList();
    }
}
