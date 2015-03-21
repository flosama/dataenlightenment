/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 00:25:25
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.gen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author saynoom
 */
public class VDF {

    private static final String META_FILE = "vdf-structures-en.txt";
    private static Map<String, String> MAPPING;

    static {
        Map<String, String> mapping = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(META_FILE)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(':');
                if (index < 0)
                    continue;

                String key = line.substring(0, index).trim();
                String name = line.substring(index + 1).trim();
                mapping.put(key, name);
            }
        } catch (IOException e) {
            throw new Error(e);
        }
        VDF.MAPPING = mapping;
    }

    public static String getName(String key) {
        return MAPPING.get(key);
    }

    public static Set<String> getKeys() {
        return Collections.unmodifiableSet(MAPPING.keySet());
    }

}
