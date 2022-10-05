package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.utils.TTGraphics;

import java.awt.*;

public class PolygonNode extends GraphicNode {

    private double[] polygonRelativeX;
    private double[] polygonRelativeY;


    /**
     * @param panel            panel where the node is shown
     * @param x                coordinate x
     * @param y                coordinate y
     * @param polygonRelativeX array with the relative position x of each point of the polygon (relatively to x)
     * @param polygonRelativeY array with the relative position y of each point of the polygon (relatively to y)
     */
    public PolygonNode(GraphicPanel panel, double x, double y, double[] polygonRelativeX, double[] polygonRelativeY) {
        super(panel, x, y);
        this.polygonRelativeX = polygonRelativeX;
        this.polygonRelativeY = polygonRelativeY;
    }

    @Override
    public void draw(TTGraphics g, int x, int y, int size) {
        int[] xPoints = new int[this.polygonRelativeX.length];
        int[] yPoints = new int[this.polygonRelativeY.length];
        int length = xPoints.length;

        if (length == yPoints.length) {
            for (int i = 0; i < length; i++) {
                xPoints[i] = (int) (x + this.polygonRelativeX[i]);
                yPoints[i] = (int) (y + this.polygonRelativeY[i]);
            }
            g.drawPolygon(xPoints, yPoints, length);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isPointIn(double x, double y) {
        int[] xPoints = new int[this.polygonRelativeX.length];
        int[] yPoints = new int[this.polygonRelativeY.length];
        int length = xPoints.length;

        if (length == yPoints.length) {
            for (int i = 0; i < length; i++) {
                xPoints[i] = (int) (this.getX() + this.polygonRelativeX[i]);
                yPoints[i] = (int) (this.getY() + this.polygonRelativeY[i]);
            }
        } else {
            throw new IllegalArgumentException();
        }
        Polygon polygon = new Polygon(xPoints, yPoints, this.polygonRelativeX.length);
        return polygon.contains(x, y);
    }

    public double[] getPolygonRelativeX() {
        return polygonRelativeX;
    }

    public void setPolygonRelativeX(double[] polygonRelativeX) {
        this.polygonRelativeX = polygonRelativeX;
    }

    public double[] getPolygonRelativeY() {
        return polygonRelativeY;
    }

    public void setPolygonRelativeY(double[] polygonRelativeY) {
        this.polygonRelativeY = polygonRelativeY;
    }
}
