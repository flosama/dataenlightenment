import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.UsageGraph;
import com.sixgroup.dfi.hackathon.dataenlightenment.client.MainFrame;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.DataGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instruction;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.InstructionGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.InstructionParser;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instructions;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.MarkovChain;

/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 05:38:17
 *------------------------------------------------------------------------------
 */

/**
 * @author saynoom
 */
public class DataEnlightenment {

    private static final int NUMBER_OF_INSTRUCTIONS = 400;
    private static final int MAX_WEIGHT = 40;
    private static final int MARKOV_DEGREE = 2;
    private static final int FORECAST_ITERATIONS = 4;

    public static void main(String[] args) throws IOException {
        Instructions instructions = generateInstructions();

        UsageGraph usageGraph = new UsageGraph();

        MarkovChain markovChain = new MarkovChain();
        DataService dataService = learn(instructions, usageGraph, markovChain, MARKOV_DEGREE);

        MainFrame jarvis = new MainFrame(dataService, FORECAST_ITERATIONS);
        jarvis.setVisible(true);
    }

    private static Instructions generateInstructions() throws IOException {
        InstructionGenerator generator = new InstructionGenerator();
        byte[] data;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                Writer writer = new OutputStreamWriter(out)) {
            generator.generateInstructions(NUMBER_OF_INSTRUCTIONS, MAX_WEIGHT, writer);
            writer.close();
            data = out.toByteArray();
        }

        InstructionParser parser = new InstructionParser();
        List<Instruction> instructionList;
        try (ByteArrayInputStream in = new ByteArrayInputStream(data);
                Reader reader = new InputStreamReader(in)) {
            instructionList = parser.parseInstructions(reader);
        }
        return new Instructions(instructionList);
    }

    private static DataService learn(Instructions instructions, UsageGraph graph, MarkovChain markovChain, int markovDegree) {
        DataService dataService = new DataService(graph, markovChain, markovDegree);
        DataGenerator generator = new DataGenerator(dataService);
        generator.generateData(instructions, 100);
        return dataService;
    }

}
