/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : pangody
 * Creation : 20.03.2015 21:43:21
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.vis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sixgroup.dfi.common.graphviz.DOT;
import com.sixgroup.dfi.common.graphviz.Image;
import com.sixgroup.dfi.common.graphviz.MediaType;
import com.sixgroup.dfi.common.graphviz.RenderingEngine;
import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.GraphDataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.UsageGraph;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.DataGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.InstructionParser;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instructions;

/**
 * @author pangody
 */
public class Test {

    public static void main(String[] args) {
        try {
            MediaType format = new MediaType("image", "png");
            DOT executable = new DOT();
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
            ExecutorService executor = new ThreadPoolExecutor(100, 200, 5000, TimeUnit.SECONDS, workQueue);
            RenderingEngine renderingEngine = new RenderingEngine(executor, executable, format);

            UsageGraph graph = new UsageGraph();
            DataService dataService = new GraphDataService(graph);
            DataGenerator generator = new DataGenerator(dataService);
            InstructionParser parser = new InstructionParser();
            Instructions instructions = new Instructions(parser.parseInstructions(new File(args[0])));
            generator.generateData(instructions, 100);

            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            try (DOTWriter dotWriter = new DOTWriter(new OutputStreamWriter(out))) {
                dotWriter.write(graph);
            }

            byte[] graphData = out.toByteArray();
            Future<Image> imageFuture = renderingEngine.renderImage(new ByteArrayInputStream(graphData));

            Image image = imageFuture.get();
            InputStream in = image.openInputStream();
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            int read;
            while ((read = in.read()) != -1)
                out2.write(read);

            JLabel jImage = new JLabel(new ImageIcon(out2.toByteArray()));
            JFrame jFrame = new JFrame("Graph Visu");
            jFrame.setSize(800, 600);
            jFrame.add(jImage);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        } catch (InterruptedException
                | IOException
                | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
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
