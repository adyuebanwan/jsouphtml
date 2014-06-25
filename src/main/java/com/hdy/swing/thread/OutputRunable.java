package com.hdy.swing.thread;

import com.hdy.alibaba.CreditDetailHtml;
import org.jsoup.helper.StringUtil;

import javax.swing.*;

/**
 * Created by hedongyu on 14-6-25.
 * 799374340@qq.com
 */
public class OutputRunable  implements Runnable{
    private JTextArea input;
    private JTextArea output;
    private JFrame frame;
    public OutputRunable(JTextArea input,JTextArea output,JFrame frame){
        this.input = input;
        this.output = output;
        this.frame = frame;
    }

    @Override
    public void run() {
        String url = input.getText().trim();
        if(StringUtil.isBlank(url)){
            JOptionPane.showMessageDialog(frame, "输入项不能为空");
            return;
        }
        try{
            output.append("正在采集，请稍等...");
            String result = new CreditDetailHtml().requestHtml(url);
            output.setText("");
            output.append(result);
            JOptionPane.showMessageDialog(frame, "抓取完成");
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, "抓取失败"+e.getMessage());
        }
    }
}
