package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.utils.TTGraphics;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RectangleNode extends GraphicNode {

    private double baseWidth = 50;
    private double baseHeight = 50;
    private Color color = Color.WHITE;

    public RectangleNode(GraphicPanel panel, double x, double y) {
        super(panel, x, y);
    }

    public RectangleNode(GraphicPanel panel, double x, double y, Color color) {
        super(panel, x, y);
        this.color = color;
    }

    public RectangleNode(GraphicPanel panel, double x, double y, double baseWidth, double baseHeight) {
        super(panel, x, y);
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public RectangleNode(GraphicPanel panel, double x, double y, double baseWidth, double baseHeight, Color color) {
        super(panel, x, y);
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.color = color;
    }

    @Override
    public void draw(TTGraphics g, int x, int y, int size) {
        Color ancient = g.getColor();
        g.setColor(color);
        g.fillRect(x, y, (int) (size * baseWidth), (int) (size * baseHeight));
        g.setColor(ancient);
    }

    @Override
    public boolean isPointIn(double x, double y) {
        Rectangle rectangle = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getBaseWidth(), (int) this.getBaseHeight());
        return rectangle.contains(x, y);
    }

    public double getBaseWidth() {
        return baseWidth;
    }

    public void setBaseWidth(double baseWidth) {
        this.baseWidth = baseWidth;
    }

    public double getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(double baseHeight) {
        this.baseHeight = baseHeight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
