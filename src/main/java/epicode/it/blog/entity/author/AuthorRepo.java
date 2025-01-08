package epicode.it.blog.entity.author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    public Author findFirstByEmailIgnoreCase(String email);
}
