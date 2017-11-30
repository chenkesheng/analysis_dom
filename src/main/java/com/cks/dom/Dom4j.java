package com.cks.dom;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Dom4j {
    public static void main(String[] args) {
        SAXReader saxReader = new SAXReader();

        InputStream inputStream = saxReader.getClass().getClassLoader().getResourceAsStream("cks1.xml");
        InputStream input = saxReader.getClass().getClassLoader().getResourceAsStream("cks1.xml");

        Document document = null;
        try {
            document = saxReader.read(inputStream);
            // 获取根元素
            Element root = document.getRootElement();
            System.out.println("Root: " + root.getName());

            // 获取所有子元素
            List<Element> childList = root.elements();
            System.out.println("total child count: " + childList.size());

            // 获取特定名称的子元素
            List<Element> childList2 = root.elements("hello");
            System.out.println("hello child: " + childList2.size());

            // 获取名字为指定名称的第一个子元素
            Element firstWorldElement = root.element("world");
            // 输出其属性
            System.out.println("first World Attr: "
                    + firstWorldElement.attribute(0).getName() + "="
                    + firstWorldElement.attributeValue("name"));

            System.out.println("迭代输出-----------------------");
            // 迭代输出
            for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                Element e = (Element) it.next();
                System.out.println(e.attributeValue("name"));

            }

            System.out.println("用DOMReader-----------------------");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 注意要用完整类名
            org.w3c.dom.Document document2 = db.parse(input);

            DOMReader domReader = new DOMReader();

            // 将JAXP的Document转换为dom4j的Document
            Document document3 = domReader.read(document2);

            Element rootElement = document3.getRootElement();

            System.out.println("Root: " + rootElement.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
