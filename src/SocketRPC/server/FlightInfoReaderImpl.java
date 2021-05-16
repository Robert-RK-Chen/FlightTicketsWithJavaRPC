package SocketRPC.server;

import DOMInterface.MyXmlReaderDomFj;
import SocketRPC.FlightInfoReader;
import org.dom4j.Element;

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
        return MyXmlReaderDomFj.readXmlFile("FlightInfo.xml");
    }
}
