import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface to implement methods which areused in remote call
 */
interface RemoteInterface extends Remote {

    /**
     * The method put key and value in map
     * 
     * @param key the value of key
     * @param value the value
     * @return the response based on operation success
     * @throws RemoteException Exception in case of method invocaition failure 
     */
    String put(String key, String value) throws RemoteException;
    
    /**
     * The method get value from map using key
     * 
     * @param key the value of key
     * @return the response based on operation success
     * @throws RemoteException Exception in case of method invocaition failure 
     */
    String get(String key) throws RemoteException;

    /**
     * The method delete value from map using key
     * 
     * @param key the value of key
     * @return the response based on operation success
     * @throws RemoteException Exception in case of method invocaition failure 
     */
    String delete(String key) throws RemoteException;
    
}
