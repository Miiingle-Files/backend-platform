package net.miiingle.files.platform;

import io.micronaut.context.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.rdsdata.RdsDataClient;
import software.amazon.awssdk.services.rdsdata.model.ExecuteStatementRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class BookService {

    @Value("${rds.cluster.arn}") String rdsClusterArn;
    @Value("${rds.cluster.database}") String rdsClusterDatabase;
    @Value("${rds.cluster.secrets}") String rdsClusterSecretsArn;
    @Inject S3Client s3;
    @Inject RdsDataClient rdsData;

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

        ExecuteStatementRequest request = ExecuteStatementRequest.builder()
                .resourceArn(rdsClusterArn)
                .secretArn(rdsClusterSecretsArn)
                .database(rdsClusterDatabase)
                .sql("select * from information_schema.tables")
                .build();

        var result = rdsData.executeStatement(request);
        log.info("Data API Result: {}", result);
    }
}
