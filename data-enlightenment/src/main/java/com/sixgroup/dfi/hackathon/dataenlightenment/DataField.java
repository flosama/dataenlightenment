/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: api
 * Author   : saynoom
 * Creation : 20.03.2015 19:38:28
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

/**
 * @author saynoom
 */
public class DataField implements Comparable<DataField> {

    // --- Fields --------------------------------------------------------------

    private final String key;
    private String name;

    // --- Constructors --------------------------------------------------------

    public DataField(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    // --- Properties ----------------------------------------------------------

    public String getKey() {
        return key;
    }

    public String getName() {
        return name != null ? name : key;
    }

    public void setName(String name) {
        this.name = name;
    }

    // --- Comparison ----------------------------------------------------------

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DataField && equals((DataField) obj);
    }

    public boolean equals(DataField other) {
        return other != null && this.key.equals(other.key);
    }

    @Override
    public int compareTo(DataField other) {
        if (other == null)
            return -1;
        return this.key.compareTo(other.key);
    }

    // --- Display -------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        if (name != null)
            sb.append(": ").append(name);
        return sb.toString();
    }

}
