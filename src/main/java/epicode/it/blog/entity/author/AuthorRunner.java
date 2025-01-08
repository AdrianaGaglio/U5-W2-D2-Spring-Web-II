package epicode.it.blog.entity.author;

import com.github.javafaker.Faker;
import epicode.it.blog.exceptions.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class AuthorRunner implements ApplicationRunner {
    private final AuthorSvc authorSvc;
    private final Faker faker;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(authorSvc.count() == 0) {

            for (int i = 0; i < 20; i++) {
                try {
                Author a = new Author();
                a.setName(faker.name().firstName());
                a.setSurname(faker.name().lastName());
                a.setEmail(a.getName().toLowerCase() + "." + a.getSurname().toLowerCase() + "@mail.com");
                a.setBirthDate(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                a.setAvatar();
                authorSvc.save(a);
                } catch (AlreadyExistException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    }
}
