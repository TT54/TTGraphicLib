package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.events.NodeMoveEvent;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.interfaces.Movable;

public abstract class MovableNode extends GraphicNode implements Movable {


    public MovableNode(GraphicPanel panel, double x, double y) {
        super(panel, x, y);
    }

    public MovableNode(GraphicPanel panel, double x, double y, double size) {
        super(panel, x, y, size);
    }

    @Override
    public void move() {
        this.speedX += this.accelerationX;
        this.speedY += this.accelerationY;


        double simX = this.x + this.speedX;
        double simY = this.y + this.speedY;
        NodeMoveEvent event = new NodeMoveEvent(simX, simY, this, this.panel);

        if (this.moveAction != null)
            this.moveAction.onMove(event);
        GraphicsListener.playNodeMoveEvent(event);

        if (!event.isCanceled()) {
            this.x = event.getNextPosX();
            this.y = event.getNextPosY();
        } else {
            this.x += this.speedX;
            this.y += this.speedY;
        }
    }

}
