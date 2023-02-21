import java.util.Scanner;
import java.net.InetAddress;

public class Client {
    public final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "config.client",
                extraArgs)) {

            // com.zeroc.Ice.ObjectPrx base =
            // communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                    communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            // Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = twoway.ice_twoway();

            if (printer == null) {
                throw new Error("Invalid proxy");
            }

            String localIP = InetAddress.getLocalHost().getHostName();
            System.out.println("¿Qué numero de la serie fibonacci desea ver?");
            String n = scanner.nextLine();

            while (!n.equalsIgnoreCase("exit")) {

                long number = printer.sequenceFibonacci(localIP + ":" + n);
                System.out.println(localIP + ":" + number);
                System.out.println("¿Qué numero de la serie fibonacci desea ver?");
                n = scanner.nextLine();

            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}