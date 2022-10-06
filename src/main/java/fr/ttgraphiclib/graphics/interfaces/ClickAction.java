package fr.ttgraphiclib.graphics.interfaces;

import fr.ttgraphiclib.graphics.events.NodeClickedEvent;

public interface ClickAction<T extends NodeClickedEvent> {

    void onClick(T event);

}
