import com.zeroc.Ice.Current;
import Demo.ChatManagerPrx;

public class CallbackReceiverImp implements Demo.CallbackReceiver {

    private ChatManagerPrx server;

    public CallbackReceiverImp(ChatManagerPrx server) {
        this.server = server;
    }

    @Override
    public void printMsg(String message, Current current) {
        System.out.println(message);
    }
}
