package typo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.font.TextLayout;


public class Glyph extends Box {
	final private static FontRenderContext frc
		= new FontRenderContext(null, false, false);
	final private Font font;
	final private char[] chars;
	final private Rectangle2D bounds;

// Constructeur
	public Glyph(Font font, char c) {
	this.font = font;
	this.chars = new char[1];
	this.chars[0] = c;
	TextLayout layout = new TextLayout("" + chars[0], font, frc);
	this.bounds = layout.getBounds();
	} 

	public double getStretchingCapacity() {
		return 0;
	}

	public double getAscent() {
		return - this.bounds.getY();
	}

	public double getDescent() {
		return this.bounds.getHeight() + this.bounds.getY();
	}

	public double getWidth() {
		return this.bounds.getWidth();
	}

	public String toString() {
		return String.format("Glyph(%s)[w=%g, a=%g, d=%g, sC=%g]",
			chars[0], this.getWidth(), this.getAscent(),
			this.getDescent(), this.getStretchingCapacity());
	}
// doDraw
	public boolean doDraw(Graphics graph, double x, double y, double w){
		graph.setFont(this.font);
		graph.drawChars(this.chars, 0, 1, (int) (x - this.bounds.getX()), (int) (y - this.bounds.getY()));
		return true;
	}
}

