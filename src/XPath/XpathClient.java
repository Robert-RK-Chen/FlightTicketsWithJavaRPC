package XPath;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.FileInputStream;

/**
 * @author Robert Chen
 */
public class XpathClient
{
    private static Document doc;
    private static XPath xpath;

    /**
     * ��ʼ��Document��XPath����
     */
    public static void init() throws Exception
    {
        // ����Document����
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new FileInputStream("FlightInfo.xml"));

        // ����XPath����
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    /**
     * ����ָ����Ϣ��ѯ����
     *
     * @param activity : ����Ϊ��ѯ���࣬�磺�����ء�Ŀ�ĵز�ѯ�����
     * @param keyword  : ��ѯ�ؼ���
     */
    public static void searchFlight(String activity, String keyword) throws XPathExpressionException
    {
        String xPathExpression = "//flight[@" + activity + " = '" + keyword + "']";
        NodeList nodeList = (NodeList) xpath.evaluate(xPathExpression, doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            System.out.println("����ţ�" + node.getAttributes().getNamedItem("ID").getTextContent());
            System.out.println("�����أ�" + node.getAttributes().getNamedItem("departure").getTextContent());
            System.out.println("Ŀ�ĵأ�" + node.getAttributes().getNamedItem("destination").getTextContent());
        }
    }

    public static void main(String[] args) throws Exception
    {
        init();
        searchFlight("destination", "�������");
    }
}