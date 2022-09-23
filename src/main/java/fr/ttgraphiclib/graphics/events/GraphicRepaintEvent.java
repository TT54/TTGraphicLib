package fr.ttgraphiclib.graphics.events;

import fr.ttgraphiclib.graphics.GraphicPanel;
import fr.ttgraphiclib.thread.Frame;
import fr.ttgraphiclib.utils.OffsetGraphics;

public class GraphicRepaintEvent extends Event {

    private final OffsetGraphics graphics;
    private final int topX;
    private final int topY;

    public GraphicRepaintEvent(final Frame frame, final GraphicPanel panel, final OffsetGraphics graphics, final int topX, final int topY) {
        super(frame, panel);
        this.graphics = graphics;
        this.topX = topX;
        this.topY = topY;
    }

    public OffsetGraphics getGraphics() {
        return graphics;
    }

    public int getTopX() {
        return topX;
    }

    public int getTopY() {
        return topY;
    }
}
