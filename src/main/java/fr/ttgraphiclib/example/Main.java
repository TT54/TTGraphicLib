package fr.ttgraphiclib.example;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.events.GraphicRepaintEvent;
import fr.ttgraphiclib.graphics.events.NodeMoveEvent;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.nodes.RectangleNode;
import fr.ttgraphiclib.thread.Frame;

import java.awt.*;

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


        GraphicManager.enable(new Frame("Test"), panel);
        GraphicManager.registerGraphicListener(new Main());
        GraphicManager.registerMouseListener(new ExampleMouseListener());


        RectangleNode rect = new RectangleNode(panel, 0, 50);
        rect.accelerate(1, 0);
        rect.setMoveAction(event -> {
            if (event.getNextPosX() > 400 && event.getNode().getAccelerationX() > 0) {
                event.getNode().setAccelerationX(-1);
            } else if (event.getNextPosX() < 600 && event.getNode().getAccelerationX() < 0) {
                event.getNode().setAccelerationX(1);
            }
        });
    }

    @Override
    public void onPanelRepaint(GraphicRepaintEvent event) {
        event.getGraphics().drawString("Repainted", 250, 250);
    }

    @Override
    public void onNodeMove(NodeMoveEvent event) {
        if (event.getNode() instanceof RectangleNode) {
            RectangleNode rectangleNode = (RectangleNode) event.getNode();
            if (rectangleNode.getX() < 400) {
                rectangleNode.setColor(Color.RED);
            } else {
                rectangleNode.setColor(Color.WHITE);
            }
        }
    }
}
