package com.github.input.sax;

import com.github.input.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class SAX2ObjectHandler extends DefaultHandler {

    private String value;
    private Book book;
    private List<Book> bookList;

    {
        value = "";
        bookList = new ArrayList<>();
    }

    // 用来解析 XML 文件的开始标签
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("book")) {
            book = new Book();
            // 前提：不知道 book 元素下属性的名称以及个数，如何获取属性名以及属性值
            final int num = attributes.getLength();
            for (int i=0; i<num; i++) {
                if (attributes.getQName(i).equals("id")) {
                    book.setId(attributes.getValue(i));
                }
            }
        }

    }

    // 用来解析 XML 文件的结束标签
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        // 判断是否针对一本书的解析已经遍历结束
        if (qName.equals("name")) {
            book.setName(value);
        } else if (qName.equals("author")) {
            book.setAuthor(value);
        } else if (qName.equals("year")) {
            book.setYear(value);
        } else if (qName.equals("price")) {
            book.setPrice(value);
        } else if (qName.equals("language")) {
            book.setLanguage(value);
        } else if (qName.equals("book")) {
            // 每遍历完一本书，将其信息保存到一个 List 中
            bookList.add(book);
            // 在 book 的结束标签清空 book 对象，方便遍历下一个节点
            book = null;
        }
    }

    // 遍历节点名和节点值
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        final String value = new String(ch, start, length);
        // 屏蔽掉内容是空格和换行的节点
        if (!value.trim().equals("")) {
            this.value = value;
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
