package com.github.input.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// 3. 创建一个类继承 DefaultHandler，重写其中的一些方法进行业务处理并创建这个类的实例 handler
class SAXParserHandler extends DefaultHandler {

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
            System.out.println("==================下面开始遍历第" + (bookIndex) + "本书的内容==================");
//            // 前提：已知 book 元素下属性的名称，根据属性名称获取属性值
//            String id = attributes.getValue("id");
//            System.out.println("book的属性值是：" + id);

            // 前提：不知道 book 元素下属性的名称以及个数，如何获取属性名以及属性值
            final int num = attributes.getLength();
            for (int i=0; i<num; i++) {
                System.out.print("book元素的第" + (i+1) + "个属性，其属性名是：" + attributes.getQName(i));
                System.out.println(" --- 属性值是：" + attributes.getValue(i));
            }
        } else if (!qName.equals("bookstore")){
            System.out.print("节点名是：" + qName + " --- ");
        }

    }

    // 用来解析 XML 文件的结束标签
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        // 判断是否针对一本书的解析已经遍历结束
        if (qName.equals("book")) {
            System.out.println("==================结束遍历第" + (bookIndex) + "本书的内容==================");
        }
    }

    // 遍历节点名和节点值
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        final String value = new String(ch, start, length);
        // 屏蔽掉内容是空格和换行的节点
        if (!value.trim().equals("")) {
            System.out.println("节点值是：" + value);
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
