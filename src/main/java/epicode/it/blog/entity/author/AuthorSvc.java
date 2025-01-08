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

    public Author save(Author author) throws AlreadyExistException {
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

    public Author findById(Long id) throws NotFoundException {
        if (!authorRepo.existsById(id)) {
            throw new NotFoundException("Author not found");
        }
        return authorRepo.findById(id).get();
    }

    public String delete(Author author) throws NotFoundException {
        Author a = findById(author.getId());

        authorRepo.delete(a);

        return "Author deleted successfully";
    }

    public String delete(Long id) throws NotFoundException {
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

    public Author update(Long id, AuthorUpdateRequest request) throws NotFoundException {
        Author a = findById(id);

        BeanUtils.copyProperties(request, a);

        return authorRepo.save(a);
    }

    public Author update(Long id, String name) throws NotFoundException {
        Author a = findById(id);

        a.setName(name);

        return authorRepo.save(a);
    }

    @Transactional
    public Author create(AuthorCreateRequest request) throws AlreadyExistException {
        Author newAuthor = new Author();

        if (findByEmail(request.getEmail()) != null) throw new AlreadyExistException("Email already exist");

        BeanUtils.copyProperties(request, newAuthor);

        return authorRepo.save(newAuthor);
    }
}