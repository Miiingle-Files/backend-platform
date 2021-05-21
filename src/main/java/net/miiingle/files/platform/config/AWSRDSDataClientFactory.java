package net.miiingle.files.platform.config;

import io.micronaut.aws.sdk.v2.service.AwsClientFactory;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.regions.providers.AwsRegionProviderChain;
import software.amazon.awssdk.services.rdsdata.RdsDataAsyncClient;
import software.amazon.awssdk.services.rdsdata.RdsDataAsyncClientBuilder;
import software.amazon.awssdk.services.rdsdata.RdsDataClient;
import software.amazon.awssdk.services.rdsdata.RdsDataClientBuilder;

import javax.inject.Singleton;

@Factory
public class AWSRDSDataClientFactory extends AwsClientFactory<RdsDataClientBuilder, RdsDataAsyncClientBuilder, RdsDataClient, RdsDataAsyncClient> {

    protected AWSRDSDataClientFactory(AwsCredentialsProviderChain credentialsProvider, AwsRegionProviderChain regionProvider) {
        super(credentialsProvider, regionProvider);
    }

    @Override
    protected RdsDataClientBuilder createSyncBuilder() {
        return RdsDataClient.builder();
    }

    @Override
    @Singleton
    public RdsDataClientBuilder syncBuilder(SdkHttpClient httpClient) {
        return super.syncBuilder(httpClient);
    }

    @Override
    @Bean(preDestroy = "close")
    public RdsDataClient syncClient(RdsDataClientBuilder builder) {
        return super.syncClient(builder);
    }

    @Override
    protected RdsDataAsyncClientBuilder createAsyncBuilder() {
        return RdsDataAsyncClient.builder();
    }

    @Override
    @Singleton
    @Requires(beans = SdkAsyncHttpClient.class)
    public RdsDataAsyncClientBuilder asyncBuilder(SdkAsyncHttpClient httpClient) {
        return super.asyncBuilder(httpClient);
    }

    @Override
    @Bean(preDestroy = "close")
    @Requires(beans = SdkAsyncHttpClient.class)
    public RdsDataAsyncClient asyncClient(RdsDataAsyncClientBuilder builder) {
        return super.asyncClient(builder);
    }
}
