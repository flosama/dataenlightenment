/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : pangody
 * Creation : 21.03.2015 12:21:44
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov.test;

import java.io.File;
import java.io.IOException;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.DataGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.InstructionParser;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instructions;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.MarkovChain;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.MarkovDataService;

/**
 * @author pangody
 */
public class MarkovTrainingTest {

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

    public static void main(String[] args) {
        try {
            MarkovChain markovChain = new MarkovChain();
            DataService dataService = new MarkovDataService(markovChain, 2);
            DataGenerator generator = new DataGenerator(dataService);
            InstructionParser parser = new InstructionParser();
            Instructions instructions;
            instructions = new Instructions(parser.parseInstructions(new File("src/main/resources/instructions.gen")));
            generator.generateData(instructions, 1000);
            
            // TODO
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
