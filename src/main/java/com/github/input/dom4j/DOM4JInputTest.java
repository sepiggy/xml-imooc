package com.github.input.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DOM4JInputTest {

    public static void main(String[] args) {
        // 使用 dom4j 解析 books.xml
        // 1. 准备工作
        // 1.1 创建 SAXReader 的对象 reader
        SAXReader saxReader = new SAXReader();
        try {
            // 1.2 通过 SAXReader 对象的 read 方法加载 books.xml 文件
            Document document = saxReader.read(new File("src/main/resources/books.xml"));

            // 1.3 通过 Document 对象的 getRootElement() 方法获取根节点
            Element bookStoreElement = document.getRootElement();

            // 1.4 通过 Element 对象的 elementIterator() 方法获取迭代器
            Iterator iterator = bookStoreElement.elementIterator();

            // 2. 解析工作
            // 2.1 遍历迭代器，获取根节点中的信息（书籍信息）
            while (iterator.hasNext()) {
                System.out.println("===== 开始遍历某一本书 =====");
                Element book = (Element) iterator.next();

                // 2.2 获取 book 的属性名以及属性值
                List<Attribute> attributes = book.attributes();

                for (Attribute attribute : attributes) {
                    System.out.println("属性名：" + attribute.getName() + " --- 属性值：" + attribute.getValue());
                }

                // 2.3 获取 book 的子节点名以及子节点值
                Iterator childrenIterator = book.elementIterator();
                while (childrenIterator.hasNext()) {
                    Element bookChild = (Element) childrenIterator.next();
                    String bookChildName = bookChild.getName();
                    String bookChildStringValue = bookChild.getStringValue();
                    System.out.println("节点名：" + bookChildName + " --- 节点值：" + bookChildStringValue);
                }

                System.out.println("===== 结束遍历某一本书 =====");
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
