import java.util.Scanner;

public class Client
{
    public final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("¿Qué numero de la serie fibonacci desea ver?");
        int n = scanner.nextInt();

        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.client",extraArgs))
        {

            
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = twoway.ice_twoway();
            

            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }

            int number = printer.sequenceFibonacci(n);
            System.out.println("El numero es: " + number);
        }
    }
}