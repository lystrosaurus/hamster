package com.hamster.robot;

import com.google.common.collect.ImmutableBiMap;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by opabinia on 2017/5/26.
 */
public class TlRobot implements Robot {

  @Override
  public String send(Map<String, Object> body) throws UnsupportedEncodingException {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
    httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    ResponseEntity<String> resp = restTemplate
        .postForEntity(Constant.TU_LING_API, ImmutableBiMap.of(
            "key", Constant.TU_LING_API_KEY,
            "info", body.get("message")
        ), String.class, httpHeaders);

    return new String(resp.getBody().getBytes("ISO-8859-1"), "utf-8");

    //        String data;
    //        ObjectMapper objectMapper = new ObjectMapper();
    //        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //        JsonGenerator jsonGenerator = null;
    //        try {
    //            jsonGenerator = objectMapper.getFactory().createGenerator(outputStream);
    //            jsonGenerator.writeObject(ImmutableMap.of("key", API_KEY, "info", message));
    //            data = new String(outputStream.toByteArray(), "UTF-8");
    //            HttpClient httpClient = HttpClientBuilder.create().build();
    //            HttpPost post = new HttpPost(API);
    //            StringEntity postingString = new StringEntity(data, "UTF-8");
    //            post.setEntity(postingString);
    //            post.setHeader("Content-type", "application/json;charset=UTF-8");
    //            HttpResponse response = httpClient.execute(post);
    //
    //            HttpEntity httpEntity = response.getEntity();
    //            String result = IOUtils.toString(httpEntity.getContent(), "UTF-8");
    //
    //            log.info(result);
    //        } catch (IOException e) {
    //            log.error(e.getMessage());
    //        }
  }
}
