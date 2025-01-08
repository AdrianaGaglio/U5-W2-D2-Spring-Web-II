package epicode.it.blog.entity.post;

import epicode.it.blog.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
@RequiredArgsConstructor
public class PostController {

    private final PostSvc postSvc;

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(postSvc.findById(id));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest request) throws Exception {
        return ResponseEntity.ok(postSvc.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        try {
            return ResponseEntity.ok(postSvc.update(id, request));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody String title) {
        try {
            return ResponseEntity.ok(postSvc.update(id, title));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(postSvc.delete(id));
    }

}
