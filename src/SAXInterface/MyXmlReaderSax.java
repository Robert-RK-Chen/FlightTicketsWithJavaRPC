package SAXInterface;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.xml.parsers.*;

/**
 * @author Robert Chen
 */
public class MyXmlReaderSax extends DefaultHandler
{
    String temp;
    String element = "flight";
    Stack<Object> tags = new Stack<>();
    private final StringBuffer currentValue = new StringBuffer();

    public MyXmlReaderSax()
    {
        super();
    }

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        try
        {
            SAXParserFactory sf = SAXParserFactory.newInstance();
            SAXParser sp = sf.newSAXParser();
            MyXmlReaderSax reader = new MyXmlReaderSax();
            sp.parse(new InputSource("FlightInfo.xml"), reader);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("运行时间：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
    {
        currentValue.delete(0, currentValue.length());
        tags.push(qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    {
        temp = (String) tags.pop();
        if (element.equals(temp))
        {
            System.out.println("状态：" + currentValue);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
    {
        currentValue.append(ch, start, length);
    }
} 

