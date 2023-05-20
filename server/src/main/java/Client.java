import Demo.CallbackReceiverPrx;

public class Client {
    
    private CallbackReceiverPrx callbackReceiverPrx;
    private String hostname;

    public Client(CallbackReceiverPrx callbackReceiverPrx, String hostname){
        this.callbackReceiverPrx = callbackReceiverPrx;
        this.hostname = hostname;
    }

    public CallbackReceiverPrx getClientProxy(){
        return this.callbackReceiverPrx;
    }

    public void setClientProxy(CallbackReceiverPrx callbackReceiverPrx){
        this.callbackReceiverPrx = callbackReceiverPrx;
    }

    public String getHostname(){
        return this.hostname;        
    }

    public void setHostname(String hostname){
        this.hostname = hostname;
    }
}
