/**
 * Created by jianming.xiao on 10/19/14.
 */
public class KdTree {
    private Node root;
    private int N;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D point, RectHV rectHV) {
            p = point;
            rect = rectHV;
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
        root = insert(root, p, true, new RectHV(0, 0, 1, 1));
    }

    public Node insert(Node rootNode, Point2D newPoint, boolean useX, RectHV rectHV) {
        if (rootNode == null) {
            N++;
            return new Node(newPoint, rectHV);
        }

        int cmp = comparePoint(rootNode.p, newPoint, useX);
        if (useX) {
            if (cmp < 0)
                rootNode.lb = insert(rootNode.lb, newPoint, !useX,
                        new RectHV(rectHV.xmin(), rectHV.ymin(), rootNode.p.x(), rectHV.ymax()));
            else if (cmp > 0)
                rootNode.rt = insert(rootNode.rt, newPoint, !useX,
                        new RectHV(rootNode.p.x(), rectHV.ymin(), rectHV.xmax(), rectHV.ymax()));
        } else {
            if (cmp < 0)
                rootNode.lb = insert(rootNode.lb, newPoint, !useX,
                        new RectHV(rectHV.xmin(), rectHV.ymin(), rectHV.xmax(), rootNode.p.y()));
            else if (cmp > 0)
                rootNode.rt = insert(rootNode.rt, newPoint, !useX,
                        new RectHV(rectHV.xmin(), rootNode.p.y(), rectHV.xmax(), rectHV.ymax()));
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

    public void drawPoint(Node pointNode, boolean useX) {
        if (pointNode == null) return;

        StdDraw.setPenRadius();
        if (useX) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(pointNode.p.x(), pointNode.rect.ymin(), pointNode.p.x(), pointNode.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(pointNode.rect.xmin(), pointNode.p.y(), pointNode.rect.xmax(), pointNode.p.y());
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(pointNode.p.x(), pointNode.p.y());

        drawPoint(pointNode.lb, !useX);
        drawPoint(pointNode.rt, !useX);
    }
//
//    public Iterable<Point2D> range(RectHV rect) {
//    }
//
//    public Point2D nearest(Point2D p) {
//    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree tree = new KdTree();
        assert tree.isEmpty();
        assert tree.size() == 0;

        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.25, 0.4));
        tree.insert(new Point2D(0.75, 0.6));
        tree.insert(new Point2D(0.15, 0.25));
        tree.insert(new Point2D(0.35, 0.85));
        tree.insert(new Point2D(0.05, 0.75));
        assert !tree.isEmpty();
        assert tree.size() == 6;

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