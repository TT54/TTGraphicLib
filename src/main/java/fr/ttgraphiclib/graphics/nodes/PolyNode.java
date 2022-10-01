package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;
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
    public void move() {
        super.move();

        //TODO Voir pour faire bouger également les nodes internes
    }
}
