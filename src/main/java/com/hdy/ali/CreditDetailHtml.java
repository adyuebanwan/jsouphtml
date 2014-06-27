package com.hdy.ali;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;

/**
 * Created by hedongyu on 14-6-25.
 * 799374340@qq.com
 */
public class CreditDetailHtml {
    public void requestHtml(String url,JTextArea output) throws Exception {
        output.setText("");
        Document doc = Jsoup.connect(url).get();
        Element moduleMain = doc.select(".module-main").first();
        Elements detalContents = moduleMain.select(".module-common").select(".ovavinfobox").select(".detal-content");
        int size = detalContents.size();
        if(size>0){
            detalContents.get(size-1).remove();
        }
        outputAppend(output,moduleMain.html());
    }
    private void outputAppend(JTextArea output,String content){
        output.append(content);
        output.setCaretPosition(output.getText().length());
    }
}
