package org.fakeurl.parcer;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.fakeurl.exeptions.CaptchaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/**
 */
public class GooglePositionParser implements IPositionParser {

    private static String simpleQuery = "https://google.com.ua/search?q=%s&start=%s";

    @Override
    public Integer currentPosition(String domain, String word, String query) throws CaptchaException, IOException {
        Integer position = 0;
        boolean domainWasFound = false;
        for (int i = 0; i < 5 && !domainWasFound; i++) {
            Source source = ParcerUtil.getSourceWithEncodeWIN1251(String.format(query, URLEncoder.encode(word, "utf-8"), i * 10));
            List<Element> allElements = source.getAllElements(HTMLElementName.LI);

            for (Iterator<Element> iterator = allElements.iterator(); iterator.hasNext() && !domainWasFound; ) {
                Element element = iterator.next();

                if(!isRealSite(element)){
                    continue;
                }
                List<Element> cites = element.getAllElements(HTMLElementName.CITE);
                if(cites.size() != 1){
                    continue;
                }
                Element cite = cites.get(0);
                position++;

                String site = cite.getTextExtractor().toString();

                System.out.println(site);
                if(site.startsWith(domain)) domainWasFound = true;
            }
        }
        return domainWasFound ? position : 51;
    }

    private boolean isRealSite(Element element){
        try{
            String aClass = element.getAttributeValue("class");
            if(!aClass.equals("g")) return false;
            List<Element> childElements = element.getChildElements();
            if(childElements.size() != 2) return false;
            Element h3 = childElements.get(0);
            if(!h3.getAttributeValue("class").equals("r")) return false;
            List<Element> childElements1 = h3.getChildElements();
            if(childElements1.size() != 1) return false;
            Element a = childElements1.get(0);
            if(!a.getAttributeValue("href").startsWith("/url?")) return false;
        } catch(NullPointerException e){
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, CaptchaException {
        GooglePositionParser parser = new GooglePositionParser();
        Integer position = parser.currentPosition("pizza-kiev.com.ua", "пицца киев", simpleQuery);
        System.out.println(position);
    }

}
