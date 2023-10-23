package org.ryan.application;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.ryan.application.dto.CommentCreateDto;
import org.ryan.domain.entity.Comment;
import org.ryan.domain.services.CommentService;
import org.ryan.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    Publisher<ResponseDto<Comment>> createComment(@RequestBody CommentCreateDto comment) {
        return commentService.createComment(comment)
                             .map(ResponseDto::ok);
    }

    @PutMapping
    Publisher<ResponseEntity<Comment>> updateComment(@RequestBody Comment comment) {
        return commentService.updateComment(comment)
                             .map(ResponseEntity::ok);
    }

    @GetMapping
    Publisher<ResponseDto<List<Comment>>> getAllComments() {
        return commentService.findAllComments()
                             .collectList()
                             .map(ResponseDto::ok);
    }

    @GetMapping("/{id}")
    Publisher<ResponseDto<Comment>> getComment(@PathVariable("id") String id) {
        return commentService.findCommentById(id)
                             .map(ResponseDto::ok);
    }

    @DeleteMapping("/{id}")
    Publisher<ResponseDto<Void>> deleteComment(@PathVariable("id") String id) {
        return commentService.deleteComment(id)
                             .thenReturn(ResponseDto.ok());
    }

    // gen request all comment with ownerid
    @GetMapping("/owner/{id}")
    Publisher<ResponseDto<List<Comment>>> getAllCommentsByOwnerId(@PathVariable("id") Long id) {
        return commentService.findAllCommentsByOwnerId(id)
                             .collectList()
                             .map(ResponseDto::ok);
    }
}
