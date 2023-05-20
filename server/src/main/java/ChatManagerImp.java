import Demo.CallbackReceiverPrx;
import com.zeroc.Ice.Current;

import java.util.List;
import java.util.ArrayList;
import java.util.Stream;
import java.util.stream.Collectors;

public class ChatManagerImp implements Demo.ChatManager{

    private List<Client> clients;
    
    
    public ChatManagerImp(){

    }

    @Override
    public void subscribe(CallbackReceiverPrx callback, String hostname,Current current){
        System.out.println(hostname + " subscribe");
        if (!isSubscribe(hostname)){
            clients.add(new Client(callback,hostname));
        }
    }

    private boolean isSubscribe(String hostname){
        List<Client> found = clients.stream()
        .filter(host -> host.getHostname() == hostname)
        .collect(Collectors.toList());
        //If found != 0 it's because the hostname is already subscribe
        return found.size() != 0;
    }

    @Override
    public void sendMessage(String msg, String hostname, Current current){
        new Thread(() -> {
            CallbackReceiverPrx callbackClient = getCallbackPrxClient(hostname);
            if (callbackClient != null){
                System.out.println("New message " + msg);                
            }
            
        }).start();

    }

    private CallbackReceiverPrx getCallbackReceiverPrx(String hostname){
        List<Client> found = clients.stream()
        .filter(host -> host.getHostname() == hostname)
        .collect(Collectors.toList());
        if (found.size() != 0){
            System.out.println("CallbackPrx of the " + hostname + " was found");
            return found.get(0).getClientProxy();
        }
        System.out.println("CallbackPrx of the " + hostname + " wasn't found");
        return null;

    }
    
}
