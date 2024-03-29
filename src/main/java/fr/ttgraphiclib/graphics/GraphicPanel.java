package fr.ttgraphiclib.graphics;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.events.GraphicRepaintEvent;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.events.listener.UserListener;
import fr.ttgraphiclib.graphics.interfaces.PaintAction;
import fr.ttgraphiclib.graphics.nodes.GraphicNode;
import fr.ttgraphiclib.utils.TTGraphics;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class GraphicPanel extends JPanel {
    private final Map<Integer, List<PaintAction>> actions = new HashMap<>();

    private final List<GraphicNode> nodes = new ArrayList<>();

    private final Map<Integer, List<GraphicNode>> nodesPriority = new HashMap<>();
    private final List<Integer> priorities = new ArrayList<>();


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
            for (int priority : new ArrayList<>(priorities)) {

                List<PaintAction> toRemove = new ArrayList<>();
                boolean removeNeeded = false;
                for (PaintAction action : new ArrayList<>(this.actions.get(priority))) {
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

    public void setTopX(int topX) {
        this.topX = topX;
    }

    public void setTopY(int topY) {
        this.topY = topY;
    }

    private void drawNodes(TTGraphics g) {
        for (int priority : priorities) {
            for (GraphicNode node : new ArrayList<>(nodesPriority.getOrDefault(priority, new ArrayList<>()))) {
                if (node != null)
                    node.draw(g, (int) node.getX(), (int) node.getY(), (int) node.getSize());
            }
        }

        /*final List<GraphicNode> nodes = this.getNodes();
        try {
            for (GraphicNode node : nodes) {
                if(node != null)
                    node.draw(g, (int) node.getX(), (int) node.getY(), (int) node.getSize());
            }
        } catch (ConcurrentModificationException e){
            e.printStackTrace();
        }*/
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
        int priority = node.getPriority();
        if (!this.priorities.contains(priority)) {
            this.priorities.add(priority);
            this.priorities.sort(Comparator.comparingInt(value -> value));
        }
        List<GraphicNode> nodesList = this.nodesPriority.getOrDefault(priority, new ArrayList<>());
        nodesList.add(node);
        this.nodesPriority.put(priority, nodesList);
    }

    public List<GraphicNode> getNodes() {
        return new ArrayList<>(this.nodes);
    }

    public void removeNode(GraphicNode graphicNode) {
        this.nodes.remove(graphicNode);

        int priority = graphicNode.getPriority();
        nodesPriority.get(priority).remove(graphicNode);

        UserListener.nodeWithClickEvent.remove(graphicNode);
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

    public double[] getCoordinatesFromGraphic(int x, int y){
        int centerX = this.getWidth() / 2;
        int centerY = this.getHeight() / 2;
        return new double[] {((x - (double) centerX) / zoom) + topX, ((y - centerY) / zoom) + topY};
    }

    public int[] getGraphicFromCoordinates(double x, double y){
        int centerX = this.getWidth() / 2;
        int centerY = this.getHeight() / 2;
        return new int[] {(int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY};
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
