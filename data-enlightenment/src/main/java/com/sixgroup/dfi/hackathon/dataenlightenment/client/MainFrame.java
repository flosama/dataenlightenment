/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 05:37:40
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sixgroup.dfi.common.graphviz.DOT;
import com.sixgroup.dfi.common.graphviz.Image;
import com.sixgroup.dfi.common.graphviz.MediaType;
import com.sixgroup.dfi.common.graphviz.RenderingEngine;
import com.sixgroup.dfi.hackathon.dataenlightenment.DataField;
import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.UsageGraph;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.DataGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instructions;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.DataFieldTuple;
import com.sixgroup.dfi.hackathon.dataenlightenment.markov.MarkovChain;
import com.sixgroup.dfi.hackathon.dataenlightenment.vis.DOTWriter;

/**
 * @author saynoom
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = -1711963739199399507L;

    // --- Fields --------------------------------------------------------------

    final Instructions instructions;
    private final int forecastIterations;
    private final int markovDegree;

    private RenderingEngine renderingEngine;

    private JPanel graphvizPanel;
    private JPanel markovPanel;

    private Action generateAction;
    private Action exitAction;

    // --- Constructors --------------------------------------------------------

    public MainFrame(Instructions instructions, int forecastIterations, int markovDegree) {
        super();
        this.instructions = instructions;
        this.forecastIterations = forecastIterations;
        this.markovDegree = markovDegree;

        initSelf();
        initActions();
        initComponents();

        MediaType format = new MediaType("image", "png");
        DOT executable = new DOT("neato");
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ExecutorService executor = new ThreadPoolExecutor(100, 200, 5000, TimeUnit.SECONDS, workQueue);
        this.renderingEngine = new RenderingEngine(executor, executable, format);
    }

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

    private void initSelf() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setLocation(300, 100);
        this.setMinimumSize(new Dimension(600, 400));
        this.setTitle("Data Enlightenment");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        JTabbedPane contentCard = new JTabbedPane();
        graphvizPanel = new JPanel();
        contentCard.addTab("Analysis", graphvizPanel);
        markovPanel = new JPanel();
        contentCard.addTab("Forecast", markovPanel);
        rootPanel.add(contentCard, BorderLayout.CENTER);

        JPanel buttonCard = new JPanel();
        rootPanel.add(buttonCard, BorderLayout.SOUTH);
        initButtons(buttonCard);

        this.add(rootPanel);
    }

    private void initButtons(JPanel buttonCard) {
        JButton generateButton = new JButton(generateAction);
        JButton exit = new JButton(exitAction);

        buttonCard.add(generateButton);
        buttonCard.add(exit);

    }

    private void initActions() {

        generateAction = new AbstractAction("Generate") {

            private static final long serialVersionUID = -8507010632269506221L;

            @Override
            public void actionPerformed(ActionEvent e) {
                DataService dataService = learn(instructions);
                generateUsageGraphics(dataService.getUsageGraph());
                generateForecastGraphics(dataService.getMarkovChain());
            }
        };

        exitAction = new AbstractAction("Exit") {

            /**
             * 
             */
            private static final long serialVersionUID = 1032171808150985393L;

            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.dispose();
            }

        };

    }

    DataService learn(Instructions instructions) {
        UsageGraph usageGraph = new UsageGraph();
        MarkovChain markovChain = new MarkovChain();

        DataService dataService = new DataService(usageGraph, markovChain, markovDegree);
        DataGenerator generator = new DataGenerator(dataService);
        generator.generateData(instructions, 100);
        return dataService;
    }

    void generateUsageGraphics(UsageGraph usageGraph) {
        try {
            ImageIcon image = renderGraph(usageGraph);
            graphvizPanel.removeAll();
            graphvizPanel.add(new JLabel(image));
            graphvizPanel.repaint();
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new IllegalStateException("Could not generate image.", e);
        }
    }

    void generateForecastGraphics(MarkovChain markovChain) {
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
                prefixFields[markovDegree - 1] = predictedField;
            }
        }

        try {
            ImageIcon image = renderGraph(forecastGraph);
            markovPanel.removeAll();
            markovPanel.add(new JLabel(image));
            markovPanel.repaint();
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new IllegalStateException("Could not generate image.", e);
        }
    }

    private ImageIcon renderGraph(UsageGraph graph) throws IOException, InterruptedException, ExecutionException {
        byte[] graphData = generateDOTFile(graph);
        Future<Image> imageFuture = renderingEngine.renderImage(new ByteArrayInputStream(graphData));
        ImageIcon image = toAWTImage(imageFuture.get());
        return image;
    }

    private byte[] generateDOTFile(UsageGraph graph) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
                DOTWriter dotWriter = new DOTWriter(new OutputStreamWriter(out))) {
            dotWriter.write(graph);
            dotWriter.close();
            return out.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Could not gneerate DOT.", e);
        }
    }

    private ImageIcon toAWTImage(Image image) throws IOException {
        try (InputStream in = image.openInputStream()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read;
            while ((read = in.read()) != -1)
                out.write(read);
            return new ImageIcon(out.toByteArray());
        }
    }

    // --- Finalization --------------------------------------------------------

    // --- Private implementation ----------------------------------------------

    // --- Inner types ---------------------------------------------------------

}
