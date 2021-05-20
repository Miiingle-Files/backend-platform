package net.miiingle.files.platform;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class BookService {

    public BookService() {
        log.info("Initialized BookService");
    }

    public void doSomething() {
        log.info("Bookservice did something!");
    }
}
