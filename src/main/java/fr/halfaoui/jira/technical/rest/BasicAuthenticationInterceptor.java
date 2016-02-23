package fr.halfaoui.jira.technical.rest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author amirouche
 */
public class BasicAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private String username;
    private String password;

    public BasicAuthenticationInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        request.getHeaders().add("Authorization", "Basic " + base64Creds);
        return execution.execute(request, body);
    }

}