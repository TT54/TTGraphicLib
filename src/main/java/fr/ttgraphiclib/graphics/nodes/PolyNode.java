package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.events.NodeClickedEvent;
import fr.ttgraphiclib.utils.TTGraphics;

public class PolyNode extends GraphicNode {

    private final GraphicNode[] graphicNodes;

    public PolyNode(GraphicPanel panel, double x, double y, GraphicNode... graphicNodes) {
        super(panel, x, y);

        this.graphicNodes = graphicNodes;
        for (GraphicNode graphicNode : graphicNodes) {
            panel.removeNode(graphicNode);
        }
    }

    @Override
    public void draw(TTGraphics g, int x, int y, int size) {
        for (GraphicNode node : graphicNodes) {
            node.draw(g, (int) (x + node.getX()), (int) (y + node.getY()), size);
        }
    }

    @Override
    public boolean isPointIn(double x, double y) {
        for(GraphicNode node : this.graphicNodes){
            if(node.isPointIn(x - this.getX(), y - this.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onNodeClicked(NodeClickedEvent event) {
        super.onNodeClicked(event);
        double[] coos = GraphicManager.getPanel().getCoordinatesFromGraphic((int) (event.getMouseX() - 9), (int) (event.getMouseY() - 38));
        for(GraphicNode node : this.graphicNodes){
            if(node.isPointIn(coos[0] - this.getX(), coos[1] - this.getY())) {
                node.onNodeClicked(event);
            }
        }
    }

    @Override
    public void move() {
        super.move();

        //TODO Voir pour faire bouger également les nodes internes
    }
}
