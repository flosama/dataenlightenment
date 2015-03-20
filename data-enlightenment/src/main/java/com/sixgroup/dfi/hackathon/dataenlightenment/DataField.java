/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: api
 * Author   : saynoom
 * Creation : 20.03.2015 19:38:28
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

/**
 * @author saynoom
 */
public class DataField implements Comparable<DataField> {

    // --- Fields --------------------------------------------------------------

    private final String name;

    // --- Constructors --------------------------------------------------------

    public DataField(String name) {
        this.name = name;
    }

    // --- Properties ----------------------------------------------------------

    /**
     * @return the name
     */
    public String getName() {
        return name;
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

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DataField && equals((DataField) obj);
    }

    public boolean equals(DataField other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public int compareTo(DataField other) {
        if (other == null)
            return -1;
        return this.name.compareTo(other.name);
    }

    // --- Duplication ---------------------------------------------------------

    // --- Conversion ----------------------------------------------------------

    // --- Display -------------------------------------------------------------

    @Override
    public String toString() {
        return name;
    }

    // --- Serialization -------------------------------------------------------

    // --- Initialization ------------------------------------------------------

    // --- Finalization --------------------------------------------------------

    // --- Private implementation ----------------------------------------------

    // --- Inner types ---------------------------------------------------------

}
