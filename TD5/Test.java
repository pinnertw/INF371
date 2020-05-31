package typo;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.font.TextLayout;


public class Test{
// Test
	static void test1() {
		Font f = new Font("SansSerif", Font.PLAIN, 70);
		Glyph g = new Glyph(f, 'g');
		System.out.println(g);
	}
	static void test2() {
		Font f = new Font("SansSerif", Font.PLAIN, 70);
		Glyph g = new Glyph(f, 'g');
		System.out.println(g);
		new Page(g, 150, 150);
	}
	public static void main(String Args[]) {
		test2();
	}
}
