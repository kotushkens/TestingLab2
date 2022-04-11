package trigonometry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import trigonometry.Cos;

import java.io.FileWriter;
import java.io.IOException;

public class Sec {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public Sec() {
        this.cos = new Cos();
    }

    public double sec(double x, double eps) {
        double cosVal = cos.cos(x, eps);
        if (Double.isNaN(cosVal) || cosVal == 0) return Double.NaN;
        return 1 / cosVal;
    }

    public double writeResultToCSV(double x, double eps) {
        double res = sec(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new FileWriter("\\src\\main\\resources\\CsvFiles\\Outputs"))) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}
