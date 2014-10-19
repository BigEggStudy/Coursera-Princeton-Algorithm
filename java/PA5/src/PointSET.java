/**
 * Created by jianming.xiao on 10/16/14.
 */
public class PointSET {
    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (!points.contains(p))
            points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }
        StdDraw.setPenRadius();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> result = new SET<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point))
                result.add(point);
        }
        return result;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;

        Point2D nearestPoint = null;
        double distance = Double.NEGATIVE_INFINITY;
        for (Point2D point : points) {
            if (nearestPoint == null) {
                nearestPoint = point;
                distance = point.distanceSquaredTo(p);
            } else {
                double newDistance = point.distanceSquaredTo(p);
                if (newDistance < distance) {
                    nearestPoint = point;
                    distance = newDistance;
                }
            }
        }
        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET set = new PointSET();
        assert set.isEmpty();
        assert set.size() == 0;

        set.insert(new Point2D(0.5, 0.5));
        set.insert(new Point2D(0.25, 0.5));
        set.insert(new Point2D(0.75, 0.5));
        set.insert(new Point2D(0.25, 0.25));
        set.insert(new Point2D(0.25, 0.75));
        assert !set.isEmpty();
        assert set.size() == 5;

        assert set.contains(new Point2D(0.5, 0.5));
        assert set.contains(new Point2D(0.25, 0.5));
        assert set.contains(new Point2D(0.75, 0.5));
        assert set.contains(new Point2D(0.25, 0.25));
        assert set.contains(new Point2D(0.25, 0.75));
        assert !set.contains(new Point2D(0.25, 0.74));
        assert !set.contains(new Point2D(0.24, 0.75));

        set.draw();
        Iterable<Point2D> range = set.range(new RectHV(0.49, 0.49, 0.51, 0.51));
        Point2D nearest = set.nearest(new Point2D(0.49, 0.49));
        assert nearest.x() == 0.5;
        assert nearest.y() == 0.5;
    }
}
