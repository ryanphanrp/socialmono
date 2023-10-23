package org.ryan.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("likes")
public class Likes {
    @Id
    String likeId;
    Long ownerId;
    String commentId;
    Long postId;

}
