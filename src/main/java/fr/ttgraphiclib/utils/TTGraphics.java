package fr.ttgraphiclib.utils;

import fr.ttgraphiclib.graphics.GraphicPanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class TTGraphics {

    private final Graphics2D g;

    private final int topX;
    private final int topY;
    private final double zoom;
    private final GraphicPanel panel;

    private final int centerX;
    private final int centerY;

    public TTGraphics(Graphics2D g, int topX, int topY, double zoom, GraphicPanel panel) {
        this.g = g;
        this.topX = topX;
        this.topY = topY;
        this.zoom = zoom;
        this.panel = panel;

        this.centerX = this.panel.getWidth() / 2;
        this.centerY = this.panel.getHeight() / 2;
    }


    public void drawString(String text, int x, int y) {
        g.drawString(text, (int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY);
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height));
    }

/*Ancient top left coordinates version
    public void fillRect(int x, int y, int width, int height) {
        g.fillRect((int) (zoom * (x - topX - centerX)) + centerX, (int) (zoom * (y - topY - centerY)) + centerY, (int) (zoom * width), (int) (zoom * height));
    }*/

    public void fillRect(int x, int y, int width, int height) {
        g.fillRect((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height));
    }

    public boolean drawImage(Image image, int x, int y, ImageObserver observer) {
        if (observer != null && image != null) {
            return g.drawImage(image, (int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * image.getWidth(observer)), (int) (zoom * image.getHeight(observer)), observer);
        }
        return false;
    }

    public boolean drawImage(Image image, int x, int y, int width, int height, ImageObserver observer) {
        if (image != null) {
            return g.drawImage(image, (int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height), observer);
        }
        return false;
    }

    public boolean drawRotatedImage(Image image, int x, int y, int width, int height, ImageObserver observer, double rotateAngle) {
        if (image != null) {
            g.rotate(rotateAngle);
            boolean ret = g.drawImage(image, (int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height), observer);
            g.rotate( -rotateAngle);
            return ret;
        }
        return false;
    }

    public boolean drawImage(Image image, int x, int y, int width, int height, int imageTopX, int imageTopY, int imageWidth, int imageHeight, ImageObserver observer) {
        if (image != null) {
            int d2x = x + width;
            int d2y = y + height;
            int s2x = imageTopX + imageWidth;
            int s2y = imageTopY + imageHeight;

            return g.drawImage(image, (int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * (d2x - topX)) + centerX, (int) (zoom * (d2y - topY)) + centerY, imageTopX, imageTopY, s2x, s2y, observer);
        }
        return false;
    }

    public void drawRotatedImage(BufferedImage image, int x, int y, int width, int height, double rotation){
        if(image != null) {
            double rotationRequired = Math.toRadians(rotation);
            double locationX = image.getWidth() / 2d;
            double locationY = image.getHeight() / 2d;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

//Drawing the rotated image at the required drawing locations
            g.drawImage(op.filter(image, null), (int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height), null);
        }
    }


    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g.fillArc((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height), startAngle, arcAngle);
    }

    public void fillOval(int x, int y, int width, int height) {
        g.fillOval((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height));
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g.fillRoundRect((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height), arcWidth, arcHeight);
    }

    public void fillPolygon(int xPoints[], int yPoints[], int nPoints) {
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = (int) (zoom * (xPoints[i] - topX)) + centerX;
            yPoints[i] = (int) (zoom * (yPoints[i] - topY)) + centerY;
        }
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g.drawArc((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height), startAngle, arcAngle);
    }

    public void drawOval(int x, int y, int width, int height) {
        g.drawOval((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height));
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g.drawRoundRect((int) (zoom * (x - topX)) + centerX, (int) (zoom * (y - topY)) + centerY, (int) (zoom * width), (int) (zoom * height), arcWidth, arcHeight);
    }

    public void drawPolygon(int xPoints[], int yPoints[], int nPoints) {
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = (int) (zoom * (xPoints[i] - topX)) + centerX;
            yPoints[i] = (int) (zoom * (yPoints[i] - topY)) + centerY;
        }
        g.drawPolygon(xPoints, yPoints, nPoints);
    }

    public Color getColor() {
        return this.g.getColor();
    }

    public void setColor(Color color) {
        this.g.setColor(color);
    }

    public Graphics2D getGraphics() {
        return this.g;
    }
}
