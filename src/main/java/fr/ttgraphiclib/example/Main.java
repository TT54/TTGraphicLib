package fr.ttgraphiclib.example;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.events.GraphicRepaintEvent;
import fr.ttgraphiclib.graphics.events.NodeMoveEvent;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.nodes.ImageNode;
import fr.ttgraphiclib.graphics.nodes.PolygonNode;
import fr.ttgraphiclib.graphics.nodes.RectangleNode;
import fr.ttgraphiclib.thread.Frame;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends GraphicsListener {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        GraphicPanel panel = new GraphicPanel.DebugPanel().addPainting(graphic -> {
            graphic.drawString("COUCOCU", 200, 50);
            graphic.drawString("FPS : " + GraphicManager.getFrame().getFps(), 200, 100);

            if (time + 3 * 1000 < System.currentTimeMillis()) {
                GraphicManager.getFrame().setMaxFPS(60);
            }

            return true;
        }, 1);

        RectangleNode node = new RectangleNode(panel, 0, 0);
        node.accelerate(1, 0);
        node.setMoveAction(event -> {
            if (event.getNextPosX() > 600 && event.getNode().getAccelerationX() > 0) {
                event.getNode().setAccelerationX(-1);
            } else if (event.getNextPosX() < 900 && event.getNode().getAccelerationX() < 0) {
                event.getNode().setAccelerationX(1);
            }
        });


        GraphicManager.enable(new Frame("Test"), panel);
        GraphicManager.registerGraphicListener(new Main());
        GraphicManager.registerMouseListener(new ExampleListener());


        RectangleNode rect = new RectangleNode(panel, 0, 50, 70, 50);
        rect.accelerate(1, 0);
        rect.setMoveAction(event -> {
            if (event.getNextPosX() > 400 && event.getNode().getAccelerationX() > 0) {
                event.getNode().setAccelerationX(-1);
            } else if (event.getNextPosX() < 600 && event.getNode().getAccelerationX() < 0) {
                event.getNode().setAccelerationX(1);
            }
        });

        RectangleNode r = new RectangleNode(panel, -50, -50, 100, 100);
        r.setColor(Color.BLACK);

        try {
            ImageNode imageNode = new ImageNode(panel, 25, 250, 50, 50, new URL("https://static.wikia.nocookie.net/hypixel-skyblock/images/e/e6/Site-logo.png/revision/latest?cb=20220430221340"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        PolygonNode polygonNode = new PolygonNode(panel, 200, 250, new double[]{-10d, 10d, 0}, new double[]{0, 0, 10});
    }

    @Override
    public void onPanelRepaint(GraphicRepaintEvent event) {
        event.getGraphics().drawString("Repainted", 250, 250);
        event.getGraphics().drawRect(250, 250, 50, 50);
    }

    @Override
    public void onNodeMove(NodeMoveEvent event) {
        if (event.getNode() instanceof RectangleNode && (event.getNode().getSpeedX() != 0 || event.getNode().getSpeedY() != 0)) {
            RectangleNode rectangleNode = (RectangleNode) event.getNode();
            if (rectangleNode.getX() < 400) {
                rectangleNode.setColor(Color.RED);
            } else {
                rectangleNode.setColor(Color.YELLOW);
            }
        }
    }
}
