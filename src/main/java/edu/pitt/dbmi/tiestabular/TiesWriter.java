package edu.pitt.dbmi.tiestabular;

import java.io.PrintStream;
import java.io.Writer;

/**
 * Author : Jeremy Espino MD
 * Created  6/7/18 2:23 PM
 */
public class TiesWriter {

    public static void write(TiesData data, PrintStream streamOut, Integer minOccurences, Float minFractionOccurrences, boolean useCuiName) {

        String separator = ",";

        // calculate minOccurrences if using percent
        if (minOccurences == null && minFractionOccurrences != null) {
            minOccurences = Math.round(data.ids.size() * minFractionOccurrences);
        }

        // counts of cuis for debugging
        /**
        for (String cui : data.cuiCount.keySet()) {
            if (minOccurences != null && data.cuiCount.get(cui) < minOccurences) continue;
            streamOut.println(cui + " = " + data.cuiCount.get(cui));
        }
         **/


        // output the header
        streamOut.print("\"id\"");
        for (String cui : data.cuiCount.keySet()) {
            if (minOccurences != null && data.cuiCount.get(cui) < minOccurences) continue;

            // replace cui with name if available
            if (useCuiName) {
                if (data.cuiName.get(cui) != null) cui = cui + "|" + data.cuiName.get(cui);
            }
            streamOut.print(separator+ "\"" + cui + "\"");
        }
        streamOut.print("\n");

        // output status of cui per id
        String idCui;
        int status = 0;
        for (String id : data.ids) {
            streamOut.print("\"" + id + "\"");

            for (String cui : data.cuiCount.keySet()) {

                // skip if doesn't meet criteria
                if (minOccurences != null && data.cuiCount.get(cui) < minOccurences) continue;

                // get cui status for this id
                status = 0;
                idCui = id + "|" + cui;
                if (data.idCuiValue.containsKey(idCui)) status = data.idCuiValue.get(idCui);

                streamOut.print(separator + status);
            }
            streamOut.print("\n");
        }
    }
}
