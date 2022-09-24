package fr.ttgraphiclib.graphics.interfaces;

import fr.ttgraphiclib.utils.TTGraphics;

public interface PaintAction{

    /**
     * @param graphic
     * @return boolean used to indicate if this code should be executed during the next graphics paintings.
     */
    boolean doAction(TTGraphics graphic);

}
