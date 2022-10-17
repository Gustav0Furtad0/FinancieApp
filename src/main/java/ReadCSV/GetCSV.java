package ReadCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class GetCSV {
    public static List<String[]> read(String link) throws IOException {
        String st = link; // "https://static.quandl.com/coverage/WIKI_PRICES.csv"
        URL stockURL = new URL(st);
        BufferedReader in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
        Reader reader = in;
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> pessoas = csvReader.readAll();
        return pessoas;
    }
}
