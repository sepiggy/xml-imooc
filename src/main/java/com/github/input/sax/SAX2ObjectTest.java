package com.github.input.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

// SAX 方式解析 xml 文件并存入对象中
class SAX2ObjectTest {

    public static void main(String[] args) {
        final SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            final SAXParser parser = factory.newSAXParser();
            SAX2ObjectHandler handler = new SAX2ObjectHandler();
            parser.parse("books.xml", handler);
            System.out.println(handler.getBookList());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
