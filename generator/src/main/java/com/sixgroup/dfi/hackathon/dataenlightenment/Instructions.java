/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: generator
 * Author   : saynoom
 * Creation : 20.03.2015 19:39:47
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author saynoom
 */
public class Instructions {

    // --- Fields --------------------------------------------------------------

    private final Map<DataField, List<Instruction>> instructions = new HashMap<>();

    // --- Constructors --------------------------------------------------------

    // --- Properties ----------------------------------------------------------

    // --- Creation ------------------------------------------------------------

    // --- Addition ------------------------------------------------------------

    public void insertInstruction(DataField predecessor, DataField successor, int weight) {
        insertInstruction(new Instruction(predecessor, successor, weight));
    }

    public void insertInstruction(Instruction instruction) {
        DataField predecessor = instruction.getPredecessor();
        List<Instruction> instructions = this.instructions.get(predecessor);
        if (instructions == null)
            this.instructions.put(predecessor, instructions = new LinkedList<>());
        instructions.add(instruction);
    }

    // --- Access --------------------------------------------------------------

    public DataField getField(int index) {
        DataField field = null;
        Iterator<DataField> it = instructions.keySet().iterator();
        for (int i = 0; i <= index && it.hasNext(); i++)
            field = it.next();
        return field;
    }

    public DataField getField(DataField predecessor, double probability) {
        assert 0.0 <= probability && probability <= 1.0;
        List<Instruction> tuples = instructions.get(predecessor);
        if (tuples != null) {
            int total = calculateTotal(tuples);
            int position = (int) (total * probability);
            int count = 0;
            for (Instruction tuple : tuples) {
                count += tuple.getWeight();
                if (position <= count)
                    return tuple.getSuccessor();
            }
        }
        return null;
    }

    private int calculateTotal(List<Instruction> tuples) {
        int total = 0;
        for (Instruction tuple : tuples)
            total += tuple.getWeight();
        return total;
    }

    // --- Examination ---------------------------------------------------------

    // --- Editing -------------------------------------------------------------

    // --- Removal -------------------------------------------------------------

    // --- Measurement ---------------------------------------------------------

    public int count() {
        return instructions.size();
    }

    // --- Status report -------------------------------------------------------

    // --- Status setting ------------------------------------------------------

    // --- Cursor movement -----------------------------------------------------

    // --- Actions -------------------------------------------------------------

    // --- Basic operations ----------------------------------------------------

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
