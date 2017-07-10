package com.github.input.dom;

import org.w3c.dom.*;
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

            // 通过 NodeList 对象的 getLength() 方法可以获取 NodeList 的长度
            System.out.println("一共有" + bookList.getLength() + "本书");

            // 2.2 遍历每个 book 节点的属性
            for (int i=0; i<bookList.getLength(); i++) {
                System.out.println("==================下面开始遍历第" + (i+1) + "本书的内容==================");
                // 通过 NodeList 对象的 item(index) 方法获取每个 book 节点， NodeList 的索引值从 0 开始
                final Node bookNode = bookList.item(i);

                // 通过 Node 对象的 getAttributes() 方法获取 book 节点的所有属性集合
                final NamedNodeMap bookAttributes = bookNode.getAttributes();

                // 通过 NamedNodeMap 对象的 getLength() 方法可以获取 NameNodeMap 的长度
                System.out.println("第" + (i+1) + "本书共有" + bookAttributes.getLength() + "个属性");
                // 遍历 book 节点的属性
                for (int j=0; j<bookAttributes.getLength(); j++) {
                    // 通过 NamedNodeMap 对象的 item(index) 方法获取 book 节点的某个属性
                    final Node bookAttribute = bookAttributes.item(j);
                    // 获取属性名
                    System.out.print("属性名：" + bookAttribute.getNodeName());
                    // 获取属性值
                    System.out.println(" --- 属性值：" + bookAttribute.getNodeValue());
                }

//                // 前提：已经知道 book 节点属性结构和个数的情况，可以使用如下简化写法
//                // 将 book 节点进行强制类型转换，转换成 Element 类型
//                Element book = (Element) bookList.item(i);
//                // 通过 Element 对象的 getAttribute(name) 方法获取属性值
//                String attrValue = book.getAttribute("id");
//                System.out.println("id属性的属性值为" + attrValue);


                // 2.3 解析每个 book 节点的子节点
                final NodeList childNodes = bookNode.getChildNodes();
                // 遍历 childNodes 获取每个节点的节点名和节点值
                System.out.println("第" + (i+1) + "本书共有" + childNodes.getLength() + "个子节点");

                for (int k=0; k<childNodes.getLength(); k++) {
                    // 区分出 text 类型的 node 以及 element 类型的 node
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        // 获取了 element 类型节点的节点名
                        System.out.print("第" + (k+1) + "个节点的节点名：" + childNodes.item(k).getNodeName());
                        // System.out.println(childNodes.item(k).getNodeValue());
                        // 获取了 element 类型节点的节点值 (注意以下两行代码的区别）
//                         System.out.println(" --- 节点值：" + childNodes.item(k).getFirstChild().getNodeValue());
                         System.out.println(" --- 节点值：" + childNodes.item(k).getTextContent());
                    }
                }

                System.out.println("==================结束遍历第" + (i+1) + "本书的内容==================");
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
