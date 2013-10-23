package org.fakeurl.captcha;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 */
public class CaptchaSolver {


    public static final String sendCaptchaUrl = "http://antigate.com/in.php";
    public static final String getCaptchaTextUrl = "http://antigate.com/res.php";

    private HttpClient httpClient;
    private AtigateKeyFactory keyFactory;
    private String key = "";
    
    public CaptchaSolver(){
        httpClient = HttpClients.createDefault();
        key = keyFactory.getKey();
    }
    
    public String sendCaptchaAndGetId(File captcha) throws IOException {
        //todo send captcha to antigate using httpclient
        HttpPost post = new HttpPost(sendCaptchaUrl);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addTextBody("method", "post");
        multipartEntityBuilder.addTextBody("key", key);
        multipartEntityBuilder.addBinaryBody("file", captcha);
        post.setEntity(multipartEntityBuilder.build());
        HttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        if(s.startsWith("OK")){
            String[] split = s.split("|");
            return split[1];
        }

        return null;
    }

    public String getCaptchaTextById(String captchaId) throws IOException {
        //todo get captcha text by id from antigate using httpclient
        StringBuilder request = new StringBuilder(getCaptchaTextUrl);
        request.append("?");
        request.append("key=").append(key);
        request.append("&action=get&");
        request.append("id=").append(captchaId);

        HttpGet get = new HttpGet(request.toString());
        HttpResponse execute = httpClient.execute(get);
        String s = EntityUtils.toString(execute.getEntity());
        return s.split("|")[1];
    }

}
