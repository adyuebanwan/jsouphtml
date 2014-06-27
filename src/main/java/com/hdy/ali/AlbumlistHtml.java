package com.hdy.ali;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hdy.common.HtmlPager;
import com.hdy.common.WebClientFactory;
import com.hdy.dto.ResultMsg;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by hedongyu on 14-6-26.
 * 799374340@qq.com
 */
public class AlbumlistHtml {
    public ResultMsg requestHtml(String url,JTextArea output) throws Exception {
        output.setText("");
        // 模拟浏览器打开一个目标网址
        HtmlPage page = HtmlPager.getPage(url);
        WebClientFactory.getWebClient().waitForBackgroundJavaScript(1000*3);
        //追加第一页的数据
        String firstPageHtml = page.asXml();
        Document doc = Jsoup.parse(firstPageHtml);
        Element element = doc.select(".album-page-1").first();
        String firstPagePhoto = element.html();
        output.append(firstPagePhoto);

        //追加分页数据
        List links = (List) page.getByXPath ("//li[@class='pagination']/a");
        if(null!=links){
            int size = links.size();
            if(size>3){
                int min = 2;
                int max = size-2;
                for(int i=min;i<=max;i++){
                    output.append(getNextPageContent(url, i));
                }
            }
        }
        WebClientFactory.closeAllWindows();
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setResult(null);
        return resultMsg;
    }

    private String getNextPageContent(String url,int idx) throws IOException {
        HtmlPage newPage = HtmlPager.getPage(url);
        WebClientFactory.getWebClient().waitForBackgroundJavaScript(1000*4);
        List newLinks = (List) newPage.getByXPath ("//li[@class='pagination']/a");
        HtmlAnchor newlink = (HtmlAnchor)newLinks.get(idx);
        newPage = newlink.click();
        WebClientFactory.closeAllWindows();
        String newHtml = newPage.asXml();
        Document doc = Jsoup.parse(newHtml);
        Element element = doc.select(".album-page-" + idx).first();
        return element.html();
    }
    public static void main(String[] args) throws Exception {
//        ResultMsg resultMsg = new AlbumlistHtml().requestHtml("http://zhenyusujiao.1688.com/page/albumlist.htm");
//        System.out.println(resultMsg.getResult());
    }

    private static void req() throws IOException {
        // 模拟一个浏览器
        WebClient  webClient = new WebClient(BrowserVersion.getDefault());
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(10*1000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getPage("http://zhenyusujiao.1688.com/page/albumlist.htm");
        webClient.closeAllWindows();
    }
}
