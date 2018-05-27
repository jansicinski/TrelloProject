package com.janek.TrelloProject.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyFirstSpringController {

    @GetMapping("trello/members/me")
    String me() throws Exception{

        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", "89b04a6238e8705a5fad7a5a3462a2f8");
        parameters.put("token", "dbb7b0bef8bf41638301238d4702d28a3bc23fe79605e2775cbd1af3806243a4");

        URL url = new URL("https://api.trello.com/1/members/me/?" + ParameterStringBuilder.getParamsString(parameters));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        System.out.println(content);

        HashMap<String,Object> result =
                new ObjectMapper().readValue(content.toString(), HashMap.class);

        con.disconnect();

        return content.toString();
    }

}
