
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import mygeom.Point2;
import widget.OBB;


public class MyOBB extends OBB {

	
	public boolean contains(Point2 p) {
		AffineTransform tr=new AffineTransform();
        tr.scale(1.0/this.getWidth(),1.0/this.getHeight());
        tr.rotate(-this.getAngle());
        tr.translate(-this.getOrigin().x, -this.getOrigin().y);
        
		Point2D pt = new Point2D.Double();
		pt = tr.transform(new Point2D.Double(p.x, p.y), pt);
//		System.out.println(pt.getX()+"/"+pt.getY());
		if (pt.getX() > 0 && pt.getX() < 1 && pt.getY() > 0 && pt.getY() < 1){
			return true;
		}
		return false;

	}
	
	
}
