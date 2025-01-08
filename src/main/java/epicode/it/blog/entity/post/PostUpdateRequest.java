package epicode.it.blog.entity.post;

import lombok.Data;

@Data
public class PostUpdateRequest {

    private String category;

    private String title;

    private String cover;

    private String content;

    private double readingTime;

    private Long authorId;
}
