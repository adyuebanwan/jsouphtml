package com.hdy.common;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by hedongyu on 14-6-26.
 * 799374340@qq.com
 */
public class HtmlPager {

    public static HtmlPage getPage(String url) throws IOException {
        final WebClient webClient = WebClientFactory.getWebClient();
        URL URL = new URL(url);
        return webClient.getPage(URL);
    }
}
