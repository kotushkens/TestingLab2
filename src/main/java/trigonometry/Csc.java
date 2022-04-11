package trigonometry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;


public class Csc {

    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public Csc() {
        this.sin = new Sin();
    }

    public double csc(double x, double eps) {
        double sinVal = sin.sin(x, eps);
        if (Double.isNaN(sinVal) || sinVal == 0) return Double.NaN;
        return 1 / sinVal;
    }

    public double writeResultToCSV(double x, double eps) {
        double res = csc(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new FileWriter("\\src\\main\\resources\\CsvFiles\\Outputs"))) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}

