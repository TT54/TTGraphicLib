package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.events.NodeClickedEvent;
import fr.ttgraphiclib.graphics.events.NodeMoveEvent;
import fr.ttgraphiclib.graphics.events.listener.GraphicsListener;
import fr.ttgraphiclib.graphics.events.listener.UserListener;
import fr.ttgraphiclib.graphics.interfaces.ClickAction;
import fr.ttgraphiclib.graphics.interfaces.MoveAction;
import fr.ttgraphiclib.utils.TTGraphics;

public abstract class GraphicNode {


    private GraphicPanel panel;
    private double x;
    private double y;
    private double size = 1;
    private double speedX;
    private double speedY;
    private double accelerationX;
    private double accelerationY;

    private MoveAction<NodeMoveEvent> moveAction;
    private ClickAction<NodeClickedEvent> clickAction;


    public GraphicNode(GraphicPanel panel, double x, double y) {
        this.x = x;
        this.y = y;
        this.panel = panel;

        this.panel.addNode(this);
    }

    public GraphicNode(GraphicPanel panel, double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.panel = panel;

        this.panel.addNode(this);
    }

    public abstract void draw(TTGraphics g, int x, int y, int size);

    public abstract boolean isPointIn(double x, double y);

    public final void setX(double x) {
        this.x = x;
    }

    public final void setY(double y) {
        this.y = y;
    }

    public final void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public final void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public final void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }

    public final void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }

    public final void accelerate(double aX, double aY){
        this.accelerationX += aX;
        this.accelerationY += aY;
    }

    public final void moveSpeed(double speedX, double speedY){
        this.speedX += speedX;
        this.speedY += speedY;
    }


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

    public void onNodeClicked(NodeClickedEvent event){
        if(this.clickAction != null) {
            this.clickAction.onClick(event);
        }
    }

    public GraphicPanel getPanel() {
        return panel;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getAccelerationX() {
        return accelerationX;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setMoveAction(MoveAction<NodeMoveEvent> action) {
        this.moveAction = action;
    }

    public void setClickAction(ClickAction<NodeClickedEvent> action){
        this.clickAction = action;
        if(!UserListener.nodeWithClickEvent.contains(this))
            UserListener.nodeWithClickEvent.add(this);
    }
}
