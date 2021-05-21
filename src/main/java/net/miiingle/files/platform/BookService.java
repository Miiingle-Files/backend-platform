package net.miiingle.files.platform;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class BookService {

    @Inject S3Client s3;

    public BookService() {
        log.info("Initialized BookService");

       //TODO: connect to aurora via data api
    }

    public void doSomething() {
        log.info("Bookservice did something!");

        var buckets = s3.listBuckets();

        buckets.buckets().stream()
                .map(Bucket::name)
                .forEach(log::info);
    }
}
