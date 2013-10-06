package org.fakeurl.parcer;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import org.fakeurl.exeptions.CaptchaException;

import java.io.IOException;

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

        int position = 0;
        int i = 0;
        while(i < 5){
            Source source = ParcerUtil.getSourceWithEncodeUTF8(String.format(query, i, word));

            List<Element> allElements = source.getAllElements(HTMLElementName.LI);
            for (Iterator<Element> iterator = allElements.iterator(); iterator.hasNext(); ) {
                Element element = iterator.next();

            }
        }
//        System.out.println(source.getParseText().toString());
        return position;
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

            Integer water = parcer.currentPosition("", "water", simpleQueryUrl);
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
