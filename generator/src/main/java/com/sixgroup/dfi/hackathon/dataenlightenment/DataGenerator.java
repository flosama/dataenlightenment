/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: generator
 * Author   : saynoom
 * Creation : 20.03.2015 19:41:03
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

import java.security.SecureRandom;
import java.util.Random;

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

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    // --- Access --------------------------------------------------------------

    // --- Examination ---------------------------------------------------------

    // --- Editing -------------------------------------------------------------

    // --- Removal -------------------------------------------------------------

    // --- Measurement ---------------------------------------------------------

    // --- Status report -------------------------------------------------------

    // --- Status setting ------------------------------------------------------

    // --- Cursor movement -----------------------------------------------------

    // --- Actions -------------------------------------------------------------

    // --- Basic operations ----------------------------------------------------

    public void generateData(Instructions instructions, int iterations) {
        int index = random.nextInt(instructions.count());
        DataField field = instructions.getField(index);
        for (int i = 0; i < iterations; i++) {
            // do the visit
            dataService.getData(field);
            field = getRandomField(instructions, field);
        }
    }

    private DataField getRandomField(Instructions instructions, DataField predecessor) {
        return instructions.getField(predecessor, random.nextDouble());
    }

    // --- Miscellaneous -------------------------------------------------------

    // --- Transformation ------------------------------------------------------

    // --- Observation ---------------------------------------------------------

    // --- Comparison ----------------------------------------------------------

    // --- Duplication ---------------------------------------------------------

    // --- Conversion ----------------------------------------------------------

    // --- Display -------------------------------------------------------------

    // --- Serialization -------------------------------------------------------

    // --- Initialization ------------------------------------------------------

    // --- Finalization --------------------------------------------------------

    // --- Private implementation ----------------------------------------------

    // --- Inner types ---------------------------------------------------------

}
