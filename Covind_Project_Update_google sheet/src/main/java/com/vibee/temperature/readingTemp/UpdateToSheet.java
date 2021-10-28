package com.vibee.temperature.readingTemp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UpdateToSheet{

    //upload url
    String postUrl ="https://content-sheets.googleapis.com/v4/spreadsheets/1PN6WpSsvuTh7m3uJtG2S27gjOQZ0SS4dHFp1eDqC674/values/A2:C2:append?valueInputOption=RAW&alt=json";

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 500000)
     void WorldCovidTotalPost() throws JsonProcessingException {

        //Getdata from covidworld data from (url:"https://api.covid19api.com/world/total")
        HttpHeaders httpheaders =new HttpHeaders();
        httpheaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpheaders);
        String url ="https://api.covid19api.com/world/total";
        String output = restTemplate.exchange(url,HttpMethod.GET,entity,String.class).getBody();

        //data get from api convert to string
         ObjectMapper mapper =new ObjectMapper();
         JsonNode jsonNode = mapper.readTree(output);
         String totalConfirmed = jsonNode.get("TotalConfirmed").toString();
         String totalDeaths =   jsonNode.get("TotalDeaths").toString();
         String totalRecovered  =   jsonNode.get("TotalRecovered").toString();

         //uploaded string
         String upload  ="{\n" + "  \"majorDimension\": \"COLUMNS\",\n"
                 + "  \"range\": \"A2:C2\",\n"
                 + "  \"values\": [\n"
                 + "    [\n"
                 + "      \""+totalConfirmed+"\"\n"
                 + "    ],\n"
                 + "    [\n"
                 + "      \""+totalDeaths+"\"\n"
                 + "    ],\n"
                 + "    [\n"
                 + "      \""+totalRecovered+"\"\n"
                 + "    ]\n"
                 + " ]\n"
                 + "\n }";


        //update the data to google sheet
         httpheaders.add("Authorization","Bearer " + "ya29.a0ARrdaM9ut_CzkoX_lIIoGt7CJNLMo8z8BOSUkx9eriK6RXozZhndgo3MXc1MCSYH-hGQ8bSKCQjQXs_kiioXMXVoK3xxqPbF9gbPsmbRYa3OOOxeP2FJEZrg0AbAwguGmQ-ZJOZ-K08jcSC43ktbRZQQPU01MRXHQQ");
         HttpEntity<String>entityToSheet = new HttpEntity<>(upload, httpheaders);
         System.out.println(restTemplate.exchange(postUrl,HttpMethod.POST,entityToSheet,String.class).getBody());



    }

}
