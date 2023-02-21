public class PrinterI implements Demo.Printer {
    
    public long sequenceFibonacci(String n, com.zeroc.Ice.Current current) {

        String[] arr = n.split(":");
        String[] data = sequenceFibonacci(Integer.parseInt(arr[1]), "").split("-");        

        System.out.println(
                "Client: " + arr[0] + "\n"
                        + "Number: " + arr[1] + "\n"
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