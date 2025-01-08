package epicode.it.blog.entity.author;

import com.fasterxml.jackson.annotation.JsonBackReference;
import epicode.it.blog.entity.post.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String surname;

    private String email;

    @Column(name="birth_date")
    private LocalDate birthDate;

    private String avatar;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();

    public void setAvatar() {
        this.avatar = "https://ui-avatars.com/api/name=" + getName() + "+" + getSurname();
    }
}