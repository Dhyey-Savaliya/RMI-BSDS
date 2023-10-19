import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;  
public class Client{  
public static void main(String args[]){  
    try{  
        Registry registry = LocateRegistry.getRegistry("localhost", 9150);
        RemoteInterface stub = (RemoteInterface) registry.lookup("KeyValueServer");
        System.out.println(stub.add(1, 2));
    }catch(Exception e){e.printStackTrace();}  
    }  
}  