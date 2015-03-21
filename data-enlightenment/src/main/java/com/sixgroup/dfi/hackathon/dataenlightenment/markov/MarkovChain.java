/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: markov
 * Author   : fmahler
 * Creation : 20.03.2015 23:32:23
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

/**
 * @author fmahler
 */
public class MarkovChain {

    // --- Fields --------------------------------------------------------------

    private HashMap<DataFieldTuple, HashMap<DataField, DataNode>> continuations;
    private Random rnd;

    // --- Constructors --------------------------------------------------------

    public MarkovChain() {
        continuations = new HashMap<>();
        rnd = new Random();
    }

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    public void increment(DataFieldTuple prefix, DataField suffix) {
        HashMap<DataField, DataNode> existingSuffices = continuations.get(prefix);
        if (existingSuffices != null) {
            DataNode oldSuffix = existingSuffices.get(suffix);
            if (oldSuffix == null) {
                existingSuffices.put(suffix, new DataNode(suffix));
            }
            else {
                oldSuffix.increment();
            }
        }
        else {
            HashMap<DataField, DataNode> suffices = new HashMap<>();
            suffices.put(suffix, new DataNode(suffix));
            continuations.put(prefix, suffices);
        }

    }

    // --- Access --------------------------------------------------------------

    public DataField getNextField(DataFieldTuple prefix) {
        HashMap<DataField, DataNode> suffices = continuations.get(prefix);
        if (suffices == null) {
            return null;
        }
        int totalCount = getTotalCount(suffices);
        int randomLimit = rnd.nextInt(totalCount);
        for (DataNode node : suffices.values()) {
            if (randomLimit <= 0 || randomLimit < node.getCount()) {
                return node.getField();
            }
            randomLimit -= node.getCount();
        }
        return null;
    }

    private int getTotalCount(HashMap<DataField, DataNode> suffices) {
        int totalCount = 0;
        for (DataNode node : suffices.values()) {
            totalCount += node.getCount();
        }
        return totalCount;
    }

    public DataFieldTuple getRandomPrefix() {
        int numberOfPrefixes = continuations.keySet().size();
        Random random = new Random();
        int randomIndex = random.nextInt(numberOfPrefixes);
        Iterator<DataFieldTuple> iterator = continuations.keySet().iterator();
        DataFieldTuple prefix = iterator.next();
        for (int i = 0; i < randomIndex; i++) {
            prefix = iterator.next();
        }
        return prefix;
    }
    // --- Examination ---------------------------------------------------------

    // --- Editing -------------------------------------------------------------

    // --- Removal -------------------------------------------------------------

    // --- Measurement ---------------------------------------------------------

    // --- Status report -------------------------------------------------------

    // --- Status setting ------------------------------------------------------

    // --- Cursor movement -----------------------------------------------------

    // --- Actions -------------------------------------------------------------

    // --- Basic operations ----------------------------------------------------

    // --- Miscellaneous -------------------------------------------------------

    // --- Transformation ------------------------------------------------------

    // --- Observation ---------------------------------------------------------

    // --- Comparison ----------------------------------------------------------

    // --- Duplication ---------------------------------------------------------

    // --- Conversion ----------------------------------------------------------

    // --- Display -------------------------------------------------------------

    // --- Serialization -------------------------------------------------------

    // --- Initialization ------------------------------------------------------

    // --- Finalization --------------------------------------------------------

    // --- Private implementation ----------------------------------------------

    // --- Inner types ---------------------------------------------------------

}
