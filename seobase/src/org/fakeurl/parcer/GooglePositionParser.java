package org.fakeurl.parcer;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import org.fakeurl.exeptions.CaptchaException;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 */
public class GooglePositionParser extends AbstractPositionParser {

    private static String simpleQueryPattern = "https://google.com/search?q=%s&start=%s";
    public final String ENCODING = "utf-8";

    @Override
    protected Integer currentPosition(String domain, String word, String queryPattern) throws CaptchaException, IOException {
        Integer position = 0;
        boolean domainWasFound = false;
        for (int i = 0; i < 5 && !domainWasFound; i++) {
            Source source = ParcerUtil.getSourceWithEncodeWIN1251(String.format(queryPattern, URLEncoder.encode(word, ENCODING), i * 10));
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

    @Override
    protected String getQueryPattern() {
        return simpleQueryPattern;
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
            List<Element> tables = element.getAllElements(HTMLElementName.TABLE);
            if(!tables.isEmpty()) return false;
        } catch(NullPointerException e){
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, CaptchaException {
        GooglePositionParser parser = new GooglePositionParser();
        Integer position = parser.currentPosition("pizza-kiev.com.ua", "пицца киев");
        System.out.println("====================================================================================================");
        Map<String, String> filter = new HashMap<String, String>();
        filter.put("cr", "countryUA");
        Integer position2 = parser.currentPosition("pizza-kiev.com.ua", "пицца киев", filter);
        System.out.println(position);
        System.out.println(position2);
    }



}
