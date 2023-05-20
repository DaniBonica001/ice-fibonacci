import java.io.*;
public class Server {
    public static void main(String[] args) {
        
        //java.util.List<String> extraArgs = new java.util.ArrayList<String>();

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "config.server")) {           

            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Service");     
                   
            ChatManagerImp chat = new ChatManagerImp();
            adapter.add(chat, com.zeroc.Ice.Util.stringToIdentity("ChatManager"));
            adapter.activate();
            communicator.waitForShutdown();
        }
    }

}