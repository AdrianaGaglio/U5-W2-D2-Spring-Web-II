package epicode.it.blog.entity.post;

import lombok.Data;

@Data
public class PostCreateRequest {
    private String category;

    private String title;

    private String content;

    private double readingTime;

    private Long authorId;
}
