package com.hdy.ali;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by hedongyu on 14-6-26.
 * 799374340@qq.com
 */
public class OfferlistHtml {
    public String requestHtml(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Element offerListDom = doc.select(".wp-category-nav-unit").first();
        return offerListDom.html();
    }

    public static void main(String[] args) throws Exception {
        OfferlistHtml html = new OfferlistHtml();
        System.out.println(html.requestHtml("http://wuxiujian518.1688.com/page/offerlist.htm"));
    }
}
