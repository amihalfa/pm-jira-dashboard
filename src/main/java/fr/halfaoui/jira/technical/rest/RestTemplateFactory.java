package fr.halfaoui.jira.technical.rest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amirouche
 */
@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {

    private RestTemplate restTemplate;

    @Value("${jira.username}")
    private String username;

    @Value("${jira.password}")
    private String password;

    public RestTemplate getObject() {
        return restTemplate;
    }
    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }
    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() {

        restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());
        interceptors.add(new BasicAuthenticationInterceptor(username, password));
        restTemplate.setInterceptors(interceptors);
    }
}

