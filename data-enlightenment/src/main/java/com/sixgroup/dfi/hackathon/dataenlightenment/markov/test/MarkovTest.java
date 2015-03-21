/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: markov
 * Author   : fmahler
 * Creation : 21.03.2015 03:32:58
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov.test;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.DataFieldTuple;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.MarkovChain;

/**
 * @author fmahler
 */
public class MarkovTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        MarkovChain chain = new MarkovChain();
        DataField prefix1 = new DataField("1", "prefix");
        DataField prefix2 = new DataField("2", "prefix");
        DataField prefix3 = new DataField("3", "prefix");
        DataField prefix4 = new DataField("4", "prefix");

        DataFieldTuple prefix = new DataFieldTuple(new DataField[] { prefix1, prefix2, prefix3, prefix4 });

        DataField suffix1 = new DataField("56", "suffix1");
        DataField suffix2 = new DataField("78", "suffix2");

        for (int i = 0; i < 70; ++i) {
            chain.increment(prefix, suffix1);
        }

        for (int i = 0; i < 30; ++i) {
            chain.increment(prefix, suffix2);
        }

        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < 100; ++i) {
            DataField suffix = chain.getNextField(prefix);
            if (suffix != null && suffix.equals(suffix1)) {
                ++count1;
            }
            else {
                ++count2;
            }
        }
        
        System.out.println("Suffix 1 chosen (should be near 70): " + count1);
        System.out.println("Suffix 2 chosen (should be near 30): " + count2);
    }

}
