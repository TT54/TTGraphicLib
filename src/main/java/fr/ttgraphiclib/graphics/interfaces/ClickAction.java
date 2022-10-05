package fr.ttgraphiclib.graphics.interfaces;

import fr.ttgraphiclib.graphics.events.NodeClickedEvent;
import fr.ttgraphiclib.graphics.events.NodeMoveEvent;

public interface ClickAction<T extends NodeClickedEvent> {

    void onMove(T event);

}
