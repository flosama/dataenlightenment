/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: markov
 * Author   : fmahler
 * Creation : 21.03.2015 01:50:12
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

/**
 * @author fmahler
 */
public class DataNode {

    private DataField field;
    private int count;

    public DataNode(DataField field) {
        if (field == null)
            throw new IllegalArgumentException("No null value allowed");
        this.field = field;
        count = 1;
    }

    public DataField getField() {
        return field;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count += 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !Object.class.equals(DataNode.class) || !((DataNode) other).getField().equals(field)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return ("nodeof" + field.toString()).hashCode();
    }

}
