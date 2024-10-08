package ai.sahaj.kafkapubsub.producer;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class RidesUtil {

  public List<Ride> getRides() throws IOException, CsvException {
    var ridesStream = this.getClass().getResource("/rides.csv");
    var reader = new CSVReader(new FileReader(ridesStream.getFile()));
    reader.skip(1);
    return reader.readAll().stream().map(arr -> new Ride(arr))
      .collect(Collectors.toList());
  }
}
