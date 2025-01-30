package sharingcalender.front.config;


import feign.Client;
import feign.RequestInterceptor;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import sharingcalender.front.threadlocal.AuthorizationTokenHolder;

public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (!requestTemplate.headers().containsKey("Authorization")) {
                requestTemplate.header("Authorization",
                    "Bearer " + AuthorizationTokenHolder.getToken());
            }
        };
    }

    @Bean
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public Client client(CloseableHttpClient closeableHttpClient) {
        return new ApacheHttpClient(closeableHttpClient);
    }
}
