package fr.ttgraphiclib.example;

import fr.ttgraphiclib.GraphicManager;
import fr.ttgraphiclib.graphics.events.listener.UserListener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ExampleListener extends UserListener {


    @Override
    public void onScroll(MouseWheelEvent e) {
        if (e.getPreciseWheelRotation() > 0) {
            GraphicManager.getPanel().addZoom(-.05d);
        } else {
            GraphicManager.getPanel().addZoom(.05d);
        }
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                GraphicManager.getPanel().translatePanel(5, 0);
                break;
            case KeyEvent.VK_LEFT:
                GraphicManager.getPanel().translatePanel(-5, 0);
                break;
            case KeyEvent.VK_UP:
                GraphicManager.getPanel().translatePanel(0, -5);
                break;
            case KeyEvent.VK_DOWN:
                GraphicManager.getPanel().translatePanel(0, 5);
                break;
            case KeyEvent.VK_SPACE:
                GraphicManager.freezeNodes(!GraphicManager.areNodesFrozen());
                break;
        }
    }
}
