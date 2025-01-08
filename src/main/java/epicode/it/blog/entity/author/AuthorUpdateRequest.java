package epicode.it.blog.entity.author;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorUpdateRequest {

    private String name;

    private String surname;

    private String email;

    private LocalDate birthDate;

    private String avatar;
}
