/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: api
 * Author   : saynoom
 * Creation : 20.03.2015 19:35:19
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.vis;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;
import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.UsageGraph;

/**
 * @author saynoom
 */
public class GraphDataService implements DataService {

    // --- Fields --------------------------------------------------------------

    private final UsageGraph graph;
    private DataField lastAccessedField = null;

    // --- Constructors --------------------------------------------------------

    public GraphDataService(UsageGraph graph) {
        super();
        this.graph = graph;
    }

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
        return null;
    }

}
