package SocketRPC.server;

import SocketRPC.FlightInfoReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Robert Chen
 * @author Wang Huadong
 */
public class FlightInfoReaderImpl implements FlightInfoReader
{
    @Override
    public List<Element> getFlightInfo()
    {
        List<Element> flights = new ArrayList<>();
        try
        {
            File flightInfoFile = new File("FlightInfo.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(flightInfoFile);
            Element root = doc.getRootElement();
            for (Iterator<?> i = root.elementIterator(); i.hasNext(); )
            {
                Element foo = (Element) i.next();
                flights.add(foo);
            }
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        return flights;
    }
}
