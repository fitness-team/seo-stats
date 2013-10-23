package org.fakeurl.parcer;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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

    public GooglePositionParser() {
        super();
    }

    @Override
    protected Integer currentPosition(String domain, String word, String queryPattern) throws CaptchaException, IOException {
        Integer position = 0;
        boolean domainWasFound = false;
        for (int i = 0; i < 5 && !domainWasFound; i++) {
            HtmlPage page = getHtmlPage(String.format(queryPattern, URLEncoder.encode(word, ENCODING), i * 10));

            List allElements = page.getBody().getElementsByAttribute("li", "class", "g");

            for (Iterator iterator = allElements.iterator(); iterator.hasNext() && !domainWasFound; ) {
                HtmlElement element = (HtmlElement) iterator.next();
                if(!isRealSite(element)){
                    continue;
                }
                List cites = element.getHtmlElementsByTagName("cite");
                if(cites.size() != 1){
                    continue;
                }
                HtmlElement cite = (HtmlElement) cites.get(0);
                position++;

                String site = cite.getTextContent();

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


    private boolean isRealSite(HtmlElement element){
        try{
            String aClass = element.getAttribute("class");
            if(!aClass.equals("g")) return false;
//            int countChildElements = element.getChildElementCount();
//            if(countChildElements != 2) return false;
            List h3s = element.getElementsByAttribute("h3", "class", "r");
            if(h3s.isEmpty() || h3s.size() > 1) return false;
//            if(!h3.getAttributeValue("class").equals("r")) return false;
            int countChildElements = ((HtmlElement) h3s.get(0)).getChildElementCount();
//            List<Element> childElements1 = h3.getChildElements();
            if(countChildElements != 1) return false;
            List anchors = ((HtmlElement) h3s.get(0)).getHtmlElementsByTagName("a");
            if(anchors.isEmpty() || anchors.size() > 1) return false;
//            String href = ((HtmlElement) anchors.get(0)).getAttribute("href");
//            if(!href.startsWith("/url?")) return false;
            List tables = element.getHtmlElementsByTagName("table");
            if(!tables.isEmpty()) return false;
        } catch(NullPointerException e){
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, CaptchaException {

        GooglePositionParser parser = new GooglePositionParser();
        Integer position = parser.currentPosition("capo.com.ua", "пицца киев");
        System.out.println("====================================================================================================");
        Map<String, String> filter = new HashMap<String, String>();
        filter.put("cr", "countryUA");
        Integer position2 = parser.currentPosition("capo.com.ua", "пицца киев", filter);
        System.out.println(position);
        System.out.println(position2);
    }



}
