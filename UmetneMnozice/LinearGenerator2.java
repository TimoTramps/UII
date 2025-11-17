import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class LinearGenerator2 {

    // File type enumeration
    public enum FileType { CSV, ARFF }

    public static void main(String[] args) {
         try {
            // Create output file
            PrintWriter outFile = new PrintWriter(new File("UmetnaMnozica2.csv"));

            // 2001 instances, 6 attributes (A1..A6), CSV format
            rLinear(2001, 6, FileType.CSV, outFile);

            outFile.close();
            System.out.println("File generated: UmetnaMnozica2.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The generator function
    public static void rLinear(int numInst, int numAttr, FileType ft, PrintWriter outFile) {
 double[] row = new double[numAttr + 1];  // +1 for R

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
                outFile.println("@relation 'Primer B'");
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

            // Generate A1..A6 ∈ [0,100]
            for (int j = 0; j < numAttr; j++) {
                row[j] = Math.random() * 100;
            }

            // ----- PRIMER B RULE -----
            if (row[1] > 50) { // A2 > 50
                row[numAttr] = 2.5 * row[0] + 3.5 * row[2]; // R = 2.5*A1 + 3.5*A3
            } else {
                row[numAttr] = 2 * row[3] + 0.7 * row[4];   // R = 2*A4 + 0.7*A5
            }

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
