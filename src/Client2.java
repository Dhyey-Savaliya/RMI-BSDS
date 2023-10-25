import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The client initialize the server
 * The client allows user to perform operations on server
 */
public class Client2{  
    

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");

    /**
     * Initializing the server
     * 
     * @param args IP and port 
     */
    public static void main(String args[]){  
        try{  
            // locating the registry and creating object
            Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
            RemoteInterface stub = (RemoteInterface) registry.lookup("KeyValueServer");

            // dummy put requests
            for(int i = 0; i< 10; i++){
                printLog("PUT request sent | Key : " + i + " | Value : " + String.valueOf(100+i));
                printLog(stub.put(String.valueOf(i), String.valueOf(i+100)));
            }

            // dummy get requests
            for(int i = -1; i< 5; i++){
                printLog("GET request sent | Key : " + i );
                printLog(stub.get(String.valueOf(i)));
            }
            
            // dummy deete request
            for(int i = -1; i< 5; i++){
                printLog("DELETE request sent | Key : " + i );
                printLog(stub.delete(String.valueOf(i)));
            }

            controller(stub);
        }catch(Exception e){e.printStackTrace();}  
    }

    /**
     * The controller controlls the flow of the appication
     * 
     * @param stub the object to call remote methods
     * @throws RemoteException throws in case of error while calling remote method
     */
    private static void controller(RemoteInterface stub) throws RemoteException{
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Please select one of the following options: ");
            System.out.println("1. PUT");
            System.out.println("2. GET");
            System.out.println("3. DELETE");
            System.out.println("4. EXIT");
            System.out.print("Enter your choice: ");

            // getting input from user
            String choice = sc.nextLine().strip();

            // checking if input is valid
            // if not valid, then asking user to enter again
            // else returning input
            switch (choice.strip()) {
                case "1":
                    System.out.print("Enter key: ");
                    String key = sc.nextLine();
                    System.out.print("Enter value: ");
                    String value = sc.nextLine();
                    printLog("PUT request sent | Key : " + key + " | Value : " + value);
                    printLog(stub.put(key, value));
                    break;
                case "2":
                    System.out.print("Enter key: ");
                    key = sc.nextLine();
                    printLog("GET request sent | Key : " + key );
                    printLog(stub.get(key));
                    break;
                case "3":
                    System.out.print("Enter key: ");
                    key = sc.nextLine();
                    printLog("DELETE request sent | Key : " + key );
                    printLog(stub.delete(key));
                    break;
                case "4":
                    printLog("Closing client...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    /**
     * prints log message with time stamp
     * @param log the log message
     */
    public static void printLog(String log) {
        System.out.println(dateFormat.format(new Date()) + " | " + log);
    }

}  