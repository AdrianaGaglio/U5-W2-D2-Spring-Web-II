package epicode.it.blog.entity.post;

import epicode.it.blog.exceptions.AlreadyExistException;
import epicode.it.blog.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
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
    public ResponseEntity<Post> getById(@PathVariable Long id)  {
        return ResponseEntity.ok(postSvc.findById(id));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest request)  {
        return new ResponseEntity<>(postSvc.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post request) {
        return ResponseEntity.ok(postSvc.update(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody String title)  {
            return ResponseEntity.ok(postSvc.update(id, title));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id)  {
        return ResponseEntity.ok(postSvc.delete(id));
    }

}
