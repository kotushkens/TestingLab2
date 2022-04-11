package logarithm;

import logarithm.Ln;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class Log {

    private final Ln ln;

    public Log(Ln ln) {
        this.ln = ln;
    }

    public Log() {
        this.ln = new Ln();
    }

    public double log(double a, double b, double esp) {
        return ln.ln(b, esp) / ln.ln(a, esp);
    }

    public double writeResultToCSV(double a, double b, double eps) {
        double res = log(a, b, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new FileWriter("\\src\\main\\resources\\CsvFiles\\Outputs"))) {
            printer.printRecord(a, b, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}
