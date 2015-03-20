/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 00:20:14
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author saynoom
 */
public class VdfDokTranslator {

    public static void main(String[] args) throws IOException {
        InstructionParser parser = new InstructionParser();
        for (String arg : args) {
            List<Instruction> instructions = parser.parseInstructions(new File(arg));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + ".txt")))) {
                for (Instruction instruction : instructions) {
                    writer.write(instruction.getPredecessor().getName());
                    writer.write(" -> ");
                    writer.write(instruction.getSuccessor().getName());
                    writer.write(" = ");
                    writer.write(Integer.toString(instruction.getWeight()));
                    writer.write('\n');
                }
            }
        }
    }

}
