package com.github.input.dom4j;

import com.github.input.entity.Book;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DOM4J2ObjectTest {

    public static void main(String[] args) {
        SAXReader saxReader = new SAXReader();
        List<Book> bookList = new ArrayList<Book>();

        try {
            Document document = saxReader.read(new File("src/main/resources/books.xml"));
            Element bookStoreElement = document.getRootElement();
            Iterator iterator = bookStoreElement.elementIterator();

            while (iterator.hasNext()) {
                Book bookEntity = new Book();
                Element bookElement = (Element) iterator.next();

                // 获取 book 的属性
                List<Attribute> attributes = bookElement.attributes();
                for (Attribute attribute : attributes) {
                    String attributeName = attribute.getName();
                    String attributeValue = attribute.getValue();
                    if (attributeName.equals("id")) {
                        bookEntity.setId(attributeValue);
                    }
                }

                // 获取 book 的子节点
                Iterator bookChildIterator = bookElement.elementIterator();

                while (bookChildIterator.hasNext()) {
                    Element bookChild = (Element) bookChildIterator.next();
                    String bookChildName = bookChild.getName();
                    String bookChildStringValue = bookChild.getStringValue();

                    if (bookChildName.equals("name")) {
                        bookEntity.setName(bookChildStringValue);
                    } else if (bookChildName.equals("author")) {
                        bookEntity.setAuthor(bookChildStringValue);
                    } else if (bookChildName.equals("year")) {
                        bookEntity.setYear(bookChildStringValue);
                    } else if (bookChildName.equals("price")) {
                        bookEntity.setPrice(bookChildStringValue);
                    } else if (bookChildName.equals("language")) {
                        bookEntity.setLanguage(bookChildStringValue);
                    }

                }

                bookList.add(bookEntity);
                bookEntity = null;
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        System.out.println(bookList);
    }
}
