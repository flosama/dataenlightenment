/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 00:29:34
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

/**
 * @author saynoom
 */
public interface DataService {

    /**
     * Requests the data of the specified {@link DataField field}.
     * 
     * @param field
     *            The {@link DataField} to access.
     * @return the data of the specified {@link DataField field}.
     */
    public String getData(DataField field);

    /**
     * @return
     */
    public UsageGraph getGraph();

}
