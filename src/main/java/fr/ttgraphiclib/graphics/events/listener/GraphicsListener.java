package fr.ttgraphiclib.graphics.events.listener;

import fr.ttgraphiclib.graphics.events.GraphicRepaintEvent;
import fr.ttgraphiclib.graphics.events.NodeMoveEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class GraphicsListener {

    public static List<GraphicsListener> listeners = new ArrayList<>();

    public static void registerListener(GraphicsListener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    public static void playPanelRepaintEvent(GraphicRepaintEvent event) {
        for (GraphicsListener listener : listeners) {
            listener.onPanelRepaint(event);
        }
    }

    public static void playNodeMoveEvent(NodeMoveEvent event) {
        for (GraphicsListener listener : listeners) {
            listener.onNodeMove(event);
        }
    }


    public void onPanelRepaint(GraphicRepaintEvent event) {
    }

    public void onNodeMove(NodeMoveEvent event) {
    }


}
