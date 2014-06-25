package com.hdy.ali;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by hedongyu on 14-6-25.
 * 799374340@qq.com
 */
public class CreditDetailHtml {
    public String requestHtml(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Element moduleMain = doc.select(".module-main").first();
        Elements detalContents = moduleMain.select(".module-common").select(".ovavinfobox").select(".detal-content");
        int size = detalContents.size();
        if(size>0){
            detalContents.get(size-1).remove();
        }
        return moduleMain.html();
    }
}
