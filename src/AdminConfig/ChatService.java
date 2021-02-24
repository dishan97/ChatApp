package AdminConfig;

import interfaces.Messages;
import java.rmi.server.UnicastRemoteObject;
import javassist.tools.rmi.RemoteException;

public class ChatService extends UnicastRemoteObject implements Chat {

    Messages msg = null;

    public ChatService() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public void set_msg(Messages msg) throws RemoteException {
        System.out.println(msg.getMsgid());
        System.out.println(msg.getUserid());
        System.out.println(msg.getUsername());
        System.out.println(msg.getMsg_content());

        this.msg = msg;
    }

    @Override
    public Messages broadcast_msg() throws RemoteException {
        return msg;
    }

}
