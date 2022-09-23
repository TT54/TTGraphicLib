package fr.ttgraphiclib.utils;

import java.awt.*;

public class OffsetGraphics {

    private final Graphics g;

    private int topX;
    private int topY;

    public OffsetGraphics(Graphics g, int topX, int topY) {
        this.g = g;
        this.topX = topX;
        this.topY = topY;
    }


    public void drawString(String text, int x, int y) {
        g.drawString(text, x - topX, y - topY);
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x - topX, y - topY, width, height);
    }

    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x - topX, y - topY, width, height);
    }

    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g.fillArc(x - topX, y - topY, width, height, startAngle, arcAngle);
    }

    public void fillOval(int x, int y, int width, int height) {
        g.fillOval(x - topX, y - topY, width, height);
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g.fillRoundRect(x - topX, y - topY, width, height, arcWidth, arcHeight);
    }

    public void fillPolygon(int xPoints[], int yPoints[], int nPoints) {
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = xPoints[i] - topX;
            yPoints[i] = yPoints[i] - topY;
        }
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g.drawArc(x - topX, y - topY, width, height, startAngle, arcAngle);
    }

    public void drawOval(int x, int y, int width, int height) {
        g.drawOval(x - topX, y - topY, width, height);
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g.drawRoundRect(x - topX, y - topY, width, height, arcWidth, arcHeight);
    }

    public void drawPolygon(int xPoints[], int yPoints[], int nPoints) {
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = xPoints[i] - topX;
            yPoints[i] = yPoints[i] - topY;
        }
        g.drawPolygon(xPoints, yPoints, nPoints);
    }

    public Color getColor() {
        return this.g.getColor();
    }

    public void setColor(Color color) {
        this.g.setColor(color);
    }

    public Graphics getGraphics() {
        return this.g;
    }
}
