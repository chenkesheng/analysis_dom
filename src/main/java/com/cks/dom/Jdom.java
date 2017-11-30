package com.cks.dom;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Jdom {
    public static void main(String[] args) {
        InputStream input = null;
        try {
            SAXBuilder builder = new SAXBuilder();
            input = builder.getClass().getClassLoader().getResourceAsStream("cks.xml");
            Document doc = builder.build(input);

            parseJDOM(doc);// 解析XML文档

        } catch (Exception e) {
            System.out.println("Can't read the file");
        }finally {
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 解析XML文档
    private static void parseJDOM(Document doc) {
        Element root = doc.getRootElement();
        List lineList = root.getChildren("line");// 也可使用root.getChildren()
        for (Iterator it = lineList.iterator();
             it.hasNext();) {
            Element lineElement = (Element) it.next();// 获取<line>元素

            String lid = lineElement.getAttributeValue("lid");// 获取<line>元素的lid属性值
            String num = lineElement.getAttributeValue("num");// 获取<line>元素的num属性值

            System.out.println("==lid:" + lid);
            System.out.println("==num:" + num);

            Element idElement = lineElement.getChild("id");// 获得<line>下<id>标签下的子元素
            String id = idElement.getText();// // 获得<line>下<id>标签下的子元素值
            System.out.println("==路线id:" + id);

            List stationList = lineElement.getChildren("station");// 获得<line>下<station>列表
            for (Iterator subIt = stationList.iterator(); subIt.hasNext();) {
                Element stationElement = (Element) subIt.next();// 获取<station>元素

                Element sidElement = stationElement.getChild("sid");// 获得<station>下<sid>标签下的子元素
                Element snameElement = stationElement.getChild("sname");// 获得<station>下<sname>标签下的子元素
                String sid = sidElement.getText();// 获得<station>下<sid>标签下的子元素值
                String sname = snameElement.getText();// 获得<station>下<sname>标签下的子元素值

                System.out.println("==路线sid:" + sid);
                System.out.println("==路线sname:" + sname);
            }
        }
    }
}
