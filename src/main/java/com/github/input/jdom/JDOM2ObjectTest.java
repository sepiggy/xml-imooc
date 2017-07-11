package com.github.input.jdom;


import com.github.input.entity.Book;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JDOM2ObjectTest {

    public static void main(String[] args) {

        ArrayList<Book> books = new ArrayList<>();
        SAXBuilder saxBuilder = new SAXBuilder();

        try {
            InputStream inputStream = new FileInputStream("src/main/resources/books.xml");

            Document document = saxBuilder.build(inputStream);

            Element rootElement = document.getRootElement();

            List<Element> bookList = rootElement.getChildren();

            for (Element book : bookList) {
                Book bookEntity = new Book();

                List<Attribute> attributeList = book.getAttributes();
                for (Attribute attribute : attributeList) {
                    String attributeName = attribute.getName();
                    String attributeValue = attribute.getValue();

                    if (attributeName.equals("id")) {
                        bookEntity.setId(attributeValue);
                    }
                }

                List<Element> bookChildren = book.getChildren();
                for (Element bookChild : bookChildren) {
                    String bookChildName = bookChild.getName();
                    String bookChildValue = bookChild.getValue();

                    if (bookChildName.equals("name")) {
                        bookEntity.setName(bookChildValue);
                    } else if (bookChildName.equals("author")) {
                        bookEntity.setAuthor(bookChildValue);
                    } else if (bookChildName.equals("year")) {
                        bookEntity.setYear(bookChildValue);
                    } else if (bookChildName.equals("price")) {
                        bookEntity.setPrice(bookChildValue);
                    } else if (bookChildName.equals("language")) {
                        bookEntity.setLanguage(bookChildValue);
                    }
                }
                books.add(bookEntity);
                // 将 bookEntity 设为空，交给 GC 处理
                bookEntity = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 打印遍历结果
        System.out.println(books);
    }
}
