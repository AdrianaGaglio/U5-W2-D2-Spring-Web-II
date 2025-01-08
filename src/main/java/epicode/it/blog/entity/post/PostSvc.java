package epicode.it.blog.entity.post;

import epicode.it.blog.entity.author.Author;
import epicode.it.blog.entity.author.AuthorRepo;
import epicode.it.blog.exceptions.NotFoundException;
import epicode.it.blog.exceptions.RequiredException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostSvc {
    private final PostRepo postRepo;
    private final AuthorRepo authorRepo;

    public Post save(Post post) {
        return postRepo.save(post);
    }

    public List<Post> saveAll(List<Post> posts) {
        return postRepo.saveAll(posts);
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public Post findById(Long id) throws NotFoundException {
        if (!postRepo.existsById(id)) {
            throw new NotFoundException("Post not found");
        }

        return postRepo.findById(id).get();
    }

    public void delete(Post post) throws NotFoundException {
        Post p = findById(post.getId());
        postRepo.delete(p);
    }

    public String delete(Long id) throws NotFoundException {
        Post p = findById(id);
        postRepo.delete(p);
        return "Post deleted successfully";
    }

    public int count() {
        return (int) postRepo.count();
    }

    @Transactional
    public Post update(Long id, PostUpdateRequest request) throws NotFoundException {
        Post p = findById(id);

        BeanUtils.copyProperties(request, p);

        Author a = authorRepo.findById(request.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));
        p.setAuthor(a);

        return postRepo.save(p);
    }

    public Post update(Long id, String title) throws NotFoundException {
        Post p = findById(id);

        p.setTitle(title);

        return save(p);
    }

    public Post update(Long id, Long authorId) throws NotFoundException {
        Post p = findById(id);

        Author a = authorRepo.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));

        p.setAuthor(a);

        return save(p);
    }

    public Post create(PostCreateRequest request) throws NotFoundException {
        Post newPost = new Post();

        BeanUtils.copyProperties(request, newPost);

        Author a = authorRepo.findById(request.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));
        if (a == null) throw new NotFoundException("Author not found");
        newPost.setAuthor(a);

        return postRepo.save(newPost);
    }
}