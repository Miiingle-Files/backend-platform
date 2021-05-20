package net.miiingle.files.platform;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Controller
public class BookController {

    @Inject BookService bookService;

    @Post
    public BookSaved save(@Valid @Body Book book) {
        log.info("Creating Book: {}", book);

        BookSaved bookSaved = new BookSaved();
        bookSaved.setName(book.getName());
        bookSaved.setIsbn(UUID.randomUUID().toString());

        bookService.doSomething();

        log.info("Created Book: {}", bookSaved);
        return bookSaved;
    }
}
