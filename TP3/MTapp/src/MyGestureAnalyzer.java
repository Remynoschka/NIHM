import java.awt.geom.AffineTransform;

import mygeom.Point2;
import mygeom.Segment2;
import mygeom.Tuple2;
import mygeom.Vector2;
import event.ComponentMap;
import event.GestureAnalyzer;

public class MyGestureAnalyzer extends GestureAnalyzer {

	private Point2 oldPoint1;
	private Point2 oldPoint2;
	private Point2 oldMiddle;
	private Segment2 oldSeg;

	public MyGestureAnalyzer(ComponentMap cMap) {
		super(cMap);

	}

	/**
	 * Called when a new finger is in contact with the surface
	 * 
	 * @param p
	 *            Current position of the finger that touched the surface
	 */

	public void add(Point2 p) {
		if (blobQueue.getNbFingers() == 1)
			comp.click();
		else if (blobQueue.getNbFingers() == 2) {
			// A Completer: initialisations necessaires pour SRT 2 doigts
			comp.click();

		}
	}

	/**
	 * Called when a finger updated its position (finger moved)
	 * 
	 * @param p
	 *            current finger position
	 */

	public void update(Point2 p) {
		if (oldPoint1 == null) {
			oldPoint1 = new Point2(p.x, p.y);
		}

		if (blobQueue.getNbFingers() == 1) {
			// A completer: translation avec un doigt
			System.out.println("1finger");
			Vector2 t = new Vector2(p.x - oldPoint1.x, p.y - oldPoint1.y);
			this.comp.updatePosition(t, 0, 1);
			oldPoint1 = p;

		} else if (blobQueue.getNbFingers() == 2) {
			Point2 middle = null;
			Segment2 currentSeg;
			if (!oldPoint1.equals(p)) {
				if (oldPoint2 == null) {
					oldPoint2 = p;
				}
				currentSeg = new Segment2(oldPoint1, oldPoint2);
				middle = new Point2((p.x + oldPoint2.x) / 2,
						(p.y + oldPoint2.y) / 2);
				if (oldMiddle == null) {
					oldMiddle = middle;
				}
				if (oldSeg == null) {
					oldSeg = currentSeg;
				}
				// A completer: SRT 2 doigts
				// translate
				Vector2 t = new Vector2(middle.x - oldMiddle.x, middle.y
						- oldMiddle.y);
				// rotate
				AffineTransform tr = computeFrame(p,
						new Tuple2(currentSeg.init().x - currentSeg.end().x,
								currentSeg.init().y - currentSeg.end().y));
				double angle = Segment2.computeAngle(oldSeg, currentSeg);
				double scale = Segment2.computeScale(oldSeg, currentSeg);
				System.out.println(scale);
				// scale
				if (!java.lang.Double.isNaN(angle)) {
//					this.comp.updatePosition(t, 0, 1);
					this.comp.updatePosition(new Vector2(), angle, 1);
//					this.comp.updatePosition(new Vector2(), 0, scale);
				}

				oldPoint2 = p;
				oldMiddle = middle;
				oldSeg = currentSeg;
			} else {
				// inverse d'au dessus
			}
		}

	}

	/**
	 * Called when a finger is removed from the surface
	 * 
	 * @param p
	 *            finger position before being removed from the surface
	 */

	public void remove(Point2 p) {
		oldPoint1 = null;
		oldPoint2 = null;
		oldMiddle = null;
		oldSeg = null;
	}

}
