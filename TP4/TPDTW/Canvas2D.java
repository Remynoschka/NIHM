import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/**
 * Canvas2D.java
 * 
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 * @version
 */

public class Canvas2D extends Canvas implements MouseMotionListener,
		MouseListener {
	// Stroke of reference
	private Vector<Point> RStroke;

	// Stroke to test
	private Vector<Point> TStroke;

	private int shiftModifier = 17;

	private DTW dtw;
	private Matrix m;
	private int w = 200;

	Canvas2D() {
		RStroke = new Vector<Point>();
		TStroke = new Vector<Point>();
		dtw = new DTW(TStroke, RStroke, w);
		addMouseListener(this);
		addMouseMotionListener(this);

		setBackground(new Color(255, 255, 255));
	}

	public void paint(Graphics g) {
		g.drawString(
				"Drag avec le bouton gauche ou droit de la souris + Shift : cr�ation d'une courbe de r�f�rence",
				10, 15);
		g.drawString(
				"Drag avec le bouton gauche ou droit de la souris : cr�ation d'une courbe de test",
				10, 30);
		int r = 5;

		if (!RStroke.isEmpty()) {
			g.setColor(Color.black);
			for (int i = 1; i < RStroke.size(); i++) {
				g.drawLine(RStroke.elementAt(i - 1).x,
						RStroke.elementAt(i - 1).y, RStroke.elementAt(i).x,
						RStroke.elementAt(i).y);
				g.drawArc(RStroke.elementAt(i - 1).x - r,
						RStroke.elementAt(i - 1).y - r, 2 * r, 2 * r, 0, 360);
			}
			g.drawArc(RStroke.elementAt(RStroke.size() - 1).x - r,
					RStroke.elementAt(RStroke.size() - 1).y - r, 2 * r, 2 * r,
					0, 360);
		}

		if (!TStroke.isEmpty()) {
			g.setColor(Color.orange);
			for (int i = 1; i < TStroke.size(); i++) {
				g.drawLine(TStroke.elementAt(i - 1).x,
						TStroke.elementAt(i - 1).y, TStroke.elementAt(i).x,
						TStroke.elementAt(i).y);
				g.drawArc(TStroke.elementAt(i - 1).x - r,
						TStroke.elementAt(i - 1).y - r, 2 * r, 2 * r, 0, 360);
			}
			g.drawArc(TStroke.elementAt(TStroke.size() - 1).x - r,
					TStroke.elementAt(TStroke.size() - 1).y - r, 2 * r, 2 * r,
					0, 360);
		}

		// afficher mise en correspondance
		if (!TStroke.isEmpty() && !RStroke.isEmpty() && m != null) {
			g.setColor(Color.MAGENTA);
			// for (int i = 1; i < TStroke.size() && i < RStroke.size(); i++) {
			// for (int j = 1; j < RStroke.size() && j < TStroke.size(); j++) {
			// System.out.println("i="+i+"/"+"j="+j);
			// System.out.println(m.couple[i][j].x+"/"+m.couple[i][j].y);
			// g.drawLine(TStroke.get(m.couple[i][j].x).x,
			// TStroke.get(m.couple[i][j].x).y,
			// RStroke.get(m.couple[i][j].y).x,
			// RStroke.get(m.couple[i][j].y).y);
			// }
			// }

			// int oldX = m.nRows - 1;
			// int oldY = m.nCols-1;
			// int nextX = m.nRows - 1;
			// int nextY = m.nCols-1;
			//
			// while (nextX != 0 && nextY != 0) {
			// System.out.println(m.couple.length);
			// System.out.println(m.couple[0].length);
			// nextX = m.couple[oldX][oldY].x;
			// nextY = m.couple[oldX][oldY].y;
			// g.drawLine(TStroke.get(oldX).x, TStroke.get(oldX).y,
			// RStroke.get(oldX).x, RStroke.get(oldX).y);
			// oldX = nextX;
			// oldY = nextY;
			// }

			// APPAREILLAGE QUI MARCHE BITCH !!!!!!!!!!
			 for (int i = 0; i < dtw.getAppareillage().size(); i += 2) {
			 g.drawLine(dtw.getAppareillage().get(i).x, dtw
			 .getAppareillage().get(i).y,
			 dtw.getAppareillage().get(i + 1).x, dtw
			 .getAppareillage().get(i + 1).y);
			 }
		
		}

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		if ((e.getModifiers() == shiftModifier) || RStroke.isEmpty())
			RStroke.add(e.getPoint());
		else
			TStroke.add(e.getPoint());
		repaint();
		m = dtw.calculateDistanceMatrix();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if ((e.getModifiers() == shiftModifier) || RStroke.isEmpty())
			RStroke.clear();
		else
			TStroke.clear();
	}

	public void mouseReleased(MouseEvent e) {

	}
}
