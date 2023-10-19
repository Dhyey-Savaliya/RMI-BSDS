import java.rmi.*;  
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;  
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server implements RemoteInterface {

    private Map<String, String>  map = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");

    public static void main(String args[]) {  
        try {
            Server server = new Server();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(server, 0);



            /**
             * Setting up the RMI registry.
             */
            Registry registry = LocateRegistry.createRegistry(9150);
            registry.rebind("KeyValueServer", stub);

            System.out.println("Server is running on ");
            } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public synchronized String put(String key, String value) throws RemoteException {

        printLog("PUT reuest recieved | Key : " + key + " | Value : " + value);
        // TODO Auto-generated method stub
        map.put(key, value);
        printLog("PUT reuest processed | Key : " + key + " | Value : " + value + " | New key value added ");
        return "PUT reuest processed | Key : " + key + " | Value : " + value + " | New key value added ";
    }

    @Override
    public String get(String key) throws RemoteException {
        // TODO Auto-generated method stub
        String msg;
        printLog("GET reuest recieved | Key : " + key);
        // TODO Auto-generated method stub
        if(map.containsKey(key)){
            String val = map.get(key);
            msg = "GET reuest processed | Key : " + key + " | Value : " + val + " | Value Found ";
        } else {
            msg = "GET reuest processed | Key : " + key + " | No Value Found";
        }
        
        return msg;
    }

    @Override
    public String delete(String key) throws RemoteException {
        String msg;
        printLog("GET reuest recieved | Key : " + key);
        // TODO Auto-generated method stub
        if(map.containsKey(key)){
            String val = map.get(key);
            map.remove(key);
            msg = "PUT reuest processed | Key : " + key + " | Value : " + val + " | Value Found Deletion Complete ";
        } else {
            msg = "PUT reuest processed | Key : " + key + " | No Value Found Deletion Failed";
        }
        
        return msg;
    }

    public static void printLog(String log) {
        System.out.println(dateFormat.format(new Date()) + " | " + log);
    }
    
    
}
