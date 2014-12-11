import java.awt.Point;
import java.util.Vector;

/**
 * 
 * @author FRANCOIS Remy
 * 
 */
public class DTW {

	private Vector<Point> TStroke;
	private Vector<Point> RStroke;
	private int w;

	public DTW(Vector<Point> TStroke, Vector<Point> RStroke, int w) {
		this.TStroke = TStroke;
		this.RStroke = RStroke;
		this.w = w;
	}

	public Matrix calculateDistanceMatrix() {
		if (!TStroke.isEmpty() && !RStroke.isEmpty()) {
			Matrix d = new Matrix(TStroke.size(), RStroke.size());
			d.items[0][0] = Math.sqrt(Math.pow(
					TStroke.get(0).x - RStroke.get(0).x, 2)
					+ Math.pow(TStroke.get(0).y - RStroke.get(0).y, 2));
			;
			for (int i = 1; i < d.nRows; i++) {
				d.items[i][0] = d.items[i - 1][0] + distanceEuclidienne(i, 0);
			}
			for (int i = 1; i < d.nCols; i++) {
				d.items[0][i] = d.items[0][i - 1] + distanceEuclidienne(0, i);
			}
			for (int i = 1; i < d.nRows; i++) {
				for (int j = Math.max(1, i - w); j < Math.min(d.nCols, i + w); j++) {
//					 for (int j = 1; j < d.nCols; j++) {
					double min = Math.min(
							Math.min(d.items[i - 1][j], d.items[i][j - 1]),
							d.items[i - 1][j - 1]);
					if (min == d.items[i - 1][j]) {
						d.couple[i][j] = new Couple(i - 1, j);
					} else if (min == d.items[i][j - 1]) {
						d.couple[i][j] = new Couple(i, j - 1);
					} else if (min == d.items[i - 1][j - 1]) {
						d.couple[i][j] = new Couple(i - 1, j - 1);
					}
					d.items[i][j] = min + distanceEuclidienne(i, j);

				}
			}

			return d;
		} else {
			return new Matrix(0, 0);
		}
	}

	private double distanceEuclidienne(int i, int j) {
		return Math.sqrt(Math.pow(TStroke.get(i).x - RStroke.get(j).x, 2)
				+ Math.pow(TStroke.get(i).y - RStroke.get(j).y, 2));
	}

}
