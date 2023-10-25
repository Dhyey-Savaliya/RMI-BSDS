import java.rmi.*;  
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;  
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Server is main class which initializes the server
 */
public class Server implements RemoteInterface {

    // map to maintain the reord of the data
    private Map<String, String>  map = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");

    /**
     * The main function which creates regustry. 
     * @param args 
     */
    public static void main(String args[]) {  
        try {
            // Initializing server
            Server server = new Server();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(server, 0);

            // Creating registry 
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            registry.rebind("KeyValueServer", stub);


            System.out.println("Server is running on " + Integer.parseInt(args[0]));
            } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public synchronized String put(String key, String value) throws RemoteException {

        // print msg receive
        printLog("PUT request recieved | Key : " + key + " | Value : " + value);
        // put the key into map
        map.put(key, value);
        printLog("PUT request processed | Key : " + key + " | Value : " + value + " | New key value added ");
        return "PUT request processed | Key : " + key + " | Value : " + value + " | New key value added ";
    }

    @Override
    public synchronized String get(String key) throws RemoteException {
        // print msg receive
        String msg;
        printLog("GET request recieved | Key : " + key);
        // get the value and print message
        if(map.containsKey(key)){
            String val = map.get(key);
            msg = "GET request processed | Key : " + key + " | Value : " + val + " | Value Found ";
        } else {
            msg = "GET request processed | Key : " + key + " | No Value Found";
        }
        printLog(msg);
        return msg;
    }

    @Override
    public synchronized String delete(String key) throws RemoteException {
        // print msg receive
        String msg;
        printLog("DELETE request recieved | Key : " + key);
        // delete the value and print message
        if(map.containsKey(key)){
            String val = map.get(key);
            map.remove(key);
            msg = "DELETE request processed | Key : " + key + " | Value : " + val + " | Value Found Deletion Complete ";
        } else {
            msg = "DELETE request processed | Key : " + key + " | No Value Found Deletion Failed";
        }
        printLog(msg);
        return msg;
    }

    /**
     * Prints the log with time
     * 
     * @param log the log to print
     */
    public static void printLog(String log) {
        System.out.println(dateFormat.format(new Date()) + " | " + log);
    }
    
    
}
