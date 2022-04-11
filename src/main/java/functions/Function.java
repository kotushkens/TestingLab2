package functions;

import logarithm.Ln;
import logarithm.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.math3.analysis.function.Pow;
import trigonometry.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Function {
    Sec sec;
    Log log;
    Ln ln;
    Cot cot;
    Cos cos;
    Csc csc;
    Tan tan;

    public Function() {
        this.sec = new Sec();
        this.ln = new Ln();
        this.log = new Log(ln);
        this.cot = new Cot();
        this.cos = new Cos();
        this.csc = new Csc();
        this.tan = new Tan();
    }

    public Function(Sec sec, Ln ln, Log log, Cot cot, Cos cos, Csc csc, Tan tan) {
        this.sec = sec;
        this.log = log;
        this.ln = ln;
        this.cot = cot;
        this.cos = cos;
        this.csc = csc;
        this.tan = tan;
    }

    public double SystemSolve(double x, double eps) {
        if (x <= 0) return ( (Math.pow(Math.pow((sec.sec(x, eps) / cot.cot(x, eps)), 2), 2) * ((sec.sec(x, eps) + cos.cos(x, eps) +
                tan.tan(x, eps)) / csc.csc(x, eps))) / csc.csc(x, eps) );
        else
            return (Math.pow(Math.pow((((Math.pow(log.log(3, x, eps), 2)) - log.log(2, x, eps))
                    / ln.ln(x, eps)), 2), 2));
    }

    public double writeResultToCSV(double x, double eps) {
        double res = SystemSolve(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new FileWriter("\\src\\main\\resources\\CsvFiles\\Outputs\\SystemOut.csv"))) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}
