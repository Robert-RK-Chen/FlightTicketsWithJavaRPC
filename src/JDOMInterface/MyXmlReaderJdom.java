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
                System.out.println("����ţ�" + ((Element) allChild).getAttributeValue("ID"));
                System.out.println("�����أ�" + ((Element) allChild).getAttributeValue("departure"));
                System.out.println("Ŀ�ĵأ�" + ((Element) allChild).getAttributeValue("destination"));
                System.out.println("�ͺţ�" + ((Element) allChild).getChild("type").getText());
                System.out.println("Ԥ�Ƴ���ʱ�䣺" + ((Element) allChild).getChild("takeOffExpected").getText());
                System.out.println("ʵ�ʳ���ʱ�䣺" + ((Element) allChild).getChild("takeOffActual").getText());
                System.out.println("Ԥ�Ƶ���ʱ�䣺" + ((Element) allChild).getChild("arriveAtExpected").getText());
                System.out.println("ʵ�ʵ���ʱ�䣺" + ((Element) allChild).getChild("arriveAtActual").getText());
                System.out.println("����״̬��" + ((Element) allChild).getChild("state").getText());
                System.out.println("-------------------------------------------------------------------------");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("����ʱ�䣺" + (System.currentTimeMillis() - startTime) + "����");
    }
}