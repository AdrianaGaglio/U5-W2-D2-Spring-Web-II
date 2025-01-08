package epicode.it.blog.entity.post;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class PostRunner implements ApplicationRunner {
    private final PostSvc postSvc;
    private final Faker faker;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (postSvc.count() == 0) {

            for (int i = 0; i < 50; i++) {
                PostCreateRequest newPost = new PostCreateRequest();
                newPost.setCategory(faker.book().genre());
                newPost.setTitle(faker.book().title());
                newPost.setContent(faker.lorem().sentence(100));
                newPost.setReadingTime(faker.random().nextInt(5, 20).doubleValue());
                newPost.setAuthorId(faker.random().nextInt(1, 20).longValue());
                try {
                    postSvc.create(newPost);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
