/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: generator
 * Author   : saynoom
 * Creation : 20.03.2015 19:39:47
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author saynoom
 */
public class GenerationInstruction {

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

    public DataField getField(DataField predecessor, double probability) {
        assert 0.0 <= probability && probability <= 1.0;
        List<Instruction> tuples = instructions.get(predecessor);
        int total = calculateTotal(tuples);
        int position = (int) (total * probability);
        int count = 0;
        for (Instruction tuple : tuples) {
            count += tuple.getWeight();
            if (position <= count)
                return tuple.getSuccessor();
        }
        throw new IllegalStateException("Is the probability out-side the range.");
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

    public static class Instruction {

        private final DataField predecessor;
        private final DataField successor;
        private final int weight;

        public Instruction(DataField predecessor, DataField successor, int weight) {
            super();
            this.predecessor = predecessor;
            this.successor = successor;
            this.weight = weight;
        }

        public DataField getPredecessor() {
            return predecessor;
        }

        public DataField getSuccessor() {
            return successor;
        }

        public int getWeight() {
            return weight;
        }

    }

}
