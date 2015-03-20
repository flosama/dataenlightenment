/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: api
 * Author   : saynoom
 * Creation : 20.03.2015 19:35:19
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

/**
 * @author saynoom
 */
public class DataService {

    // --- Fields --------------------------------------------------------------

    private final UsageGraph graph = new UsageGraph();
    private DataField lastAccessedField = null;

    // --- Constructors --------------------------------------------------------

    public DataService() {
        super();
    }

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    // --- Access --------------------------------------------------------------

    /**
     * Requests the data of the specified {@link DataField field}.
     * 
     * @param field
     *            The {@link DataField} to access.
     * @return the data of the specified {@link DataField field}.
     */
    public String getData(DataField field) {
        if (lastAccessedField != null) {
            DataField predecessor = this.lastAccessedField;
            DataField successor = field;
            graph.insert(predecessor, successor);
        }
        this.lastAccessedField = field;

        // TODO return the requested data
        return "Data of " + field;
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
