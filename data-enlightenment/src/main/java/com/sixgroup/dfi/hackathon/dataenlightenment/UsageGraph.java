/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 20.03.2015 23:14:40
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.jdsc.AdjacencyList;
import net.sf.jdsc.IEdge;
import net.sf.jdsc.IGraph;
import net.sf.jdsc.IVertex;

/**
 * @author saynoom
 */
public class UsageGraph {

    // --- Fields --------------------------------------------------------------

    private final IGraph<DataField, Integer> graph = new AdjacencyList<>();
    private final Map<DataField, IVertex<DataField>> vertices = new HashMap<>();

    // --- Constructors --------------------------------------------------------

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    public void insert(DataField predecessor, DataField successor) {
        IVertex<DataField> origin = getVertex(predecessor);
        IVertex<DataField> destination = getVertex(successor);
        IEdge<Integer> edge = graph.getEdge(origin, destination);
        if (edge == null) {
            edge = graph.insertEdge(origin, destination, 1);
        } else {
            int count = edge.getElement();
            count++;
            edge.setElement(count);
        }
    }

    private IVertex<DataField> getVertex(DataField field) {
        IVertex<DataField> vertex = vertices.get(field);
        if (vertex == null) {
            vertex = graph.insertVertex(field);
            vertices.put(field, vertex);
        }
        return vertex;
    }

    // --- Access --------------------------------------------------------------

    public int getWeight(DataField predecessor, DataField successor) {
        IVertex<DataField> origin = getVertex(predecessor);
        IVertex<DataField> destination = getVertex(successor);
        IEdge<Integer> edge = graph.getEdge(origin, destination );
        return edge != null ? edge.getElement().intValue() : 0;
    }
    
    public Iterable<? extends IEdge<Integer>> getEdges() {
        return graph.edges();
    }
    
    public Set<DataField> getVertices() {
        return Collections.unmodifiableSet(vertices.keySet());
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
