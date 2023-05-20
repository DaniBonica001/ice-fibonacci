import java.net.Inet4Address;
import java.util.Scanner;

import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

import Demo.CallbackReceiverPrx;
import Demo.CallbackSenderPrx;

public class Client {

    public static void main(String[] args) {

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "config.client")) {

            Demo.ChatManagerPrx serverPrx = Demo.ChatManagerPrx
                    .checkedCast(communicator.propertyToProxy("Server.Proxy"));

            try {

                CallbackReceiverImp callback = new CallbackReceiverImp(serverPrx);
                ObjectAdapter adapter = communicator.createObjectAdapter("CallbackReceiver");
                ObjectPrx proxy = adapter.add(callback, Util.stringToIdentity("CallbackReceiver"));

                adapter.activate();
                String hostname = Inet4Address.getLocalHost().getHostName();
                CallbackReceiverPrx clientPrx = CallbackReceiverPrx.uncheckedCast(proxy);

                serverPrx.subscribe(clientPrx, hostname);

                Scanner sc = new Scanner(System.in);
                String x = sc.nextLine();
                while (!x.equals("exit")) {
                    serverPrx.sendMessage(x, hostname);
                    x = sc.nextLine();
                }
                System.out.println("Bye!");
                sc.close();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

}