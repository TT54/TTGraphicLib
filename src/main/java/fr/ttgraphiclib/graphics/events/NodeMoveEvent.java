package fr.ttgraphiclib.graphics.events;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.nodes.GraphicNode;

public class NodeMoveEvent extends Event {

    private double nextPosX;
    private double nextPosY;

    private GraphicNode node;
    private GraphicPanel panel;

    public NodeMoveEvent(double nextPosX, double nextPosY, GraphicNode node, GraphicPanel panel) {
        super(GraphicManager.getFrame(), panel);
        this.nextPosX = nextPosX;
        this.nextPosY = nextPosY;
        this.node = node;
        this.panel = panel;
    }

    public double getNextPosX() {
        return nextPosX;
    }

    public void setNextPosX(double nextPosX) {
        this.nextPosX = nextPosX;
    }

    public double getNextPosY() {
        return nextPosY;
    }

    public void setNextPosY(double nextPosY) {
        this.nextPosY = nextPosY;
    }

    public GraphicNode getNode() {
        return node;
    }

    public void setNode(GraphicNode node) {
        this.node = node;
    }

    public GraphicPanel getPanel() {
        return panel;
    }

    public void setPanel(GraphicPanel panel) {
        this.panel = panel;
    }
}
