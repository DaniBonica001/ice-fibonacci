import Demo.CallbackReceiverPrx;
import com.zeroc.Ice.Current;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.stream.Collectors;

public class ChatManagerImp implements Demo.ChatManager{

    private List<Client> clients;
    
    
    public ChatManagerImp(){
        clients = new ArrayList<>();

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
                inputCommands(msg, callbackClient);              
            }
            
        }).start();

    }

    private CallbackReceiverPrx getCallbackPrxClient(String hostname){
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

    public void getClients(CallbackReceiverPrx callbackReceiverPrx){
        String clientsList = "";
        for (Client client : clients){
            clientsList += client.getHostname() + "\n";
        }
        callbackReceiverPrx.printMsg(clientsList);
    }

    public boolean sendTo(String hostname, String msg){
        boolean sended = false;
        boolean subscribe = isSubscribe(hostname);
        if (subscribe){
            CallbackReceiverPrx callbackClient = getCallbackPrxClient(hostname);
            callbackClient.printMsg(msg);
            sended = true;
        }
        return sended;
    }

    public void broadcast(String msg){
        clients.stream()
        .forEach((client) -> {
            client.getClientProxy().printMsg(msg);
        });
    }

    public void inputCommands(String msg, CallbackReceiverPrx callbackPrx){

        //If input starts with BC
        if(msg.startsWith("BC")){
            broadcast(msg.split("BC ")[1]);

        //If input starts with To
        }else if (msg.startsWith("To") && msg.contains(":")){
            String[] split = msg.split("To ");
            String hostname = split[1].split(":")[0];
            String message = split[1].split(":")[1];
            
            if (sendTo(hostname, msg)){
                callbackPrx.printMsg("Message sended to " + hostname);
            }else{
                callbackPrx.printMsg("Message wasn't sended to " + hostname);
            }           

        //If input starts with List clients
        }else if (msg.startsWith("List clients")){
            getClients(callbackPrx);
        
            //If input starts with Fibonacci
        }else if (msg.startsWith("Fibonacci: ")){
            String[] split = msg.split("Fibonacci: ");
            int number = Integer.parseInt(split[1].trim());
            callbackPrx.printMsg("Fibonacci of " + number + " is " + sequenceFibonacci(number));

        }else if (msg.toLowerCase().equals("help")) {
            callbackPrx.printMsg("BC <msg> : envia un mensaje a todos los clientes conectados");
            callbackPrx.printMsg("List clients : lista los clientes conectados");
            callbackPrx.printMsg("To <hostname>:<msg> : envia un mensaje a un cliente especifico");
            callbackPrx.printMsg("Fibonacci: <numero> : retorna la serie de fibonacci hasta <numero>");
            callbackPrx.printMsg("Help : para ver los comandos que puede ingresar");

        }else {
            callbackPrx.printMsg("Command not recognized");
        }


    }

    public long sequenceFibonacci(int number) {

        String[] data = sequenceFibonacci(number, "").split("-");        

        System.out.println(
                "----------------------------------------\n"
                        + "Number: " + number + "\n"
                        + "Sequence: " + data[1] + "\n"
                        + "Result: " + data[0] + "\n"
                        + "----------------------------------------");

        return Long.parseLong(data[0]);
    }

    private String sequenceFibonacci(int n, String s) {
        long n1 = 0, n2 = 1, n3 = 0;

        if (n >= 0) {
            if (n == 0) {
                return 0 + "-" + 0;
            }
            // Base cases return itself 0 and 1
            else if (n == 1) {
                return 1 + "-" + 1;
            } else {

                for (int i = 2; i <= n; i++) {
                    n3 = n1 + n2; // sumamos los dos números anteriores 1 2 3
                    s += n3 + " ";//1 2 3 
                    n1 = n2; // actualizamos los dos números anteriores 1 1
                    n2 = n3;//1 2
                }                

                return n3 + "-" + s;
            }
        }else{
            return 0 + "-" + n;
        }

    }
    
}
