package com.portfolio.services.impl;

import com.portfolio.exceptions.TwitterException;
import com.portfolio.services.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class TwitterServiceImpl implements TwitterService {

    @Value("${twitter.config.auth-uri}")
    private  String authUri;

    @Value("${twitter.config.users-uri}")
    private String userUri;

    @Value("${twitter.config.time-line-uri}")
    private String timeLineUri;
    @Value("${twitter.config.api-key}")
    private String apiKey;

    @Value("${twitter.config.api-secret-key}")
    private String apiSecret;

    private String accessToken;

    private final RestTemplate restTemplate;

    public TwitterServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<String> getUserTimeLine(String user) {

         try {
             if (accessToken == null) {
                 accessToken = getToken();
             }
             String userId = getIdUser(user, accessToken);
             return getTimeLine(userId, accessToken);

         }catch (Exception ex){
             log.error(ex.getMessage(),ex);
         }
         return List.of();

    }


    private List<String> getTimeLine(String id,String token){
        ResponseEntity<Map> responseEntity=restTemplate.exchange(timeLineUri.replace(":id", id),
                HttpMethod.GET, new HttpEntity<>(httpHeaders("Bearer",token)), Map.class);
        if(responseEntity.hasBody()){
            Map<String,List<Map<String,String>>> map= responseEntity.getBody();
            if(map!=null) {
                return map.get("data")
                        .stream()
                        .map(m->m.get("text"))
                        .toList();
            }
        }
        return List.of();
    }
    private String getIdUser(String user,String token){
        ResponseEntity<Map> responseEntity=restTemplate.exchange(userUri.replace(":username", user),
                HttpMethod.GET, new HttpEntity<>(httpHeaders("Bearer",token)), Map.class);
        if(responseEntity.hasBody()){
            Map<String,Map<String,String>> map= responseEntity.getBody();
            if(map!=null) {
                return map.get("data").get("id");
            }
        }
        return null;
    }
    private String getToken(){
        ResponseEntity<Map> responseEntity=restTemplate.exchange(authUri,
                HttpMethod.POST, new HttpEntity<>(httpHeaders("Basic",getBasicAuthHeader())), Map.class);

        if(responseEntity.hasBody()){
            Map<String,String> map= responseEntity.getBody();
            if(map!=null) {
                return map.get("access_token");
            }
        }
        throw new TwitterException("Error trying to get access token");
    }

    private  HttpHeaders httpHeaders(String type,String credentials) {
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add("Authorization", type+" " + credentials);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
    private  String getBasicAuthHeader() {
        String credentials = apiKey+":"+apiSecret ;
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }
}
