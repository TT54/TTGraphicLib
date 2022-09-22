package fr.ttgraphiclib.graphics;

import fr.ttgraphiclib.graphics.nodes.GraphicNode;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GraphicPanel extends JPanel {
    private final Map<Integer, List<PaintAction>> actions = new HashMap<>();

    private final List<GraphicNode> nodes = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Integer> priorities = this.actions.keySet().stream().sorted().collect(Collectors.toList());
        for(int priority : priorities){

            List<PaintAction> toRemove = new ArrayList<>();
            boolean removeNeeded = false;
            for(PaintAction action : this.actions.get(priority)){
                boolean shouldContinue = action.doAction(g);
                if(!shouldContinue) {
                    removeNeeded = true;
                    toRemove.add(action);
                }
            }

            if(removeNeeded) {
                for (PaintAction action : toRemove) {
                    this.actions.get(priority).remove(action);
                }
            }
        }


        this.drawNodes(g);
    }

    private void drawNodes(Graphics g) {
        for(GraphicNode node : this.nodes){
            node.draw(g, (int) node.getX(), (int) node.getY(), (int) node.getSize());
        }
    }


    public GraphicPanel addPainting(PaintAction action, int priority){
        List<PaintAction> list = this.actions.getOrDefault(priority, new ArrayList<>());
        list.add(action);
        this.actions.put(priority, list);

        return this;
    }

    public final void addNode(GraphicNode node){
        this.nodes.add(node);
    }

    public List<GraphicNode> getNodes(){
        return this.nodes;
    }



    public static class DebugPanel extends GraphicPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("DEBUG MODE", 15, 15);
            g.setColor(Color.RED);
            g.fillRect(150, 150, 100, 20);
        }
    }



}
