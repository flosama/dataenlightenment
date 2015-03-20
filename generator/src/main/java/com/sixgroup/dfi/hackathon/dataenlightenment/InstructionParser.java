/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: generator
 * Author   : saynoom
 * Creation : 20.03.2015 19:26:49
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author saynoom
 */
public class InstructionParser {

    // --- Fields --------------------------------------------------------------

    // --- Constructors --------------------------------------------------------

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

    public Instructions parseInstructions(File definition) throws IOException {
        Instructions instructions = new Instructions();
        try (BufferedReader reader = new BufferedReader(new FileReader(definition))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Instruction instruction = parseInstruction(line);
                if (instruction != null)
                    instructions.insertInstruction(instruction);
            }
        }
        return instructions;
    }

    private Instruction parseInstruction(String line) {
        Instruction instruction = null;
        String[] parts = line.split("[-=:]");
        if (parts.length == 3) {
            DataField predecessor = parseDataField(parts[0]);
            DataField successor = parseDataField(parts[1]);
            int weight = parseWeight(parts[2]);
            instruction = new Instruction(predecessor, successor, weight);
        }
        return instruction;
    }

    private DataField parseDataField(String string) {
        return new DataField(string);
    }

    private int parseWeight(String value) {
        return Integer.parseInt(value);
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
