package fr.ttgraphiclib.graphics;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.events.GraphicRepaintEvent;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.interfaces.PaintAction;
import fr.ttgraphiclib.graphics.nodes.GraphicNode;
import fr.ttgraphiclib.utils.TTGraphics;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GraphicPanel extends JPanel {
    private final Map<Integer, List<PaintAction>> actions = new HashMap<>();

    private final List<GraphicNode> nodes = new ArrayList<>();


    private int topX = 0;
    private int topY = 0;

    private double zoom = 1;

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(graphics instanceof Graphics2D) {
            TTGraphics g = new TTGraphics((Graphics2D) graphics, topX, topY, zoom, this);


            GraphicRepaintEvent event = new GraphicRepaintEvent(GraphicManager.getFrame(), this, g, topX, topY);
            GraphicsListener.playPanelRepaintEvent(event);
            if (event.isCanceled())
                return;


            List<Integer> priorities = this.actions.keySet().stream().sorted().collect(Collectors.toList());
            for (int priority : priorities) {

                List<PaintAction> toRemove = new ArrayList<>();
                boolean removeNeeded = false;
                for (PaintAction action : this.actions.get(priority)) {
                    boolean shouldContinue = action.doAction(g);
                    if (!shouldContinue) {
                        removeNeeded = true;
                        toRemove.add(action);
                    }
                }

                if (removeNeeded) {
                    for (PaintAction action : toRemove) {
                        this.actions.get(priority).remove(action);
                    }
                }
            }


            this.drawNodes(g);
        }
    }

    private void drawNodes(TTGraphics g) {
        try {
            for (GraphicNode node : this.nodes) {
                node.draw(g, (int) node.getX(), (int) node.getY(), (int) node.getSize());
            }
        } catch (ConcurrentModificationException e){
            System.out.println("Concurrent Modification Exception");
        }
    }

    public void translatePanel(int x, int y) {
        this.topX += x;
        this.topY += y;
    }


    public GraphicPanel addPainting(PaintAction action, int priority) {
        List<PaintAction> list = this.actions.getOrDefault(priority, new ArrayList<>());
        list.add(action);
        this.actions.put(priority, list);

        return this;
    }

    public final void addNode(GraphicNode node) {
        this.nodes.add(node);
    }

    public List<GraphicNode> getNodes() {
        return this.nodes;
    }

    public int getTopX() {
        return topX;
    }

    public int getTopY() {
        return topY;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        if (zoom > 0)
            this.zoom = zoom;
    }

    public void addZoom(double zoom) {
        if (this.zoom + zoom > 0)
            this.zoom += zoom;
    }


    public static class DebugPanel extends GraphicPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillRect(0, 0, 90, 20);
            g.setColor(Color.BLACK);
            g.drawString("DEBUG MODE", 5, 15);
        }
    }
}
