/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : pangody
 * Creation : 20.03.2015 23:08:29
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.vis;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jdsc.IEdge;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;
import com.sixgroup.dfi.hackathon.dataenlightenment.UsageGraph;

/**
 * @author pangody
 */
public class DOTWriter extends FilterWriter {

    private final static String INDENT = "    ";

    private final static String CONNECTOR = " -> ";

    // --- Constructors --------------------------------------------------------

    public DOTWriter(Writer writer) {
        super(writer);
    }

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    // --- Access --------------------------------------------------------------

    // --- Element change ------------------------------------------------------

    // --- Status report -------------------------------------------------------

    // --- Status setting ------------------------------------------------------

    // --- Measurement ---------------------------------------------------------

    // --- Cursor movement -----------------------------------------------------

    // --- Removal -------------------------------------------------------------

    // --- Resizing ------------------------------------------------------------

    // --- Transformation ------------------------------------------------------

    public void write(UsageGraph graph) throws IOException {
        write("digraph {\n");
        long graphId = graph.hashCode();
        write("id=\"" + graphId + "\";\n");

        Iterable<? extends IEdge<Integer>> edges = graph.getEdges();
        for (IEdge<Integer> edge : edges) {
            write(INDENT);
            DataField origin = (DataField) edge.getOrigin().getElement();
            write(origin.getName());
            write(CONNECTOR);
            DataField destination = (DataField) edge.getDestination().getElement();
            write(destination.getName());
            write(";\n");
        }

        write("}");
        flush();
    }

    // --- Conversion ----------------------------------------------------------

    // --- Duplication ---------------------------------------------------------

    // --- Comparison ----------------------------------------------------------

    // --- Observation ---------------------------------------------------------

    // --- Miscellaneous -------------------------------------------------------

    // --- Basic operations ----------------------------------------------------

    // --- Deprecated ----------------------------------------------------------

    // --- Inapplicable --------------------------------------------------------

    // --- Initialization ------------------------------------------------------

    // --- Finalization --------------------------------------------------------

    // --- Private implementation ----------------------------------------------

    private void writeAttributes(Writer writer, String indent, Map<String, String> attributes) throws IOException {
        for (Entry<String, String> attribute : attributes.entrySet()) {
            writer.write(indent);
            writer.write(attribute.getKey());
            writer.write("=");
            writer.write(attribute.getValue());
            writer.write(";\n");
        }
    }

    private void writeAttributes(Writer writer, Map<String, String> attributes) throws IOException {
        for (Entry<String, String> attribute : attributes.entrySet()) {
            writer.write(", ");
            writer.write(attribute.getKey());
            writer.write("=");
            writer.write(attribute.getValue());
        }
    }

    private String toString(int value) {
        return "Node" + Integer.toString(value, 16).replace('-', '_');
    }

    // --- Inner types ---------------------------------------------------------

}
