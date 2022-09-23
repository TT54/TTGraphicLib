package fr.ttgraphiclib;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.events.listener.TTMouseListener;
import fr.ttgraphiclib.thread.Frame;

public class GraphicManager {

    private static Frame frame;
    private static GraphicPanel panel;

    public static void enable(Frame frame){
        enable(frame, new GraphicPanel());
    }

    public static void enable(final Frame frame, final GraphicPanel panel) {
        GraphicManager.frame = frame;
        GraphicManager.panel = panel;
        new Thread(() -> {
            GraphicManager.frame.setPanel(panel);
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

    public static void registerMouseListener(TTMouseListener listener) {
        TTMouseListener.registerListener(listener);
    }
}
