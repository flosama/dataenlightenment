/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: generator
 * Author   : saynoom
 * Creation : 20.03.2015 19:26:49
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.gen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;

/**
 * @author saynoom
 */
public class InstructionParser {

    // --- Constructors --------------------------------------------------------

    public InstructionParser() {
        super();
    }

    // --- Basic operations ----------------------------------------------------

    public List<Instruction> parseInstructions(File definition) throws IOException {
        try (Reader reader = new FileReader(definition)) {
            return parseInstructions(reader);
        }
    }

    public List<Instruction> parseInstructions(Reader reader) throws IOException {
        List<Instruction> instructions = new LinkedList<>();
        try (BufferedReader breader = new BufferedReader(reader)) {
            String line;
            while ((line = breader.readLine()) != null) {
                Instruction instruction = parseInstruction(line);
                if (instruction != null)
                    instructions.add(instruction);
            }
        }
        return instructions;
    }

    public Instruction parseInstruction(String line) {
        Instruction instruction = null;
        int index0 = line.indexOf("->");
        int index1 = line.indexOf('=');
        if (index0 >= 0 && index1 >= 0) {
            DataField predecessor = parseDataField(line.substring(0, index0).trim());
            DataField successor = parseDataField(line.substring(index0 + 2, index1).trim());
            int weight = parseWeight(line.substring(index1 + 1).trim());
            instruction = new Instruction(predecessor, successor, weight);
        }
        return instruction;
    }

    private DataField parseDataField(String key) {
        return new DataField(key, VDF.getName(key));
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
