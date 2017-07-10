package com.github.input.dom;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMInputTest {

    public static void main(String[] args) {
        // 1. 准备工作
        // 1.1 创建一个 DocumentBuilderFactory 的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // 1.2 创建一个 DocumentBuilder 的对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 1.3 通过 DocumentBuilder 对象的 parse(String fileName) 方法加载 xml 文件到当前项目下
            final Document document = db.parse("books.xml");

            // 2. 解析工作
            // 2.1 获取所有 book 节点的集合
            NodeList bookList = document.getElementsByTagName("book");
            // 2.2 通过 NodeList 对象的 getLength() 方法可以获取 bookList 的长度
            System.out.println("一共有" + bookList.getLength() + "本书");
            // 2.3 遍历每一个 book 节点
            for (int i=0; i<bookList.getLength(); i++) {
                // 2.4 通过 NodeList 对象的 item(i) 方法获取一个 book 节点， NodeList 的索引值从 0 开始
                final Node bookNode = bookList.item(i);
                // 2.5 通过 Node 对象的 getAttributes() 方法获取 book 节点的所有属性集合
                final NamedNodeMap bookAttributes = bookNode.getAttributes();

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
