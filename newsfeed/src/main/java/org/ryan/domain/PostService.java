package org.ryan.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.PostCreateDto;
import org.ryan.application.dto.PostDetailDto;
import org.ryan.application.dto.PostDto;
import org.ryan.exception.customize.MonoNotFoundException;
import org.ryan.infrastruture.constant.NewsFeedResponseCode;
import org.ryan.infrastruture.middleware.MessageHandler;
import org.ryan.infrastruture.middleware.MessageSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostDao postDao;
  private final MessageHandler messageHandler;
  private final MessageSender messageSender;

  public PostDto getPost(Long postId) {
    Post post = postDao.findPostByPostId(postId)
        .orElseThrow(() -> new MonoNotFoundException(NewsFeedResponseCode.POST_NOT_FOUND));
    return PostDto.of(post);
  }

  public Long createPost(PostCreateDto dto, Long userId) {
    Post post = dto.createPost(userId);
    postDao.save(post);
    return post.getPostId();
  }

  public List<PostDto> getPosts() {
    return postDao.findAll()
        .stream()
        .map(PostDto::of)
        .toList();
  }

  public PostDetailDto getDetailPost(Long postId) {
    Post post = postDao.findPostByPostId(postId)
        .orElseThrow(() -> new MonoNotFoundException(NewsFeedResponseCode.POST_NOT_FOUND));
    var userDto = messageSender.getUserDto(post.getUserId());
    return PostDetailDto.of(PostDto.of(post), userDto);
  }
}