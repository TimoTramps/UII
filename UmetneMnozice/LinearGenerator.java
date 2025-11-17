import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class LinearGenerator {

    // File type enumeration
    public enum FileType { CSV, ARFF }

    public static void main(String[] args) {
         try {
            // Create output file
            PrintWriter outFile = new PrintWriter(new File("UmetnaMnozica1.csv"));

            // 2001 instances, 6 attributes (A1..A6), CSV format
            rLinear(2001, 6, FileType.CSV, outFile);

            outFile.close();
            System.out.println("File generated: UmetnaMnozica1.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The generator function
    public static void rLinear(int numInst, int numAttr, FileType ft, PrintWriter outFile) {

        
        double[] row = new double[numAttr + 1];  // +1 for R (class)

        // ---- HEADER ----
        switch (ft) {
            case CSV:
                for (int i = 0; i < row.length; i++) {
                    if (i == row.length - 1)
                        outFile.print("R");
                    else
                        outFile.print("A" + (i + 1) + ",");
                }
                outFile.println();
                break;

            case ARFF:
                outFile.println("@relation 'Linear: R = A1 + 2*A2 + 3*A3'");
                outFile.println();
                for (int i = 0; i < row.length; i++) {
                    if (i == row.length - 1)
                        outFile.println("@attribute R numeric");
                    else
                        outFile.println("@attribute A" + (i + 1) + " numeric");
                }
                outFile.println();
                outFile.println("@data");
                break;
        }

        // ---- DATA ----
        for (int i = 0; i < numInst; i++) {

            // A1..A5 ∈ [0,100]
            for (int j = 0; j < numAttr; j++) {
                row[j] = Math.random() * 100;
            }

            // R = A1 + 2*A2 + 3*A3
            row[numAttr] = row[0] + 2 * row[1] + 3 * row[2];

            print1dToF(row, outFile);
        }
    }

    // Simple CSV-style printer
    public static void print1dToF(double[] arr, PrintWriter outFile) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                outFile.print(arr[i]);
            else
                outFile.print(arr[i] + ",");
        }
        outFile.println();
    }
}
