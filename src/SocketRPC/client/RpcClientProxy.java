package SocketRPC.client;

import SocketRPC.FlightInfoReader;
import org.dom4j.Element;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

/**
 * @author Wang Huadong
 */
public class RpcClientProxy<T> implements InvocationHandler
{

    private final Class<T> serviceInterface;
    private final InetSocketAddress addr;

    public RpcClientProxy(Class<T> serviceInterface, String ip, String port)
    {
        this.addr = new InetSocketAddress(ip, Integer.parseInt(port));
        this.serviceInterface = serviceInterface;
    }

    public T getClientInstance()
    {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Socket socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;

        try
        {
            // 1.����Socket�ͻ��ˣ�����ָ����ַ����Զ�̷����ṩ��
            socket = new Socket();
            socket.connect(addr);

            // 2.��Զ�̷����������Ľӿ��ࡢ�������������б�ȱ�����͸������ṩ��
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF(serviceInterface.getName());
            output.writeUTF(method.getName());
            output.writeObject(method.getParameterTypes());
            output.writeObject(args);

            // 3.ͬ�������ȴ�����������Ӧ�𣬻�ȡӦ��󷵻�
            input = new ObjectInputStream(socket.getInputStream());
            return input.readObject();
        }
        finally
        {
            if (socket != null)
            {
                socket.close();
            }
            if (output != null)
            {
                output.close();
            }
            if (input != null)
            {
                input.close();
            }
        }
    }

    public static void main(String[] args)
    {
        RpcClientProxy<FlightInfoReader> client = new RpcClientProxy<>(FlightInfoReader.class, "localhost", "9527");
        FlightInfoReader reader = client.getClientInstance();
        List<Element> flights = reader.getFlightInfo();
        for (Element foo : flights)
        {
            System.out.println("����ţ�" + foo.attributeValue("ID"));
            System.out.println("�����أ�" + foo.attributeValue("departure"));
            System.out.println("Ŀ�ĵأ�" + foo.attributeValue("destination"));
            System.out.println("�ͺţ�" + foo.elementText("type"));
            System.out.println("Ԥ�Ƴ���ʱ�䣺" + foo.elementText("takeOffExpected"));
            System.out.println("ʵ�ʳ���ʱ�䣺" + foo.elementText("takeOffActual"));
            System.out.println("Ԥ�Ƶ���ʱ�䣺" + foo.elementText("arriveAtExpected"));
            System.out.println("ʵ�ʵ���ʱ�䣺" + foo.elementText("arriveAtActual"));
            System.out.println("����״̬��" + foo.elementText("state"));
            System.out.println("---------------------------------------------------------------------");
        }
    }
}