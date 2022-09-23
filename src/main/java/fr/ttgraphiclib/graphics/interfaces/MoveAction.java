package fr.ttgraphiclib.graphics.interfaces;

import fr.ttgraphiclib.graphics.events.NodeMoveEvent;

public interface MoveAction<T extends NodeMoveEvent> {

    void onMove(T event);

}
