package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;

import java.awt.*;

public class RectangleNode extends GraphicNode{

    private double baseWidth = 50;
    private double baseHeight = 50;

    public RectangleNode(GraphicPanel panel, double x, double y) {
        super(panel, x, y);
    }

    public RectangleNode(GraphicPanel panel, double x, double y, double baseWidth, double baseHeight) {
        super(panel, x, y);
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, (int) (size * baseWidth), (int) (size * baseHeight));
    }
}
