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
     * 初始化Document、XPath对象
     */
    public static void init() throws Exception
    {
        // 创建Document对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new FileInputStream("FlightInfo.xml"));

        // 创建XPath对象
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    /**
     * 根据指定信息查询航班
     *
     * @param activity : 按行为查询航班，如：出发地、目的地查询航班等
     * @param keyword  : 查询关键字
     */
    public static void searchFlight(String activity, String keyword) throws XPathExpressionException
    {
        String xPathExpression = "//flight[@" + activity + " = '" + keyword + "']";
        NodeList nodeList = (NodeList) xpath.evaluate(xPathExpression, doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            System.out.println("航班号：" + node.getAttributes().getNamedItem("ID").getTextContent());
            System.out.println("出发地：" + node.getAttributes().getNamedItem("departure").getTextContent());
            System.out.println("目的地：" + node.getAttributes().getNamedItem("destination").getTextContent());
        }
    }

    public static void main(String[] args) throws Exception
    {
        init();
        searchFlight("destination", "齐齐哈尔");
    }
}