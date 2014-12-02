

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import event.ComponentMap;
import event.GestureAnalyzer;
import mygeom.DebugDraw;
import mygeom.Point2;
import mygeom.Segment2;
import mygeom.Tuple2;
import mygeom.Vector2;
import widget.MTComponent;
import widget.OBB;

public class MyGestureAnalyzer extends GestureAnalyzer {

	public MyGestureAnalyzer(ComponentMap cMap) {
		super(cMap);

	}

	/**
	 * Called when a new finger is in contact with the surface
	 * @param p Current position of the finger that touched the surface
	 */
	
	public void add(Point2 p) {
		if (blobQueue.getNbFingers() == 1) comp.click();
		if (blobQueue.getNbFingers() == 2) {
			// A Completer: initialisations necessaires pour SRT 2 doigts
		}
	}
	
	/**
	 * Called when a finger updated its position (finger moved)
	 * @param p current finger position
	 */
	
	public void update(Point2 p) {
		
		if (blobQueue.getNbFingers() == 1) {
			// A completer: translation avec un doigt
		}
		
		if (blobQueue.getNbFingers() == 2) {
			// A completer: SRT 2 doigts
				
		}		
		
	}
	
	/**
	 * Called when a finger is removed from the surface
	 * @param p finger position before being removed from the surface
	 */
	
	public void remove(Point2 p) {

	}
	
	
}
