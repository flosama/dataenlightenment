/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: api
 * Author   : saynoom
 * Creation : 20.03.2015 19:35:19
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

import java.util.HashMap;
import java.util.Map;

import net.sf.jdsc.AdjacencyList;
import net.sf.jdsc.IEdge;
import net.sf.jdsc.IGraph;
import net.sf.jdsc.IVertex;

/**
 * @author saynoom
 */
public class DataService {

    // --- Fields --------------------------------------------------------------

    private final IGraph<DataField, Integer> graph = new AdjacencyList<>();
    private final Map<DataField, IVertex<DataField>> vertices = new HashMap<>();

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
            IVertex<DataField> predecessor = getVertex(this.lastAccessedField);
            IVertex<DataField> successor = getVertex(field);
            IEdge<Integer> edge = graph.getEdge(predecessor, successor);
            if (edge == null) {
                edge = graph.insertEdge(predecessor, successor, 1);
            } else {
                int count = edge.getElement();
                count++;
                edge.setElement(count);
            }
        }
        this.lastAccessedField = field;

        // TODO return the requested data
        return "Data of " + field;
    }

    private IVertex<DataField> getVertex(DataField field) {
        IVertex<DataField> vertex = vertices.get(field);
        if (vertex == null) {
            vertex = graph.insertVertex(field);
            vertices.put(field, vertex);
        }
        return vertex;
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
