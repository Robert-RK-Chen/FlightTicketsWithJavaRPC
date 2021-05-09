package SocketRPC.server;

import SocketRPC.FlightInfoReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author Wang Huadong
 */
public class RpcServer
{

    private static final HashMap<String, Class<?>> SERVICE_REGISTRY = new HashMap<>();
    private int port;

    public RpcServer(int port)
    {
        this.port = port;
    }

    public RpcServer register(Class serviceInterface, Class impl)
    {
        SERVICE_REGISTRY.put(serviceInterface.getName(), impl);
        return this;
    }

    public void run() throws IOException
    {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("start server");
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Socket socket = null;
        try
        {
            while (true)
            {
                socket = server.accept();
                input = new ObjectInputStream(socket.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                System.out.println(serviceName);
                System.out.println(methodName);
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class<?> serviceClass = SERVICE_REGISTRY.get(serviceName);
                if (serviceClass == null)
                {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (socket != null)
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        new RpcServer(6666).register(FlightInfoReader.class, FlightInfoReaderImpl.class).run();
    }

}