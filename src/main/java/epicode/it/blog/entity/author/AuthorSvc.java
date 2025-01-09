package epicode.it.blog.entity.author;

import epicode.it.blog.exceptions.AlreadyExistException;
import epicode.it.blog.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorSvc {
    private final AuthorRepo authorRepo;

    public Author save(Author author)  {
        Author a = findByEmail(author.getEmail());
        if (a != null) throw new AlreadyExistException("Email already exist");
        return authorRepo.save(author);
    }

    public List<Author> saveAll(List<Author> posts) {
        return authorRepo.saveAll(posts);
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    public Author findById(Long id) {
        return authorRepo.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
    }

    public String delete(Author author) {
        Author a = findById(author.getId());

        authorRepo.delete(a);

        return "Author deleted successfully";
    }

    public String delete(Long id)  {
        Author a = findById(id);

        authorRepo.delete(a);

        return "Author deleted successfully";
    }

    public Author findByEmail(String email) {
        return authorRepo.findFirstByEmailIgnoreCase(email);
    }

    public int count() {
        return (int) authorRepo.count();
    }

    public Author update(Long id, Author request)  {
        Author a = findById(id);

        BeanUtils.copyProperties(request, a);

        return authorRepo.save(a);
    }

    public Author update(Long id, String name)  {
        Author a = findById(id);

        a.setName(name);

        return authorRepo.save(a);
    }

    @Transactional
    public Author create(AuthorCreateRequest request) {
        Author newAuthor = new Author();

        if (findByEmail(request.getEmail()) != null) throw new AlreadyExistException("Email already exist");

        BeanUtils.copyProperties(request, newAuthor);

        return authorRepo.save(newAuthor);
    }
}
