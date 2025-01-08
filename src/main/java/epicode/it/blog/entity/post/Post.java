package epicode.it.blog.entity.post;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import epicode.it.blog.entity.author.Author;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String category;

    private String title;

    private String cover = "picsum.photos/200/300?random";

    @Column(length = 1000)
    private String content;

    @Column(name="reading_time")
    private double readingTime;

    @ManyToOne
    @JsonManagedReference
    private Author author;
}