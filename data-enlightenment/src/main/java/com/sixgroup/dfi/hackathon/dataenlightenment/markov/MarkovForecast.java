/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : pangody
 * Creation : 21.03.2015 12:21:44
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.markov;

import java.io.File;
import java.io.IOException;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;
import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.UsageGraph;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.DataGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.InstructionParser;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instructions;

/**
 * @author pangody
 */
public class MarkovForecast {

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

    public UsageGraph getForecastGraph(int forecastIterations) {
        try {
            MarkovChain markovChain = new MarkovChain();
            DataService dataService = new MarkovDataService(markovChain, 2);
            DataGenerator generator = new DataGenerator(dataService);
            InstructionParser parser = new InstructionParser();
            Instructions instructions;
            instructions = new Instructions(parser.parseInstructions(new File("src/main/resources/instructions.gen")));
            generator.generateData(instructions, 1000);

            UsageGraph forecastGraph = new UsageGraph();
            DataFieldTuple prefix = markovChain.getRandomPrefix();
            DataField[] prefixFields = prefix.getFields();
            int markovDegree = prefixFields.length;
            DataField predecessor = null;
            DataField successor = null;
            for (int i = 1; i < markovDegree; i++) {
                predecessor = prefixFields[i - 1];
                successor = prefixFields[i];
                forecastGraph.insert(predecessor, successor);
            }

            for (int j = 0; j < forecastIterations; j++) {
                DataField predictedField = markovChain.getNextField(prefix);
                if (predictedField != null) {
                    predecessor = successor;
                    successor = predictedField;
                    forecastGraph.insert(predecessor, successor);
                    for (int i = 1; i < markovDegree; i++) {
                        prefixFields[i - 1] = prefixFields[i];
                    }
                    prefixFields[markovDegree] = predictedField;
                }
            }
            return forecastGraph;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
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
