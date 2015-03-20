/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: generator
 * Author   : saynoom
 * Creation : 20.03.2015 20:00:47
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.gen;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

/**
 * @author saynoom
 */
public class Instruction implements Comparable<Instruction> {

    // --- Fields --------------------------------------------------------------

    private final DataField predecessor;
    private final DataField successor;
    private final int weight;

    // --- Constructors --------------------------------------------------------

    public Instruction(DataField predecessor, DataField successor, int weight) {
        super();
        this.predecessor = predecessor;
        this.successor = successor;
        this.weight = weight;
    }

    // --- Properties ----------------------------------------------------------

    public DataField getPredecessor() {
        return predecessor;
    }

    public DataField getSuccessor() {
        return successor;
    }

    public int getWeight() {
        return weight;
    }

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

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

    @Override
    public int hashCode() {
        int hash = 23;
        hash = hash * 31 + predecessor.hashCode();
        hash = hash * 31 + successor.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Instruction && equals((Instruction) obj);
    }

    public boolean equals(Instruction other) {
        return other != null
                && this.predecessor.equals(other.predecessor)
                && this.successor.equals(other.successor);
    }

    @Override
    public int compareTo(Instruction other) {
        if (other == null)
            return -1;
        int comp = this.predecessor.compareTo(other.predecessor);
        if (comp == 0)
            comp = this.successor.compareTo(other.successor);
        return comp;
    }

    // --- Display -------------------------------------------------------------

    @Override
    public String toString() {
        return predecessor + " -> " + successor + " (" + weight + ")";
    }

    // --- Serialization -------------------------------------------------------

    // --- Initialization ------------------------------------------------------

    // --- Finalization --------------------------------------------------------

    // --- Private implementation ----------------------------------------------

    // --- Inner types ---------------------------------------------------------

}
