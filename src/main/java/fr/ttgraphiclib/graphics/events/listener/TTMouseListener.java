package fr.ttgraphiclib.graphics.events.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TTMouseListener implements MouseListener {

    private static List<TTMouseListener> listeners = new ArrayList<>();

    public static void registerListener(TTMouseListener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }


    @Override
    public final void mouseClicked(MouseEvent e) {
        for (TTMouseListener listener : listeners)
            listener.onMouseClicked(e);
    }

    @Override
    public final void mousePressed(MouseEvent e) {
        for (TTMouseListener listener : listeners)
            listener.onMousePressed(e);
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        for (TTMouseListener listener : listeners)
            listener.onMouseReleased(e);
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
        for (TTMouseListener listener : listeners)
            listener.onMouseEnteredWindow(e);
    }

    @Override
    public final void mouseExited(MouseEvent e) {
        for (TTMouseListener listener : listeners)
            listener.onMouseExitedWindow(e);
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
}
