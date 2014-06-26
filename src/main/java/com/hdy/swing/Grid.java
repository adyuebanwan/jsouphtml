package com.hdy.swing;

import com.hdy.dto.PagingEnum;
import com.hdy.safe.SafeUtil;
import com.hdy.swing.thread.OutputRunable;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hedongyu on 14-6-25.
 * 799374340@qq.com
 */
public class Grid {
    private static final JFrame frame = new JFrame("Grid");

    private JButton begin;
    private JPanel mainPanel;
    private JButton copyOutput;
    private JRadioButton companyProfile;
    private JRadioButton companyPhoto;
    private JButton clearOutput;
    private JButton copyInput;
    private JButton clearInput;
    private JTextArea output;
    private JTextArea input;
    private JButton close;
    private JRadioButton products;

    private static int clickTimes;

    public Grid() {
        $$$setupUI$$$();
        //开始采集
        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                begin.setEnabled(false);
                if (!SafeUtil.canUse()) {
                    JOptionPane.showMessageDialog(frame, "1");
                    return;
                }
                clickTimes++;
                if (clickTimes > 10) {
                    clickTimes = 0;
                    output.setText("正在采集，请稍等...休息一下吧老婆，别太累了，喝口水暂停一下....");
                } else {
                    output.setText("正在采集，请稍等...");
                }
                boolean companyProfileSelected = companyProfile.isSelected();
                if (companyProfileSelected) {
                    OutputRunable outputRunable = new OutputRunable(begin, input, output, frame, PagingEnum.CREDITDETAIL);
                    new Thread(outputRunable).start();
                    return;
                }
                boolean ambulistSelected = companyPhoto.isSelected();
                if (ambulistSelected) {
                    OutputRunable outputRunable = new OutputRunable(begin, input, output, frame, PagingEnum.ALBUMLIST);
                    new Thread(outputRunable).start();
                    return;
                }
                boolean offerlistSelected = products.isSelected();
                if (offerlistSelected) {
                    OutputRunable outputRunable = new OutputRunable(begin, input, output, frame, PagingEnum.OFFERLIST);
                    new Thread(outputRunable).start();
                    return;
                }
//                boolean otherRadioSelected = companyPhoto.isSelected();
//                System.out.println(inputText + companyProfileSelected + otherRadioSelected);
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        copyOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                String outputText = output.getText();
                StringSelection stringSel = new StringSelection(outputText);
                clipboard.setContents(stringSel, null);
            }
        });
        clearOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
            }
        });
        copyInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                String inputText = input.getText();
                StringSelection stringSel = new StringSelection(inputText);
                clipboard.setContents(stringSel, null);
            }
        });
        clearInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText("");
            }
        });
    }

    private void createUIComponents() {
        mainPanel = new JPanel();
        input = new JTextArea();
        output = new JTextArea();
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        frame.setTitle("自动采集阿里数据");
        frame.setSize(1000, 600);
        frame.setLocation(150, 100);
        frame.setContentPane(new Grid().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel.setLayout(new GridLayoutManager(6, 15, new Insets(0, 0, 0, 0), -1, -1));
        begin = new JButton();
        begin.setText("开始采集");
        mainPanel.add(begin, new GridConstraints(5, 1, 1, 13, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        companyProfile = new JRadioButton();
        companyProfile.setEnabled(true);
        companyProfile.setSelected(true);
        companyProfile.setText("公司档案");
        mainPanel.add(companyProfile, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("公司网站首页地址");
        mainPanel.add(label1, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("输出所采集到内容");
        mainPanel.add(label2, new GridConstraints(3, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearOutput = new JButton();
        clearOutput.setText("清空");
        mainPanel.add(clearOutput, new GridConstraints(4, 14, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyOutput = new JButton();
        copyOutput.setText("复制");
        mainPanel.add(copyOutput, new GridConstraints(3, 14, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyInput = new JButton();
        copyInput.setText("复制");
        mainPanel.add(copyInput, new GridConstraints(1, 14, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearInput = new JButton();
        clearInput.setText("清空");
        mainPanel.add(clearInput, new GridConstraints(2, 14, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(3, 1, 2, 13, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(output);
        final JScrollPane scrollPane2 = new JScrollPane();
        mainPanel.add(scrollPane2, new GridConstraints(1, 1, 2, 13, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        input.setEnabled(true);
        scrollPane2.setViewportView(input);
        close = new JButton();
        close.setText("关闭");
        mainPanel.add(close, new GridConstraints(5, 14, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        products = new JRadioButton();
        products.setText("供应产品");
        mainPanel.add(products, new GridConstraints(0, 7, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        companyPhoto = new JRadioButton();
        companyPhoto.setEnabled(true);
        companyPhoto.setText("公司相册");
        mainPanel.add(companyPhoto, new GridConstraints(0, 2, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(companyProfile);
        buttonGroup.add(companyPhoto);
        buttonGroup.add(products);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
