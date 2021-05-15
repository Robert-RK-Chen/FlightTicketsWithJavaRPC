package DOMInterface;

import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

/**
 * @author Robert Chen
 * @author Wang Huadong
 */
public class MyXmlReaderDomFj
{
    public static void readXmlFile(String fileName)
    {
        try
        {
            File flightInfoFile = new File(fileName);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(flightInfoFile);
            Element root = doc.getRootElement();
            for (Iterator<?> i = root.elementIterator(); i.hasNext(); )
            {
                Element foo = (Element) i.next();
                System.out.println("����ţ�" + foo.attributeValue("ID"));
                System.out.println("�����أ�" + foo.attributeValue("departure"));
                System.out.println("Ŀ�ĵأ�" + foo.attributeValue("destination"));
                System.out.println("�ͺţ�" + foo.elementText("type"));
                System.out.println("Ԥ�Ƴ���ʱ�䣺" + foo.elementText("takeOffExpected"));
                System.out.println("ʵ�ʳ���ʱ�䣺" + foo.elementText("takeOffActual"));
                System.out.println("Ԥ�Ƶ���ʱ�䣺" + foo.elementText("arriveAtExpected"));
                System.out.println("ʵ�ʵ���ʱ�䣺" + foo.elementText("arriveAtActual"));
                System.out.println("����״̬��" + foo.elementText("state"));
                System.out.println("---------------------------------------------------------------------");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        readXmlFile("FlightInfo.xml");
        System.out.println("����ʱ�䣺" + (System.currentTimeMillis() - startTime) + "����");
    }
}