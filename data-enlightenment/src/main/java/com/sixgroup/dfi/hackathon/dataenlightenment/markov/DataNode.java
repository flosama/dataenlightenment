package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

public class DataNode {
    
    private DataField field;
    private int count;

    public DataNode(DataField field, int count) {
        if (field == null)
            throw new IllegalArgumentException("No null value allowed");
        if (count < 0)
            throw new IllegalArgumentException("No count values below zero allowed");
        this.field = field;
        this.count = count;
    }

    public DataField getField() {
        return field;
    }

    public int getCount() {
        return count;
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
