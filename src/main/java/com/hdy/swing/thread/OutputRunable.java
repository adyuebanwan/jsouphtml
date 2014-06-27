package com.hdy.swing.thread;

import com.hdy.ali.AlbumlistHtml;
import com.hdy.ali.CreditDetailHtml;
import com.hdy.ali.OfferlistHtml;
import com.hdy.dto.PagingEnum;
import org.jsoup.helper.StringUtil;

import javax.swing.*;

/**
 * Created by hedongyu on 14-6-25.
 * 799374340@qq.com
 */
public class OutputRunable  implements Runnable{
    private JButton begin;
    private PagingEnum pagingEnum;
    private JTextArea input;
    private JTextArea output;
    private JFrame frame;
    public OutputRunable(JButton begin,JTextArea input,JTextArea output,JFrame frame,PagingEnum pagingEnum){
        this.begin = begin;
        this.input = input;
        this.output = output;
        this.frame = frame;
        this.pagingEnum = pagingEnum;
    }

    @Override
    public void run() {
        String url = input.getText().trim();
        if(StringUtil.isBlank(url)){
            JOptionPane.showMessageDialog(frame, "输入项不能为空类似 http://xxxx.1688.com/");
            begin.setEnabled(true);
            begin.setText("开始采集");
            output.setText("");
            return;
        }
        if((url+"$").contains("com$")){
            url = url+"/";
        }
        if(!url.contains("http://")){
            url = "http://"+url;
        }

        try{
            switch (this.pagingEnum){
                case CREDITDETAIL:
                    new CreditDetailHtml().requestHtml(url+"page/creditdetail.htm",output);break;
                case ALBUMLIST:
                    new AlbumlistHtml().requestHtml(url+"page/albumlist.htm",output);break;
                case OFFERLIST:
                    new OfferlistHtml().requestHtml(url+"page/offerlist.htm",output);break;
            }
            JOptionPane.showMessageDialog(frame, "抓取完成");
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, "抓取失败，再试一次吧"+e.getMessage());
        }
        begin.setText("开始采集");
        begin.setEnabled(true);
    }
}
