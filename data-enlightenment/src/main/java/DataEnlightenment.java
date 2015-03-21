import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.sixgroup.dfi.hackathon.dataenlightenment.client.MainFrame;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instruction;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.InstructionGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.InstructionParser;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instructions;

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

    public static void main(String[] args) throws IOException {
        Instructions instructions = generateInstructions();
        MainFrame jarvis = new MainFrame(instructions);
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

}
