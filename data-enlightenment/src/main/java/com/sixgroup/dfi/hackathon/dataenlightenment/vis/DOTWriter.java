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
            write('\"');
            write(origin.getName());
            write('\"');

            write(CONNECTOR);

            DataField destination = (DataField) edge.getDestination().getElement();
            write('\"');
            write(destination.getName());
            write('\"');

            write(' ');
            write('[');

            write("penwidth=");
            float count = edge.getElement();
            count /= 2;
            String penwidth = Float.toString(count);
            write(penwidth);

            write(']');
            write(';');
            write('\n');
        }

        write('}');
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

    // --- Inner types ---------------------------------------------------------

}
