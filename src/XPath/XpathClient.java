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

    public static void getElement(NodeList nodelist)
    {
        Node node;
        String str;
        if (nodelist.getLength() == 0) { return; }
        for (int i = 0; i < nodelist.getLength(); i++)
        {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                System.out.print(node.getNodeName() + ": ");
                getElement(node.getChildNodes());
            }
            else if (node.getNodeType() == Node.TEXT_NODE)
            {
                str = node.getNodeValue().trim();
                if (str.length() > 0) { System.out.println(str); }
            }
        }
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
        getElement(nodeList);
    }

    public static void main(String[] args) throws Exception
    {
        init();
        searchFlight("destination", "齐齐哈尔");
    }
}