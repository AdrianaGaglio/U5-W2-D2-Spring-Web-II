package epicode.it.blog.entity.author;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorCreateRequest {
    private String name;

    private String surname;

    private String email;

    private LocalDate birthDate;

}
