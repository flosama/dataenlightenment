/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 01:41:32
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

/**
 * @author saynoom
 */
public class DataFieldTuple implements Iterable<DataField> {

    // --- Fields --------------------------------------------------------------

    private final DataField[] fields;

    // --- Constructors --------------------------------------------------------

    public DataFieldTuple(DataField... fields) {
        super();
        if (fields == null || fields.length == 0)
            throw new IllegalArgumentException("Missing a field.");
        this.fields = fields;
    }

    public DataFieldTuple(List<DataField> fields) {
        this(fields.toArray(new DataField[fields.size()]));
    }

    // --- Access --------------------------------------------------------------

    public DataField[] getFields() {
        return fields;
    }

    @Override
    public Iterator<DataField> iterator() {
        return Arrays.asList(fields).iterator();
    }

    // --- Comparison ----------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(fields);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DataFieldTuple
                && equals((DataFieldTuple) obj);
    }

    public boolean equals(DataFieldTuple other) {
        return other != null && Arrays.equals(this.fields, other.fields);
    }

    // --- Display -------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(fields[0]);
        for (int i = 1; i < fields.length; i++)
            sb.append(", ").append(fields[i]);
        sb.append(')');
        return sb.toString();
    }

}
