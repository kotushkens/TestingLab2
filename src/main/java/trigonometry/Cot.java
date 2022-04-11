package trigonometry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class Cot {
    private final Sin sin;
    private final Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public Cot() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public double cot(double x, double eps) {
        return cos.cos(x, eps) / sin.sin(x, eps);
    }

    public double writeResultToCSV(double x, double eps) {
        double res = cot(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new FileWriter("\\src\\main\\resources\\CsvFiles\\Outputs\\CotOut.csv"))) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}
