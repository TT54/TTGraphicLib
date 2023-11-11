package fr.ttgraphiclib.graphics.events;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.nodes.GraphicNode;
import fr.ttgraphiclib.thread.Frame;

public class NodeMouseReleasedEvent extends Event {

    private final GraphicNode node;
    private final double mouseX;
    private final double mouseY;

    public NodeMouseReleasedEvent(Frame frame, GraphicPanel panel, GraphicNode node, double mouseX, double mouseY) {
        super(frame, panel);
        this.node = node;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public GraphicNode getNode() {
        return node;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }
}
