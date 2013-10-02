package org.fakeurl.parcer;

import net.htmlparser.jericho.Source;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
public class ParcerUtil {

    public static Source getSource(String url, String charset) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
//        httpGet.addHeader();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        StringBuilder sb = new StringBuilder();
        try{
            StatusLine statusLine = response.getStatusLine();
//            System.out.println(statusLine.getStatusCode());
//            System.out.println(statusLine.getReasonPhrase());

            InputStream content = response.getEntity().getContent();

            BufferedReader br = new BufferedReader(new InputStreamReader(content, charset));
            for (String s = br.readLine(); s != null; s = br.readLine()){
                sb.append(s).append("\n");
            }
        } finally {
            response.close();
        }
        return new Source(sb.toString());
    }

    public static Source getSourceWithEncodeUTF8(String url) throws IOException {
        return getSource(url, "utf-8");
    }

    public static Source getSourceWithEncodeWIN1251(String url) throws IOException {
        return getSource(url, "cp1251");
    }

}
