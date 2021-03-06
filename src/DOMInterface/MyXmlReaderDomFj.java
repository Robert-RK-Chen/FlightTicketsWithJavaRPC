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
    public static List<Element> readXmlFile(String fileName)
    {
        List<Element> flights = new ArrayList<>();
        try
        {
            File flightInfoFile = new File(fileName);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(flightInfoFile);
            Element root = doc.getRootElement();
            for (Iterator<?> i = root.elementIterator(); i.hasNext(); )
            {
                Element foo = (Element) i.next();
                flights.add(foo);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flights;
    }

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        List<Element> flights = readXmlFile("FlightInfo.xml");
        for (Element foo : flights)
        {
            System.out.println("航班号：" + foo.attributeValue("ID"));
            System.out.println("出发地：" + foo.attributeValue("departure"));
            System.out.println("目的地：" + foo.attributeValue("destination"));
            System.out.println("型号：" + foo.elementText("type"));
            System.out.println("预计出发时间：" + foo.elementText("takeOffExpected"));
            System.out.println("实际出发时间：" + foo.elementText("takeOffActual"));
            System.out.println("预计到达时间：" + foo.elementText("arriveAtExpected"));
            System.out.println("实际到达时间：" + foo.elementText("arriveAtActual"));
            System.out.println("航班状态：" + foo.elementText("state"));
            System.out.println("---------------------------------------------------------------------");
        }
        System.out.println("运行时间：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}