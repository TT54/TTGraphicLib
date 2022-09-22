package fr.ttgraphiclib;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.thread.Frame;

import javax.swing.*;

public class GraphicManager {

    private static Frame frame;
    private static GraphicPanel panel;

    public static void enable(Frame frame){
        enable(frame, new GraphicPanel());
    }

    public static void enable(Frame frame, GraphicPanel panel){
        GraphicManager.frame = frame;
        GraphicManager.panel = panel;
        frame.setPanel(panel);
        frame.init();
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static Frame getFrame() {
        return frame;
    }
}
