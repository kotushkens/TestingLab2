package trigonometry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import trigonometry.Cos;
import trigonometry.Sin;

import java.io.FileWriter;
import java.io.IOException;

public class Tan {
    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin =  sin;
        this.cos = cos;
    }

    public Tan(){
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public double tan(double x, double eps) {
        return sin.sin(x, eps) / cos.cos(x, eps);
    }

    public double writeResultToCSV(double x, double eps) {
        double res = tan(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new FileWriter("\\src\\main\\resources\\CsvFiles\\Outputs\\TanOut.csv"))) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}

