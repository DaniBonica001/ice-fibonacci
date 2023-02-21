public class PrinterI implements Demo.Printer {
    
    public long sequenceFibonacci(String n, com.zeroc.Ice.Current current) {

        String[] arr = n.split(":");
        String[] data = sequenceFibonacci(Integer.parseInt(arr[1]), "").split("-");
<<<<<<< HEAD

        System.out.println("***********"+data[0]);

        System.out.println(
                "Client: " + arr[0] + "\n"
                        + "Number: " + arr[1] + "\n"
                        + "Sequence: " + data[1] + "\n"
                        + "Result: " + data[0] + "\n"
                        + "----------------------------------------");

        return Long.parseLong(data[0]);
=======
       
        int result = Integer.parseInt(data[0]);

        System.out.println(
                "Client: " + arr[0] + "\n"
                + "Number: " + arr[1] + "\n"
                + "Sequence: " + data[1] + "\n"
                + "Result: " + result + "\n"
                + "----------------------------------------");
        
        return result;
>>>>>>> 989d32bf3cc603544b36d88e8b0486ed2d11d0b1
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

                System.out.println("///////"+n3);

                return n3 + "-" + s;
            }
        }else{
            return 0 + "-" + n;
        }

    }
}