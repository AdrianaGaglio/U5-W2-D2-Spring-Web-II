package epicode.it.blog.entity.author;

import epicode.it.blog.exceptions.AlreadyExistException;
import epicode.it.blog.exceptions.NotFoundException;
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
    public ResponseEntity<?> getAuthor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(authorSvc.findById(id));
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorCreateRequest request) throws AlreadyExistException {
        try {
            return new ResponseEntity<>(authorSvc.create(request), HttpStatus.CREATED);
        } catch (AlreadyExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorUpdateRequest request) {
        try {
            return ResponseEntity.ok(authorSvc.update(id, request));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(authorSvc.delete(id));
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody String name) {
        try {
            return ResponseEntity.ok(authorSvc.update(id, name));
        } catch (NotFoundException  e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } 
    }
}
