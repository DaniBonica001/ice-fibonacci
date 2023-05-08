import com.zeroc.Ice.Current;
//import Talker.*;

public class ChatImp implements ChatController{
    @Override
    public void sendMessage(String msg, String destination, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public void broadcastMessage(String message, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'broadcastMessage'");
    }

    @Override
    public void register(String hostname, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public void subscribe(CallbackPrx callback, ChatClientPrx client, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }
    
}