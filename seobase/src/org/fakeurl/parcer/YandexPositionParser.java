package org.fakeurl.parcer;

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
            Source source = ParcerUtil.getSourceWithEncodeUTF8(String.format(query, i, URLEncoder.encode(word, ENCODING)));
            System.out.println(source.getTextExtractor().toString());
            List<Element> allElements = source.getAllElements(HTMLElementName.LI);

            for (Iterator<Element> iterator = allElements.iterator(); iterator.hasNext() && !domainWasFound; ) {
                Element element = iterator.next();

                if(!isRealSite(element)){
                    continue;
                }
                List<Element> cites = element.getAllElements(HTMLElementName.A);
                if(cites.size() < 1){
                    continue;
                }
                String site = "";
                for (Iterator<Element> elementIterator = cites.iterator(); elementIterator.hasNext(); ) {
                    Element next = elementIterator.next();

                    if(next.getAttributeValue("class") != null && next.getAttributeValue("class").equals("b-serp-item__title-link")){
                        site = next.getAttributeValue("href");
                        break;
                    }
                }
                position++;

                System.out.println(site);
                if(site.startsWith(domain)) domainWasFound = true;
            }
        }
        return domainWasFound ? position : 51;
    }

    private boolean isRealSite(Element element) {

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
            YandexPositionParser parcer = new YandexPositionParser();

            Integer water = parcer.currentPosition("pizza-kiev.com.ua", "пицца киев");
            System.out.println(water);
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
