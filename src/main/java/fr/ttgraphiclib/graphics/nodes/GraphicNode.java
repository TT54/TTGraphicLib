package fr.ttgraphiclib.graphics.nodes;

import fr.ttgraphiclib.graphics.GraphicPanel;

import java.awt.*;

public abstract class GraphicNode {


    private GraphicPanel panel;
    private double x;
    private double y;
    private double size = 1;
    private double speedX;
    private double speedY;
    private double accelerationX;
    private double accelerationY;


    public GraphicNode(GraphicPanel panel, double x, double y) {
        this.x = x;
        this.y = y;
        this.panel = panel;

        this.panel.addNode(this);
    }public GraphicNode(GraphicPanel panel, double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.panel = panel;

        this.panel.addNode(this);
    }

    public abstract void draw(Graphics g, int x, int y, int size);

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


    public final void move(){
        this.speedX += this.accelerationX;
        this.speedY += this.accelerationY;

        this.x += this.speedX;
        this.y += this.speedY;
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
}