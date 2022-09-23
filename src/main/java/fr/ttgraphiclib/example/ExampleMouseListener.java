package fr.ttgraphiclib.example;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.events.listener.TTMouseListener;

import java.awt.event.MouseEvent;

public class ExampleMouseListener extends TTMouseListener {

    @Override
    public void onMouseClicked(MouseEvent event) {
        GraphicManager.getPanel().translatePanel(5, 0);
        System.out.println(GraphicManager.getPanel().getTopX());
    }
}
