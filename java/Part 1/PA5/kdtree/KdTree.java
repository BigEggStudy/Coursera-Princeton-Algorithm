import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private Node root;
    private int N;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D point) {
            p = point;
        }
    }

    public KdTree() {
        root = null;
        N = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return N;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insert(root, p, true);
    }

    private Node insert(Node rootNode, Point2D newPoint, boolean useX) {
        if (rootNode == null) {
            N++;
            return new Node(newPoint);
        }

        int cmp = comparePoint(rootNode.p, newPoint, useX);
        if (useX) {
            if (cmp < 0)
                rootNode.lb = insert(rootNode.lb, newPoint, !useX);
            else if (cmp > 0)
                rootNode.rt = insert(rootNode.rt, newPoint, !useX);
        } else {
            if (cmp < 0)
                rootNode.lb = insert(rootNode.lb, newPoint, !useX);
            else if (cmp > 0)
                rootNode.rt = insert(rootNode.rt, newPoint, !useX);
        }
        return rootNode;
    }

    private int comparePoint(Point2D rootPoint, Point2D newPoint, boolean useX) {
        if (useX) {
            if (newPoint.x() < rootPoint.x()) return -1;
            else if (newPoint.x() > rootPoint.x()) return 1;
            else {
                if (newPoint.y() == rootPoint.y()) return 0;
                else return 1;
            }
        } else {
            if (newPoint.y() < rootPoint.y()) return -1;
            else if (newPoint.y() > rootPoint.y()) return 1;
            else {
                if (newPoint.x() == rootPoint.x()) return 0;
                else return 1;
            }
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return get(root, p, true) != null;
    }

    private Node get(Node rootNode, Point2D p, boolean useX) {
        if (rootNode == null) return null;

        int cmp = comparePoint(rootNode.p, p, useX);
        if (cmp < 0) return get(rootNode.lb, p, !useX);
        else if (cmp > 0) return get(rootNode.rt, p, !useX);
        else return rootNode;
    }

    public void draw() {
        drawPoint(root, true);
    }

    private void drawPoint(Node pointNode, boolean useX) {
        if (pointNode == null) return;

        StdDraw.setPenRadius();
        if (useX) {
            StdDraw.setPenColor(StdDraw.RED);
            //    StdDraw.line(pointNode.p.x(), pointNode.rect.ymin(), pointNode.p.x(), pointNode.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            //    StdDraw.line(pointNode.rect.xmin(), pointNode.p.y(), pointNode.rect.xmax(), pointNode.p.y());
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(pointNode.p.x(), pointNode.p.y());

        drawPoint(pointNode.lb, !useX);
        drawPoint(pointNode.rt, !useX);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        Stack<Point2D> result = new Stack<Point2D>();
        range(result, root, rect, true);
        return result;
    }

    private void range(Stack<Point2D> result, Node pointNode, RectHV rectHV, boolean useX) {
        if (pointNode == null) return;

        Point2D point = pointNode.p;
        if (rectHV.contains(point)) result.push(point);
        if (useX) {
            final double x = point.x();
            if (rectHV.xmin() < x) range(result, pointNode.lb, rectHV, !useX);
            if (rectHV.xmax() >= x) range(result, pointNode.rt, rectHV, !useX);
        } else {
            final double y = point.y();
            if (rectHV.ymin() < y) range(result, pointNode.lb, rectHV, !useX);
            if (rectHV.ymax() >= y) range(result, pointNode.rt, rectHV, !useX);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) return null;

        Nearest nearest = new Nearest(null, Double.POSITIVE_INFINITY);
        getNearest(root, p, true, nearest, new RectHV(0, 0, 1, 1));
        return nearest.point;
    }

    private void getNearest(Node pointNode, Point2D p, boolean useX, Nearest nearest, RectHV encompassingRect) {
        if (pointNode == null) return;

        double newDistance = p.distanceSquaredTo(pointNode.p);
        if (nearest.distance > newDistance) {
            nearest.point = pointNode.p;
            nearest.distance = newDistance;
        }

        if (pointNode.lb != null) {
            if (pointNode.lb.rect == null) {
                if (useX) {
                    pointNode.lb.rect = new RectHV(encompassingRect.xmin(), encompassingRect.ymin(), pointNode.p.x(), encompassingRect.ymax());
                } else {
                    pointNode.lb.rect = new RectHV(encompassingRect.xmin(), encompassingRect.ymin(), encompassingRect.xmax(), pointNode.p.y());
                }
            }
            getNearest(pointNode.lb, p, !useX, nearest, pointNode.lb.rect);
        }
        if (pointNode.rt != null) {
            if (pointNode.rt.rect == null) {
                if (useX) {
                    pointNode.rt.rect = new RectHV(pointNode.p.x(), encompassingRect.ymin(), encompassingRect.xmax(), encompassingRect.ymax());
                } else {
                    pointNode.rt.rect = new RectHV(encompassingRect.xmin(), pointNode.p.y(), encompassingRect.xmax(), encompassingRect.ymax());
                }
            }
            if (pointNode.rt.rect.distanceSquaredTo(p) < nearest.distance)
                getNearest(pointNode.rt, p, !useX, nearest, pointNode.rt.rect);
        }
    }

    private class Nearest {
        private Point2D point;
        private double distance;

        public Nearest(Point2D point2D, double distance) {
            this.point = point2D;
            this.distance = distance;
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree tree = new KdTree();
        assert tree.isEmpty();
        assert tree.size() == 0;

        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.5, 0.9));
        tree.insert(new Point2D(0.25, 0.4));
        tree.insert(new Point2D(0.75, 0.6));
        tree.insert(new Point2D(0.15, 0.25));
        tree.insert(new Point2D(0.35, 0.85));
        tree.insert(new Point2D(0.05, 0.75));
        assert !tree.isEmpty();
        assert tree.size() == 7;

        assert tree.contains(new Point2D(0.5, 0.5));
        assert tree.contains(new Point2D(0.25, 0.4));
        assert tree.contains(new Point2D(0.75, 0.6));
        assert tree.contains(new Point2D(0.15, 0.25));
        assert tree.contains(new Point2D(0.35, 0.85));
        assert tree.contains(new Point2D(0.05, 0.75));
        assert !tree.contains(new Point2D(0.25, 0.74));
        assert !tree.contains(new Point2D(0.24, 0.75));

        tree.draw();
    }
}