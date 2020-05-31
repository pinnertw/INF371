package typo;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Box {
	abstract double getWidth() ;
	abstract double getAscent() ;
	abstract double getDescent() ;
	abstract double getStretchingCapacity() ;

	final static boolean debug = false;

// draw
	public final boolean draw(Graphics graph, double x, double y, double w) {
		if (debug) {
			graph.setColor(Color.red);
			graph.drawRect((int) x, (int) y, (int) w, (int) (getAscent() + getDescent()));
			graph.setColor(Color.black);
		}
	return doDraw(graph, x, y, w);
	}

// doDraw
	public abstract boolean doDraw(Graphics graph, double x, double y, double w);

// toString
	public String toString() {
		return String.format("[w=%g, a=%g, d=%g, sC=%g]", this.getWidth(), this.getAscent(), this.getDescent(), this.getStretchingCapacity());
	}
}
