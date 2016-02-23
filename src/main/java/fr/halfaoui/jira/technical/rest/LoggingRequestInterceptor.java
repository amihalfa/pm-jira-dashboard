package fr.halfaoui.jira.technical.rest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author amirouche
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    final static Logger logger = Logger.getLogger(LoggingRequestInterceptor.class);

    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        logger.debug("===========================request begin================================================");

        logger.debug("URI : " + request.getURI());
        logger.debug("Method : " + request.getMethod());
        logger.debug("Request Body : " + new String(body, "UTF-8"));
        logger.debug("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        logger.debug("============================response begin==========================================");
        logger.debug("status code: " + response.getStatusCode());
        logger.debug("status text: " + response.getStatusText());
        logger.debug("=======================response end=================================================");
    }

}