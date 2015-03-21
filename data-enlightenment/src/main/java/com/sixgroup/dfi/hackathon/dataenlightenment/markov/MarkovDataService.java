/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 01:58:03
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import java.util.ArrayList;
import java.util.List;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;
import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;

/**
 * @author saynoom
 */
public class MarkovDataService implements DataService {

    // --- Fields --------------------------------------------------------------

    private final MarkovChain markovChain;
    private final int markovRank;

    private List<DataField> lastAccessedFields;

    // --- Constructors --------------------------------------------------------

    public MarkovDataService(MarkovChain markovChain, int markovRank) {
        super();
        this.markovChain = markovChain;
        this.markovRank = markovRank;
        this.lastAccessedFields = new ArrayList<>(markovRank);
    }

    // --- Access --------------------------------------------------------------

    @Override
    public String getData(DataField field) {
        if (lastAccessedFields.size() == markovRank) {
            markovChain.increment(new DataFieldTuple(lastAccessedFields), field);
            lastAccessedFields.remove(0);
        }
        lastAccessedFields.add(field);

        // TODO return the requested data
        return null;
    }

}
