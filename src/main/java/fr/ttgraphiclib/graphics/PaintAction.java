package fr.ttgraphiclib.graphics;

import java.awt.*;

public interface PaintAction{

    /**
     *
     * @param graphic
     * @return boolean used to indicate if this code should be executed during the next graphics paintings.
     */
    boolean doAction(Graphics graphic);

}
