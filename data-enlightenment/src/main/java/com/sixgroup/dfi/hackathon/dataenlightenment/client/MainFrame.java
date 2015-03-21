/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 05:37:40
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
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
import com.sixgroup.dfi.hackathon.dataenlightenment.DataService;
import com.sixgroup.dfi.hackathon.dataenlightenment.UsageGraph;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.DataGenerator;
import com.sixgroup.dfi.hackathon.dataenlightenment.gen.Instructions;
import com.sixgroup.dfi.hackathon.dataenlightenment.vis.DOTWriter;
import com.sixgroup.dfi.hackathon.dataenlightenment.vis.GraphDataService;

/**
 * @author saynoom
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = -1711963739199399507L;

    // --- Fields --------------------------------------------------------------

    final static String CONTENTPANEL = "Content Card";
    final static String BUTTONPANEL = "Button Card";

    private final Instructions instructions;

    private JPanel rootPanel;
    private JPanel contentCard;
    private JPanel buttonCard;
    private JTabbedPane tabsPanel;

    private JPanel graphvizPanel;
    private JPanel markovPanel;

    private JButton generateButton;

    private JButton generate;
    private JButton exit;

    private Action generateAction;
    private Action exitAction;

    // --- Constructors --------------------------------------------------------

    public MainFrame(Instructions instructions) {
        super();
        this.instructions = instructions;
        initSelf();
        initActions();
        initComponents();
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
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {

        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        contentCard = new JPanel();
        buttonCard = new JPanel();

        tabsPanel = new JTabbedPane();

        markovPanel = new JPanel();
        graphvizPanel = new JPanel();
        initButtons();

        tabsPanel.addTab("Forecast", markovPanel);
        tabsPanel.addTab("Graphical Representation", graphvizPanel);
        contentCard.add(tabsPanel);
        
        rootPanel.add(contentCard, BorderLayout.CENTER);
        rootPanel.add(buttonCard, BorderLayout.SOUTH);

        this.add(rootPanel);
    }

    private void initButtons() {
        generateButton = new JButton(generateAction);
        exit = new JButton(exitAction);

        buttonCard.add(generateButton);
        buttonCard.add(exit);

    }

    private void initActions() {

        generateAction = new AbstractAction("Generate") {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }};
            
        buttonCard = new JPanel();
        exitAction = new AbstractAction("Exit") {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        };

    }

    protected void generateGraphics() {
        try {
            MediaType format = new MediaType("image", "png");
            DOT executable = new DOT("neato");
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
            ExecutorService executor = new ThreadPoolExecutor(100, 200, 5000, TimeUnit.SECONDS, workQueue);
            RenderingEngine renderingEngine = new RenderingEngine(executor, executable, format);

            UsageGraph graph = new UsageGraph();
            DataService dataService = new GraphDataService(graph);
            DataGenerator generator = new DataGenerator(dataService);
            generator.generateData(instructions, 100);

            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            try (DOTWriter dotWriter = new DOTWriter(new OutputStreamWriter(out))) {
                dotWriter.write(graph);
            }

            byte[] graphData = out.toByteArray();
            Future<Image> imageFuture = renderingEngine.renderImage(new ByteArrayInputStream(graphData));

            Image image = imageFuture.get();
            try (InputStream in = image.openInputStream()) {
                ByteArrayOutputStream out2 = new ByteArrayOutputStream();
                int read;
                while ((read = in.read()) != -1)
                    out2.write(read);
                JLabel jImage = new JLabel(new ImageIcon(out2.toByteArray()));
                graphvizPanel.add(jImage);
                jImage.setVisible(true);
                graphvizPanel.setVisible(true);
            } catch (IOException e) {
                // TODO not generated, change it anyway
                e.printStackTrace();
            }
        } catch (InterruptedException
                | IOException
                | ExecutionException e) {

            e.printStackTrace();
        }
    }

    // --- Finalization --------------------------------------------------------

    // --- Private implementation ----------------------------------------------

    // --- Inner types ---------------------------------------------------------

}
