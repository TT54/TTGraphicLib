package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.utils.TTGraphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageNode extends GraphicNode {

    private Image image;
    private double width = 0;
    private double height = 0;

    public ImageNode(GraphicPanel panel, double x, double y, double width, double height, URL imageURL) {
        super(panel, x, y);

        this.width = width;
        this.height = height;

        try {
            this.image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageNode(GraphicPanel panel, double x, double y, double width, double height, File imageFile) {
        super(panel, x, y);

        this.width = width;
        this.height = height;

        try {
            this.image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageNode(GraphicPanel panel, double x, double y, URL imageURL) {
        super(panel, x, y);

        try {
            this.image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageNode(GraphicPanel panel, double x, double y, File imageFile) {
        super(panel, x, y);

        try {
            this.image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(TTGraphics g, int x, int y, int size) {
        if (this.width != 0 && this.height != 0) {
            g.drawImage(this.image, x, y, (int) this.width, (int) this.height, this.getPanel());
        } else {
            g.drawImage(this.image, x, y, this.getPanel());
        }
    }
}
