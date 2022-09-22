package fr.ttgraphiclib.example;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.nodes.RectangleNode;
import fr.ttgraphiclib.thread.Frame;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        GraphicPanel panel = new GraphicPanel.DebugPanel().addPainting(graphic -> {
            graphic.drawString("COUCOCU", 200, 50);
            graphic.drawString("FPS : " + GraphicManager.getFrame().getFps(), 200, 100);

            if(time + 3*1000 < System.currentTimeMillis()){
                GraphicManager.getFrame().setMaxFPS(60);
            }

            return true;
        }, 1);

        RectangleNode node = new RectangleNode(panel, 0, 0);
        node.accelerate(1, 0);




        GraphicManager.enable(new Frame("Test"), panel);
    }

}
