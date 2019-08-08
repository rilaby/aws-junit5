package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;

class NotEligibleFieldInjectionTest {
    @AWSClient(clientConfiguration = ClientConfiguration.class)
    private String client;

    @Test
    void test() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new DynamoDB().postProcessTestInstance(this, null)
        );

        Assertions.assertEquals(
            "class java.lang.String is not supported by DynamoDB extension.",
            exception.getMessage()
        );
    }

    public static final class ClientConfiguration implements by.dev.madhead.aws_junit5.common.AWSClientConfiguration {
        @Override
        public String url() {
            return "";
        }

        @Override
        public String region() {
            return Region.US_EAST_1.id();
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
