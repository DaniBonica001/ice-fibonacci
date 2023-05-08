import com.zeroc.Ice.Current;
import Talker.*;
public class ChatClientImp implements ChatClient{

    @Override
    public void notifyCallback(){
        System.out.println("notifyCallback");
    }
    @Override
    public void reciveMessage(String message, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reciveMessage'");
    }

}