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
public interface DataService {

    /**
     * Requests the data of the specified {@link DataField field}.
     * 
     * @param field
     *            The {@link DataField} to access.
     * @return the data of the specified {@link DataField field}.
     */
    public String getData(DataField field);

}
