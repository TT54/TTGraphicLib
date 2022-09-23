package fr.ttgraphiclib.graphics.events;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.thread.Frame;

public abstract class Event {

    private Frame frame;
    private GraphicPanel panel;
    private boolean canceled;

    public Event(Frame frame, GraphicPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public GraphicPanel getPanel() {
        return panel;
    }

    public void setPanel(GraphicPanel panel) {
        this.panel = panel;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
