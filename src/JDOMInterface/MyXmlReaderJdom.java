package JDOMInterface;

import java.io.*;
import java.util.*;

import org.jdom.*;
import org.jdom.input.*;

/**
 * @author Robert Chen
 * @author Wang Huadong
 */
public class MyXmlReaderJdom
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        try
        {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File("FlightInfo.xml"));
            Element foo = doc.getRootElement();
            List<?> allChildren;
            allChildren = foo.getChildren();
            for (Object allChild : allChildren)
            {
                System.out.println("航班号：" + ((Element) allChild).getAttributeValue("ID"));
                System.out.println("出发地：" + ((Element) allChild).getAttributeValue("departure"));
                System.out.println("目的地：" + ((Element) allChild).getAttributeValue("destination"));
                System.out.println("型号：" + ((Element) allChild).getChild("type").getText());
                System.out.println("预计出发时间：" + ((Element) allChild).getChild("takeOffExpected").getText());
                System.out.println("实际出发时间：" + ((Element) allChild).getChild("takeOffActual").getText());
                System.out.println("预计到达时间：" + ((Element) allChild).getChild("arriveAtExpected").getText());
                System.out.println("实际到达时间：" + ((Element) allChild).getChild("arriveAtActual").getText());
                System.out.println("航班状态：" + ((Element) allChild).getChild("state").getText());
                System.out.println("-------------------------------------------------------------------------");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("运行时间：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}