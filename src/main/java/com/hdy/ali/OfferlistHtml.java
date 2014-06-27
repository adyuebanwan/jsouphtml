package com.hdy.ali;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.util.List;

/**
 * Created by hedongyu on 14-6-26.
 * 799374340@qq.com
 */
public class OfferlistHtml {
    public String requestHtml(String url,JTextArea output) throws Exception {
        output.setText("");
//        StringBuffer sb = new StringBuffer();
        String pageOneUrl = buildPagingUrl(url,1);
        Document doc = Jsoup.connect(pageOneUrl).get();
        Element offerListDom = doc.select(".wp-category-nav-unit").first();
        output.append(offerListDom.html());
        Element productsPageOneDom = doc.select(".common-column-150").first();
        dealImgLazyLoad(productsPageOneDom);
        output.append(productsPageOneDom.html());
        //leftPage
        int pageCount = Integer.valueOf(doc.select(".page-count").first().text().trim());
        for(int i=2;i<pageCount;i++){
            Thread.sleep(1000*1);
            Document newDoc = Jsoup.connect(pageOneUrl).get();
            Element productsDom = newDoc.select(".common-column-150").first();
            dealImgLazyLoad(productsDom);
            output.append(productsDom.html());
        }
        return null;
    }

    private void dealImgLazyLoad(Element productsDom) {
        Elements imgs = productsDom.select(".image").select("img");
        for(int j=0;j<imgs.size();j++){
            Element img = imgs.get(j);
            Node imgNode = null;
            List<Node> nodes = img.parentNode().childNodes();
            for(int i=0;i<nodes.size();i++){
                Node node = nodes.get(i);
                if(!StringUtil.isBlank(node.attr("src"))){
                    imgNode = node;
                    break;
                }
            }
            if(imgNode!=null){
                String src = imgNode.attr("data-lazy-load-src");
                imgNode.attr("src",src);
            }
        }
    }

    private String buildPagingUrl(String url,int page){
        return url+"?tradenumFilter=false&priceFilter=false&mixFilter=false&privateFilter=false&groupFilter=false&sortType=tradenumdown&pageNum="+page+"#search-bar";
    }

    public static void main(String[] args) throws Exception {
//        OfferlistHtml html = new OfferlistHtml();
//        System.out.println(html.requestHtml("http://wuxiujian518.1688.com/page/offerlist.htm"));
    }
}
