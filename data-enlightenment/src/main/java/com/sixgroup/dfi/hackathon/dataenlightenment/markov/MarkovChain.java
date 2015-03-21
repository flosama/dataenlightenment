/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: markov
 * Author   : fmahler
 * Creation : 20.03.2015 23:32:23
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import java.util.HashMap;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

/**
 * @author fmahler
 */
public class MarkovChain {

    // --- Fields --------------------------------------------------------------

    private HashMap<DataFieldTuple, HashMap<DataField, Integer>> continuations;

    // --- Constructors --------------------------------------------------------

    public MarkovChain() {
        continuations = new HashMap<>();
    }

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    public void increment(DataFieldTuple prefix, DataField suffix) {
        HashMap<DataField, Integer> existingSuffices = continuations.get(prefix);
        if (existingSuffices != null) {
            if (existingSuffices.containsKey(suffix)) {
                Integer count = existingSuffices.get(suffix);
                count += 1;
            }
            else {
                existingSuffices.put(suffix, new Integer(1));
            }
        }
        else {
            HashMap<DataField, Integer> suffices = new HashMap<>();
            suffices.put(suffix, new Integer(1));
            continuations.put(prefix, suffices);
        }

    }

    // --- Access --------------------------------------------------------------

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
