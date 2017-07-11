package com.github.input.jdom;


import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.util.List;

public class JDOMInputTest {

    public static void main(String[] args) {

        // 使用 JDOM 方式对 books.xml 文件进行解析
        // 1. 准备工作
        // 1.1 创建一个 SAXBuilder 对象
        SAXBuilder saxBuilder = new SAXBuilder();

        try {
            // 1.2 创建一个输入流，将 xml 文件加载到输入流中
            InputStream inputStream = new FileInputStream("src/main/resources/books.xml");

            // 1.3 通过 SAXBuilder 对象的 build 方法，将输入流加载到 saxBuilder 中
            Document document = saxBuilder.build(inputStream);

            // 1.4 通过 Document 对象的 getRootElement() 方法获取 xml 文件的根节点
            Element rootElement = document.getRootElement();

            // 1.5 通过 Element 对象的 getChildren() 方法获取子节点的 List 集合
            List<Element> bookList = rootElement.getChildren();

            // 2. 解析工作
            for (Element book : bookList) {
                System.out.println("=====开始解析第" + (bookList.indexOf(book)+1) + "本书======");

                // 2.1 解析 book 节点的属性
                List<Attribute> attributeList = book.getAttributes();
                System.out.println("本节点共有" + attributeList.size() + "个属性");
                for (Attribute attribute : attributeList) {
                    // 获取属性名
                    String attributeName = attribute.getName();

                    // 获取属性值 (不同于 DOM 和 SAX，JDOM 解析 xml 其值都是有含义的，不包括空白符）
                    String attributeValue = attribute.getValue();

                    System.out.println("属性名：" + attributeName + " ----- 属性值：" + attributeValue);
                }

                // 2.2 对 book 节点的子节点的节点名以及节点值的遍历
                List<Element> bookChildren = book.getChildren();
                System.out.println("本节点共有" + bookChildren.size() + "个子节点");

                for (Element bookChild : bookChildren) {
                    // 获取节点名
                    String bookChildName = bookChild.getName();

                    // 获取节点值
                    String bookChildValue = bookChild.getValue();

                    System.out.println("节点名：" + bookChildName + " ----- 节点值：" + bookChildValue);
                }

                System.out.println("=====结束解析第" + (bookList.indexOf(book)+1) + "本书======");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
