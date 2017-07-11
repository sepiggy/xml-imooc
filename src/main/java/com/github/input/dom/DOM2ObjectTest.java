package com.github.input.dom;

import com.github.input.entity.Book;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

class DOM2ObjectTest {

    public static void main(String[] args) {
        ArrayList<Book> bookArrayList = new ArrayList();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            final Document document = db.parse("books.xml");
            NodeList bookList = document.getElementsByTagName("book");

            for (int i=0; i<bookList.getLength(); i++) {
                Book bookEntity = new Book();
                Node bookNode = bookList.item(i);
                NamedNodeMap bookAttributes = bookNode.getAttributes();

                for (int j=0; j<bookAttributes.getLength(); j++) {
                    final Node bookAttribute = bookAttributes.item(j);
                    String bookAttributeNodeName = bookAttribute.getNodeName();
                    String bookAttributeNodeValue = bookAttribute.getNodeValue();
                    if (bookAttributeNodeName.equals("id")) {
                       bookEntity.setId(bookAttributeNodeValue);
                    }
                }

                NodeList bookChildNodeList = bookNode.getChildNodes();
                for (int k=0; k<bookChildNodeList.getLength(); k++) {
                    // 区分出 text 类型的 node 以及 element 类型的 node
                    if (bookChildNodeList.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        String bookChildNodeName = bookChildNodeList.item(k).getNodeName();
                        String bookChildNodeValue = bookChildNodeList.item(k).getFirstChild().getNodeValue();
                        if (bookChildNodeName.equals("name")) {
                            bookEntity.setName(bookChildNodeValue);
                        } else if (bookChildNodeName.equals("author")) {
                            bookEntity.setAuthor(bookChildNodeValue);
                        } else if (bookChildNodeName.equals("year")) {
                            bookEntity.setYear(bookChildNodeValue);
                        } else if (bookChildNodeName.equals("price")) {
                            bookEntity.setPrice(bookChildNodeValue);
                        } else if (bookChildNodeName.equals("language")) {
                            bookEntity.setLanguage(bookChildNodeValue);
                        }
                    }
                }
                bookArrayList.add(bookEntity);
                bookEntity = null;
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(bookArrayList);
    }
}
