import functions.Function;
import logarithm.Ln;
import logarithm.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import trigonometry.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

class FunctionTest {

    static double functionEps = 0.1;
    double eps = 0.1;

    static Sec secMock;
    static Cos cosMock;
    static Sin sinMock;
    static Ln lnMock;
    static Log logMock;
    static Cot cotMock;
    static Csc cscMock;
    static Tan tanMock;

    static Reader secIn;
    static Reader cosIn;
    static Reader sinIn;
    static Reader lnIn;
    static Reader log2In;
    static Reader log10In;
    static Reader cotIn;
    static Reader cscIn;
    static Reader tanIn;


    @BeforeAll
    static void init() {
        secMock = Mockito.mock(Sec.class);
        cosMock = Mockito.mock(Cos.class);
        sinMock = Mockito.mock(Sin.class);
        lnMock = Mockito.mock(Ln.class);
        logMock = Mockito.mock(Log.class);
        cotMock = Mockito.mock(Cot.class);
        cscMock = Mockito.mock(Csc.class);
        tanMock = Mockito.mock(Tan.class);
        try {
            secIn = new FileReader("src/main/resources/CsvFiles/Inputs/SecIn.csv");
            cosIn = new FileReader("src/main/resources/CsvFiles/Inputs/CosIn.csv");
            sinIn = new FileReader("src/main/resources/CsvFiles/Inputs/SinIn.csv");
            lnIn = new FileReader("src/main/resources/CsvFiles/Inputs/LnIn.csv");
            log2In = new FileReader("src/main/resources/CsvFiles/Inputs/Log2In.csv");
            log10In = new FileReader("src/main/resources/CsvFiles/Inputs/Log10In.csv");
            cotIn = new FileReader("src/main/resources/CsvFiles/Inputs/CotIn.csv");
            cscIn = new FileReader("src/main/resources/CsvFiles/Inputs/CscIn.csv");
            tanIn = new FileReader("src/main/resources/CsvFiles/Inputs/TanIn.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.sec(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.cos(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.sin(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(lnMock.ln(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.log(2, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log10In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.log(10, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records) {
                Mockito.when(cscMock.csc(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cotIn);
            for (CSVRecord record : records) {
                Mockito.when(cotMock.cot(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tanIn);
            for (CSVRecord record : records) {
                Mockito.when(tanMock.tan(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException ex) {
            System.err.println("Не удалось открыть файл");
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CotIn.csv")
    void testTheCot(double value, double expected) {
        Function function = new Function();
        Assertions.assertEquals(expected, cotMock.cot(value, functionEps), eps * 20);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/TanIn.csv")
    void testTheTan(double value, double expected) {
        Function function = new Function();
        Assertions.assertEquals(expected, tanMock.tan(value, functionEps), eps * 20);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/CscIn.csv")
    void testTheCsc(double value, double expected) {
        Function function = new Function();
        Assertions.assertEquals(expected, cscMock.csc(value, functionEps), eps * 20);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testTheFunction(double value, double expected) {
        Function function = new Function(secMock, lnMock, logMock, cotMock, cosMock, cscMock, tanMock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps * 20);
    }
}