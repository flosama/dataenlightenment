/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: generator
 * Author   : saynoom
 * Creation : 20.03.2015 19:41:03
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.gen;

import java.security.SecureRandom;
import java.util.Random;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;
import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;

/**
 * @author saynoom
 */
public class DataGenerator {

    // --- Fields --------------------------------------------------------------

    private final DataService dataService;

    private final Random random = new SecureRandom();

    // --- Constructors --------------------------------------------------------

    public DataGenerator(DataService dataService) {
        super();
        this.dataService = dataService;
    }

    // --- Basic operations ----------------------------------------------------

    public void generateData(Instructions instructions, int iterations) {
        int index = random.nextInt(instructions.count());
        DataField field = instructions.getField(index);
        for (int i = 0; field != null && i < iterations; i++) {
            // do the visit
            dataService.getData(field);
            field = getRandomField(instructions, field);
        }
    }

    private DataField getRandomField(Instructions instructions, DataField predecessor) {
        return instructions.getField(predecessor, random.nextDouble());
    }

}
