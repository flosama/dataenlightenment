/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 00:29:34
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

import java.util.ArrayList;
import java.util.List;

import com.sixgroup.dfi.hackathon.dataenlightenment.markov.DataFieldTuple;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.MarkovChain;

/**
 * @author saynoom
 */
public class DataService {

    // --- Fields --------------------------------------------------------------

    private final UsageGraph usageGraph;
    private DataField lastAccessedField = null;

    private final MarkovChain markovChain;
    private final int markovRank;
    private List<DataField> lastAccessedFields;

    // --- Constructors --------------------------------------------------------

    public DataService(UsageGraph usageGraph, MarkovChain markovChain, int markovRank) {
        super();
        this.usageGraph = usageGraph;

        this.markovChain = markovChain;
        this.markovRank = markovRank;
        this.lastAccessedFields = new ArrayList<>(markovRank);
    }

    // --- Access --------------------------------------------------------------

    public UsageGraph getUsageGraph() {
        return usageGraph;
    }

    public MarkovChain getMarkovChain() {
        return markovChain;
    }

    /**
     * Requests the data of the specified {@link DataField field}.
     * 
     * @param field
     *            The {@link DataField} to access.
     * @return the data of the specified {@link DataField field}.
     */
    public String getData(DataField field) {
        trainUsageGraph(field);
        trainMarkovChain(field);

        // TODO return the requested data
        return null;
    }

    private void trainUsageGraph(DataField field) {
        if (lastAccessedField != null) {
            DataField predecessor = this.lastAccessedField;
            DataField successor = field;
            usageGraph.insert(predecessor, successor);
        }
        this.lastAccessedField = field;
    }

    private void trainMarkovChain(DataField field) {
        if (lastAccessedFields.size() == markovRank) {
            markovChain.increment(new DataFieldTuple(lastAccessedFields), field);
            lastAccessedFields.remove(0);
        }
        lastAccessedFields.add(field);
    }

}
