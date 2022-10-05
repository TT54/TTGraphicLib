package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.utils.TTGraphics;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RotatedImageNode extends ImageNode{

    private double rotateAngle;


    public RotatedImageNode(GraphicPanel panel, double x, double y, double width, double height, URL imageURL, double rotateAngle) {
        super(panel, x, y, width, height, imageURL);
        this.rotateAngle = rotateAngle;
    }

    public RotatedImageNode(GraphicPanel panel, double x, double y, double width, double height, File imageFile, double rotateAngle) {
        super(panel, x, y, width, height, imageFile);
        this.rotateAngle = rotateAngle;
    }

    public RotatedImageNode(GraphicPanel panel, double x, double y, URL imageURL, double rotateAngle) {
        super(panel, x, y, imageURL);
        this.rotateAngle = rotateAngle;
    }

    public RotatedImageNode(GraphicPanel panel, double x, double y, File imageFile, double rotateAngle) {
        super(panel, x, y, imageFile);
        this.rotateAngle = rotateAngle;
    }

    @Override
    public void draw(TTGraphics g, int x, int y, int size) {
        g.drawRotatedImage(this.image, x, y, (int) this.width, (int) this.height, this.rotateAngle);
    }
}
