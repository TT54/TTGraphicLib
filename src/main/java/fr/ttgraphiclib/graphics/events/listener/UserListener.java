package fr.ttgraphiclib.graphics.events.listener;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.events.NodeClickedEvent;
import fr.ttgraphiclib.graphics.nodes.GraphicNode;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserListener implements MouseListener, MouseWheelListener, KeyListener {

    private static List<UserListener> listeners = new ArrayList<>();

    public static List<GraphicNode> nodeWithClickEvent = new ArrayList<>();

    public static void registerListener(UserListener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }


    @Override
    public final void mouseClicked(MouseEvent e) {
        for (UserListener listener : listeners)
            listener.onMouseClicked(e);
    }

    @Override
    public final void mousePressed(MouseEvent e) {
        for (UserListener listener : listeners)
            listener.onMousePressed(e);

        for(GraphicNode node : nodeWithClickEvent){
            double[] coos = GraphicManager.getPanel().getCoordinatesFromGraphic(e.getX() - 9, e.getY() - 38);
            if(node.isPointIn(coos[0], coos[1])){
                NodeClickedEvent event = new NodeClickedEvent(GraphicManager.getFrame(), GraphicManager.getPanel(), node, e.getX(), e.getY());
                node.onNodeClicked(event);
                if(!event.isCanceled())
                    GraphicsListener.nodeClickedEvent(event);
            }
        }
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        for (UserListener listener : listeners)
            listener.onMouseReleased(e);
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
        for (UserListener listener : listeners)
            listener.onMouseEnteredWindow(e);
    }

    @Override
    public final void mouseExited(MouseEvent e) {
        for (UserListener listener : listeners)
            listener.onMouseExitedWindow(e);
    }

    @Override
    public final void mouseWheelMoved(MouseWheelEvent e) {
        for (UserListener listener : listeners) {
            listener.onScroll(e);
        }
    }

    @Override
    public final void keyTyped(KeyEvent e) {
        for (UserListener listener : listeners) {
            listener.onKeyTyped(e);
        }
    }


    @Override
    public final void keyPressed(KeyEvent e) {
        for (UserListener listener : listeners) {
            listener.onKeyPressed(e);
        }
    }

    @Override
    public final void keyReleased(KeyEvent e) {
        for (UserListener listener : listeners) {
            listener.onKeyReleased(e);
        }
    }


    public void onMouseEnteredWindow(MouseEvent event) {
    }

    public void onMouseExitedWindow(MouseEvent event) {
    }

    public void onMouseClicked(MouseEvent event) {
    }

    public void onMousePressed(MouseEvent event) {
    }

    public void onMouseReleased(MouseEvent event) {
    }

    public void onScroll(MouseWheelEvent e) {
    }

    public void onKeyTyped(KeyEvent e) {
    }

    public void onKeyPressed(KeyEvent e) {
    }

    public void onKeyReleased(KeyEvent e) {
    }
}
