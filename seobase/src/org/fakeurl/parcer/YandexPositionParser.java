package org.fakeurl.parcer;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import org.fakeurl.exeptions.CaptchaException;

import java.io.IOException;

import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/**
 */
public class YandexPositionParser extends AbstractPositionParser {

    private static final String simpleQueryUrl = "http://yandex.ua/yandsearch?p=%s&text=%s";


    public YandexPositionParser() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

    }

    @Override
    protected Integer currentPosition(String domain, String word, String query) throws CaptchaException, IOException {

        Integer position = 0;
        boolean domainWasFound = false;
        for (int i = 0; i < 5 && !domainWasFound; i++) {
            System.out.println(String.format(query, i, URLEncoder.encode(word, ENCODING)));
            HtmlPage source = getHtmlPage(String.format(query, i, URLEncoder.encode(word, ENCODING)));
            System.out.println(source.asText());
            List allElements = source.getBody().getElementsByAttribute("li", "class", "b-serp-item i-bem b-serp-item_js_inited");

            for (Iterator iterator = allElements.iterator(); iterator.hasNext() && !domainWasFound; ) {
                HtmlElement element = (HtmlElement) iterator.next();

                if(!isRealSite(element)){
                    continue;
                }
                List cites = element.getElementsByAttribute("a", "class", "b-serp-item__title-link");
                if(cites.size() < 1){
                    continue;
                }
                String site = "";
                for (Iterator elementIterator = cites.iterator(); elementIterator.hasNext(); ) {
                    HtmlElement next = (HtmlElement) elementIterator.next();

//                    if(next.getAttributeValue("class") != null && next.getAttributeValue("class").equals("b-serp-item__title-link")){
                        site = next.getAttribute("href");
//                        break;
//                    }
                }
                position++;

                System.out.println(site);
                if(site.startsWith("http://" + domain) || site.startsWith("https://" + domain)) domainWasFound = true;
            }
        }
        return domainWasFound ? position : 51;
    }

    private boolean isRealSite(HtmlElement element) {

        return true;
    }

    @Override
    protected String getQueryPattern() {
        return simpleQueryUrl;
    }

//    public Source getSource(String strUrl) throws IOException{
//
//        URL url = new URL(strUrl);
//        URLConnection con = url.openConnection();
//        Source source = new Source(con);
//        return source;
//    }

    public static void main(String[] args) {
        try {
            YandexPositionParser parser = new YandexPositionParser();

            Integer position = parser.currentPosition("www.amorepizza.kiev.ua", "пицца киев");
            System.out.println(position);
        } catch (CaptchaException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KeyManagementException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KeyStoreException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
