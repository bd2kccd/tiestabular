package edu.pitt.dbmi.tiestabular;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;

/**
 * Author : Jeremy Espino MD
 * Created  6/7/18 10:25 AM
 */
public class TiesParser {


    public static void parseCuiName(TiesData data, Reader in) throws Exception {
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String code = record.get("code");
            String name = record.get("name");

            // replace any non-alpha chars with underscore (tetrad likes this)
            name = name.replaceAll("[^A-Za-z0-9]", "_");

            data.cuiName.put(code,name);
        }
    }


    public static void parseCuiStatus(TiesData data, Reader in) throws Exception {


        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        String id, idCui, cui;
        int status = 0;
        for (CSVRecord record : records) {
            id = "";
            if (record.size() > 1) {
                id = record.get(1);
                data.ids.add(id);
            }

            for (int colIdx = 2; colIdx < record.size(); colIdx++) {
                status = 0;

                cui = record.get(colIdx);

                if (cui.equalsIgnoreCase("")) continue;

                if (cui.startsWith("N")) {
                    status = -1;
                    cui = cui.substring(1, cui.length());
                } else if (cui.startsWith("A")) {
                    status = 1;
                    cui = cui.substring(1, cui.length());
                } else {
                    System.out.println("ERROR");
                }
                idCui = id + "|" + cui;
                data.idCuiValue.put(idCui, status);

                if (data.cuiCount.containsKey(cui)) {
                    data.cuiCount.put(cui, data.cuiCount.get(cui) + 1);
                } else {
                    data.cuiCount.put(cui, 1);
                }

            }

        }
    }



}
