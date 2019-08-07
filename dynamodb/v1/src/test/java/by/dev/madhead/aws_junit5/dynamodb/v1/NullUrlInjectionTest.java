package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NullUrlInjectionTest {
    @DynamoDBLocal(serviceConfiguration = ServiceConfiguration.class)
    private AmazonDynamoDB client;

    @Test
    void test() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new DynamoDBLocalExtension().postProcessTestInstance(this, null)
        );
    }

    public static final class ServiceConfiguration implements by.dev.madhead.aws_junit5.common.ServiceConfiguration {
        @Override
        public String url() {
            return null;
        }

        @Override
        public String region() {
            return Regions.US_EAST_1.getName();
        }

        @Override
        public String accessKey() {
            return "accessKey";
        }

        @Override
        public String secretKey() {
            return "secretKey";
        }
    }
}
