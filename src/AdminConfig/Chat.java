package AdminConfig;

import java.rmi.Remote;
import java.rmi.RemoteException;
import interfaces.Messages;

public interface Chat extends Remote {

    public void set_msg(Messages msg) throws RemoteException;

    public Messages broadcast_msg() throws RemoteException;

}
