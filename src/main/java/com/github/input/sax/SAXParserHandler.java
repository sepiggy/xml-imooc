package com.github.input.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// 3. 创建一个类继承 DefaultHandler，重写其中的一些方法进行业务处理并创建这个类的实例 handler
public class SAXParserHandler extends DefaultHandler {

    private int bookIndex;

    {
        bookIndex = 0;
    }

    // 用来解析 XML 文件的开始标签
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        // 5. 开始解析 book 元素的属性
        if (qName.equals("book")) {
            bookIndex++;
            System.out.println("==================下面开始遍历第" + (bookIndex+1) + "本书的内容==================");
//            // 前提：已知 book 元素下属性的名称，根据属性名称获取属性值
//            String id = attributes.getValue("id");
//            System.out.println("book的属性值是：" + id);

            // 前提：不知道 book 元素下属性的名称以及个数，如何获取属性名以及属性值
            final int num = attributes.getLength();
            for (int i=0; i<num; i++) {
                System.out.print("book元素的第" + (i+1) + "个属性，其属性名是：" + attributes.getQName(i));
                System.out.println(" --- 属性值是：" + attributes.getValue(i));
            }

        }
    }

    // 用来解析 XML 文件的结束标签
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        // 判断是否针对一本书的解析已经遍历结束
        if (qName.equals("book")) {
            System.out.println("==================下面开始遍历第" + (bookIndex+1) + "本书的内容==================");
        }
    }

    // 用来标识解析开始
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析books.xml开始");
    }

    // 用来标识解析结束
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析books.xml结束");
    }
}
