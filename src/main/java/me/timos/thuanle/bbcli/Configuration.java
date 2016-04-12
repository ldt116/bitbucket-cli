package me.timos.thuanle.bbcli;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * Created by thuanle on 4/12/16.
 */
public class Configuration {
    private final List<CSVRecord> mRecords;
    private final Map<String, Integer> mHeaderMap;
    private String mUser;
    private String mPass;

    public Configuration(String user, String pass, File inputFile) throws IOException {
        mUser = user;
        mPass = pass;
        Reader in = new FileReader(inputFile);

        CSVParser csvParser = CSVFormat.EXCEL
                .withIgnoreEmptyLines()
                .withIgnoreSurroundingSpaces()
                .withHeader()
                .parse(in);
        mHeaderMap = csvParser.getHeaderMap();
        mRecords = csvParser.getRecords();
    }

    public int countRecords() {
        return mRecords.size();
    }

    public String getPass() {
        return mPass;
    }

    public String getRecordField(int index, String name) {
        return mRecords.get(index).get(name);
    }

    public String getUser() {
        return mUser;
    }

    public boolean hasField(String fieldName) {
        return mHeaderMap.containsKey(fieldName);
    }
}
