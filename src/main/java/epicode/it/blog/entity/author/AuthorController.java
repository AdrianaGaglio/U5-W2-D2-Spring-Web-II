package epicode.it.blog.entity.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorSvc authorSvc;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorSvc.findById(id));
    }

//    @PostMapping
//    public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreateRequest request) {
//        return ResponseEntity.ok(authorSvc.create(request));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author request)  {
        return ResponseEntity.ok(authorSvc.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id)  {
        return new ResponseEntity<>(authorSvc.delete(id), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody String name)  {
        return ResponseEntity.ok(authorSvc.update(id, name));
    }
}
