package org.ryan.domain;

import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.PostCreateDto;
import org.ryan.application.dto.PostDetailDto;
import org.ryan.application.dto.PostDto;
import org.ryan.exception.customize.CustomNotFoundException;
import org.ryan.infrastruture.middleware.MessageHandler;
import org.ryan.infrastruture.middleware.MessageSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;
    private final MessageHandler messageHandler;
    private final MessageSender messageSender;

    public PostDto getPost(Long postId) {
        Post post = postDao.findPostByPostId(postId)
                .orElseThrow(CustomNotFoundException::new);
        return PostDto.of(post);
    }

    public Long createPost(PostCreateDto postCreateDto) {
        Post post = postCreateDto.toEntity();
        postDao.save(post);
        return post.getPostId();
    }

    public List<PostDto> getPosts() {
        return postDao.findAll().stream()
                .map(PostDto::of)
                .toList();
    }

    public PostDetailDto getDetailPost(Long postId) {
        Post post = postDao.findPostByPostId(postId)
                .orElseThrow(CustomNotFoundException::new);
        messageSender.sendMessage(post.getUserId());
        var userDto = messageHandler.receiveMessage();
        return PostDetailDto.of(PostDto.of(post), userDto);
    }
}
