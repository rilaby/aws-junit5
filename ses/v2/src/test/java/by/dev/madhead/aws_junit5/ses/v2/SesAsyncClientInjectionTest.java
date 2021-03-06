package by.dev.madhead.aws_junit5.ses.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.ses.SesAsyncClient;

@ExtendWith(SES.class)
class SesAsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private SesAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        // Assertions.assertEquals(
        //     Collections.singletonList("queue"),
        //     client
        //         .listIdentities()
        //         .get()
        //         .identities()
        //         .stream()
        //         .sorted()
        //         .collect(Collectors.toList())
        // );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getProperty("ses.url");
        }

        @Override
        public String region() {
            return System.getProperty("ses.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("ses.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("ses.secretKey");
        }
    }
}
