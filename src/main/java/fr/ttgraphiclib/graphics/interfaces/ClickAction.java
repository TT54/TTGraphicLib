package fr.ttgraphiclib.graphics.interfaces;

import fr.ttgraphiclib.graphics.events.Event;

public interface ClickAction<T extends Event> {

    void onClick(T event);

}
