package com.polypote.vrptwback.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polypote.vrptwback.model.Solution;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Component;

@Component
public class DataSender {

    public static final String FRONT_URL = "http://localhost:8090";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendSolutionToFront(Solution solution) {
        Solution clonedSolution = Solution.builder()
                .routes(solution.routes().stream().filter(camion -> camion.getDistance() > 0).toList())
                .fitness(solution.fitness())
                .build();
        HttpPost httpPost = new HttpPost(FRONT_URL + "/back/update_graph");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            String requestBody = objectMapper.writeValueAsString(clonedSolution);
            StringEntity stringEntity = new StringEntity(requestBody);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpclient.execute(httpPost, response -> {
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                final HttpEntity entity = response.getEntity();
                EntityUtils.consume(entity);
                return null;
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
