/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : fmahler
 * Creation : 20.03.2015 23:32:23
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

/**
 * @author fmahler
 */
public class MarkovChain {

    // --- Fields --------------------------------------------------------------

    private Hashtable<List<DataField>, Set<DataNode>> continuations;

    // --- Constructors --------------------------------------------------------

    public MarkovChain() {
        continuations = new Hashtable<>();
    }

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    public void addContinuation(List<DataField> prefix, DataField suffix, int count) {
        Set<DataNode> suffices = continuations.get(prefix);
        if (suffices != null) {
            suffices.add(new DataNode(suffix, count));
        }
        else {
            suffices = new HashSet<>();
            suffices.add(new DataNode(suffix, count));
            continuations.put(prefix, suffices);
        }
    }

    public void addMultipleContinuations(List<DataField> prefix, Set<DataNode> suffices) {
        Set<DataNode> existingSuffices = continuations.get(prefix);
        if (suffices != null) {
            existingSuffices.addAll(suffices);
        }
        else {
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
