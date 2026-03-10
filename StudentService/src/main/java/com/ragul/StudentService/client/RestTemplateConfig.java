package com.ragul.StudentService.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){

        // if you are working with patch request in rest template add this extra class new HttpComponentsClientHttpRequestFactory(), if you didnt add this it will only work
        // with get,post,put,delete and not patch , so add this class explicitly in restTemplate to work with patch, add also dont forgot to add httpclient5 dependency in pom.xml,
        // only if you add this dependency this HttpComponentsClientHttpRequestFactory will work otherwise it will throw exceptions !!!
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

}
