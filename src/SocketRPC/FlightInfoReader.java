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
     * �񺽰���Ϣ
     *
     * @return List : ��ȡ���صĺ�����Ϣ
     */
    List<Element> getFlightInfo();
}
