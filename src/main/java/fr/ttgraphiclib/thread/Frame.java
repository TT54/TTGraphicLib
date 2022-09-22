package fr.ttgraphiclib.thread;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.graphics.nodes.GraphicNode;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final String title;
    private final int width;
    private final int height;
    private final boolean resizable;

    private int maxFPS = 30;
    private int fps = 0;

    private GraphicPanel panel;


    private long lastFrame = 0;

    public Frame(String title, int width, int height, boolean resizable) throws HeadlessException {
        this.title = title;
        this.width = width;
        this.height = height;
        this.resizable = resizable;

        this.setTitle(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(this.width, this.height);
        this.setLocationRelativeTo(null);
        this.setResizable(resizable);
    }

    public Frame(String title, int width, int height) throws HeadlessException {
        this(title, width, height, true);
    }

    public Frame(String title) throws HeadlessException {
        this(title, 720, 480, true);
    }
    
    
    public void init(){
        if(this.panel != null){
            this.setContentPane(panel);
            this.setVisible(true);
            this.drawContent();
        }
    }

    public int getMaxFPS() {
        return maxFPS;
    }

    public void setMaxFPS(int maxFPS) {
        this.maxFPS = maxFPS;
    }

    public int getFps() {
        return fps;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(GraphicPanel panel) {
        this.panel = panel;
    }

    private void drawContent() {
        while(true){
            panel.getNodes().forEach(GraphicNode::move);
            this.panel.repaint();
            try{
                Thread.sleep(Math.max(0, 1000 / this.maxFPS - (System.currentTimeMillis() - lastFrame)));
                fps += (int) (1000 / (System.currentTimeMillis() - lastFrame));
                fps /= 2;
                lastFrame = System.currentTimeMillis();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
