package SocketRPC.server;

import SocketRPC.FlightInfoReader;

/**
 * @author Robert Chen
 * @author Wang Huadong
 */
public class FlightInfoReaderImpl implements FlightInfoReader
{
    @Override
    public String sayHello(String string)
    {
        return "你好：".concat(string);
    }
}
