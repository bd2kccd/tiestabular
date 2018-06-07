package edu.pitt.dbmi.tiestabular;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Author : Jeremy Espino MD
 * Created  6/7/18 2:22 PM
 */
public class TiesData {

    public TreeMap<String, Integer> cuiCount = new TreeMap<String, Integer>();
    public HashMap<String, Integer> idCuiValue = new HashMap<String, Integer>();
    public HashMap<String, String> cuiName = new HashMap<String,String>();
    public HashSet<String> ids = new HashSet<String>();

}
