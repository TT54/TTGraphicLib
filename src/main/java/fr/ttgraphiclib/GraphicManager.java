package fr.ttgraphiclib;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.events.listener.UserListener;
import fr.ttgraphiclib.thread.Frame;

public class GraphicManager {

    private static Frame frame;
    private static GraphicPanel panel;

    private static int maxFPS = 30;
    private static int maxMPS = 30;

    public static void enable(Frame frame) {
        enable(frame, new GraphicPanel());
    }

    public static void enable(final Frame frame, final GraphicPanel panel) {
        GraphicManager.frame = frame;
        GraphicManager.panel = panel;
        new Thread(() -> {
            GraphicManager.frame.setPanel(panel);
            GraphicManager.frame.setMaxMPS(maxMPS);
            GraphicManager.frame.setMaxFPS(maxFPS);
            GraphicManager.frame.init();
        }).start();
    }

    public static GraphicPanel getPanel() {
        return panel;
    }

    public static Frame getFrame() {
        return frame;
    }

    public static void registerGraphicListener(GraphicsListener listener) {
        GraphicsListener.registerListener(listener);
    }

    public static void registerMouseListener(UserListener listener) {
        UserListener.registerListener(listener);
    }

    public static void freezeNodes(boolean freeze) {
        frame.setNodesEnabled(freeze);
    }

    public static boolean areNodesFrozen() {
        return frame.areNodesEnabled();
    }

    public static void setMaxFPS(int maxFPS) {
        GraphicManager.maxFPS = maxFPS;
        if (frame != null) {
            frame.setMaxFPS(maxFPS);
        }
    }

    public static void setMaxMovePerSecond(int maxMPS) {
        GraphicManager.maxMPS = maxMPS;
        if (frame != null) {
            frame.setMaxMPS(maxMPS);
        }
    }
}
