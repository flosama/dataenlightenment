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
public class GraphDataService implements DataService {

    // --- Fields --------------------------------------------------------------

    private final UsageGraph graph = new UsageGraph();
    private DataField lastAccessedField = null;

    // --- Constructors --------------------------------------------------------

    public GraphDataService() {
        super();
    }

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    // --- Access --------------------------------------------------------------

    @Override
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

    @Override
    public UsageGraph getGraph() {
        return graph;
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
