package by.dev.madhead.aws_junit5.sns.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.sns.SnsClient;

@ExtendWith(SNS.class)
class SnsClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private SnsClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        // Assertions.assertEquals(
        //     Collections.singletonList("topic"),
        //     client
        //         .listTopics()
        //         .topics()
        //         .stream()
        //         .map(Topic::topicArn)
        //         .sorted()
        //         .collect(Collectors.toList())
        // );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getenv("SNS_URL");
        }

        @Override
        public String region() {
            return System.getenv("SNS_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("SNS_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("SNS_SECRET_KEY");
        }
    }
}
