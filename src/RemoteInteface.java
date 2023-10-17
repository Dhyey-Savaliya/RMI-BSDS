import java.rmi.*;  

public interface RemoteInteface extends Remote{  
    public int add(int x,int y)throws RemoteException;  
}  
