public class PrinterI implements Demo.Printer {
    public int sequenceFibonacci(int n, com.zeroc.Ice.Current current) {
        if (n == 0) {
            return 0;
        }
        // Base cases return itself 0 and 1
        else if (n == 1) {
            return 1;
        } else {
            return sequenceFibonacci(n - 1,current) + sequenceFibonacci(n - 2,current);
            // Recursive calls
        }
    }
}