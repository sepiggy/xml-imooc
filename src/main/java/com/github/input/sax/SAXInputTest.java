package com.github.input.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

// SAX 方式解析 xml 文件
class SAXInputTest {

    public static void main(String[] args) {
        // SAX 方式解析 XML 步骤：
        // 1. 通过 SAXParserFactory 的静态 newInstance() 方法获取 SAXParserFactory 实例 factory
        final SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            // 2. 通过 SAXParserFactory 实例的 newSAXParser() 方法返回 SAXParser 实例 parser
            final SAXParser parser = factory.newSAXParser();

            // 4. 通过 SAXParser 对象的 parse 方法进行解析 XML
            SAXParserHandler handler = new SAXParserHandler();
            parser.parse("books.xml", handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
