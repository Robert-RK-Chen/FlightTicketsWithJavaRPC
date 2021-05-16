package SocketRPC;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert Chen
 */
public interface FlightInfoReader
{
    /**
     * 获航班信息
     *
     * @return List : 获取返回的航班信息
     */
    List<Element> getFlightInfo();
}
