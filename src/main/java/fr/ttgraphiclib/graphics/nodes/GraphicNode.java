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


    protected GraphicPanel panel;
    protected double x;
    protected double y;
    protected double size = 1;
    protected double speedX;
    protected double speedY;
    protected double accelerationX;
    protected double accelerationY;

    protected MoveAction<NodeMoveEvent> moveAction;
    protected ClickAction<NodeClickedEvent> clickAction;


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
