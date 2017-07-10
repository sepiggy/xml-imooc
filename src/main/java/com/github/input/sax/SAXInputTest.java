package com.github.input.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXInputTest {

    public static void main(String[] args) {

        // 1. 通过 SAXParserFactory 的静态 newInstance() 方法获取 SAXParserFactory 实例 factory
        final SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            // 2. 通过 SAXParserFactory 实例的 newSAXParser() 方法返回 SAXParser 实例 parser
            final SAXParser parser = factory.newSAXParser();

            parser.parse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
