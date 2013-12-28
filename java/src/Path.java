package net.nolasaint.towerdefense;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import net.nolasaint.towerdefense.exceptions.NotOnPathException;

/**
 * This class represents a Path which Monsters follow. Path behaves like a
 * LinkedList in that its line segments are stored as nodes, and these nodes
 * point to the next line segment.
 *
 * @author Evan Bailey
 * @version 1.1
 */
public final class Path {

    private static enum Direction {NORTH, EAST, SOUTH, WEST}

    @SuppressWarnings("unused")
    private final Dimension bounds;
    private PathNode head, tail;

    /**
     * Constructs a new Path constrained by the specified Dimension.
     *
     * @param   bounds  - the Dimensions by which this Path is constrained
     */
    public Path(Dimension bounds) {
       this.bounds = bounds;
       head = new PathNode(null, null, null);

       generate();

    }

    private class PathNode {
        private Direction direction;
        private Line2D.Double segment;
        private PathNode next;

        public PathNode(Line2D.Double segment, Direction direction,
                PathNode next) {
            this.segment = segment;
            this.direction = direction;
            this.next = next;

        }

        public Direction getDirection() {
            return direction;

        }

        public void setDirection(Direction direction) {
            this.direction = direction;

        }

        public Line2D.Double getSegment() {
            return segment;

        }

        @SuppressWarnings("unused")
        public void setSegment(Line2D.Double segment) {
            this.segment = segment;

        }

        public PathNode getNext() {
            return next;

        }

        public void setNext(PathNode next) {
            this.next = next;

        }

    }

    private PathNode getNodeContaining(Point p) {
        boolean contained = false;
        PathNode curNode = head;

        while (!contained && curNode != null) {
            curNode = curNode.getNext();

            if (null != curNode && curNode.getSegment().ptSegDist(p) == 0) {
                contained = true;

            }

        }

        if (null != curNode) {
            return curNode;

        } else {
            return null;

        }

    }

    private void generate() {
        tail = new PathNode(new Line2D.Double(400, 300, 600, 300),
                Direction.EAST, null);
        PathNode fourth = new PathNode(new Line2D.Double(400, 150, 400, 300),
                Direction.SOUTH, tail);
        PathNode third = new PathNode(new Line2D.Double(200, 150, 400, 150),
                Direction.EAST, fourth);
        PathNode second = new PathNode(new Line2D.Double(200, 450, 200, 150),
                Direction.NORTH, third);
        PathNode first = new PathNode(new Line2D.Double(0, 450, 200, 450),
                Direction.EAST, second);

        head.setNext(first);

    }

    /**
     * Returns whether the specified Point is contained in this Path.
     *
     * @param   p   - the Point to check
     * @return  true if this Path contains the specified Point
     */
    public boolean contains(Point p) {
        return null != getNodeContaining(p);

    }

    /**
     * Returns this Path's entry Point.
     *
     * @return  this Path's entry Point
     */
    public Point getEntryPoint() {
        Point2D entryPoint2D = head.getNext().getSegment().getP1();
        Point entryPoint = new Point((int) entryPoint2D.getX(),
                (int) entryPoint2D.getY());

        return entryPoint;

    }

    /**
     * Returns the next Point along this Path, if there is one.
     *
     * @param   p   - the current point
     * @return  null if p is the final point, otherwise the next Point along
     *          this Path
     * @throws  NotOnPathException if this Path does not contain p
     */
    public Point getNextPoint(Point p) throws NotOnPathException {
        PathNode node = getNodeContaining(p);

        if (null == node) {
            throw new NotOnPathException("Path does not contain (" + p.getX()
                    + ", " + p.getY() + ").");

        }

        // Check that point isn't endpoint of the segment
        if (((Point2D) p).equals(node.getSegment().getP2())) {
            // Make sure we aren't at end of path
            if (null != node.getNext()) {
                node = node.getNext();

            }

        }

        if (node.getDirection() == Direction.NORTH) {
            return new Point((int) p.getX(), (int) (p.getY() - 1));

        } else if (node.getDirection() == Direction.EAST) {
            return new Point((int) (p.getX() + 1), (int) p.getY());

        } else if (node.getDirection() == Direction.SOUTH) {
            return new Point((int) p.getX(), (int) (p.getY() + 1));

        } else if (node.getDirection() == Direction.WEST) {
            return new Point((int) (p.getX() - 1), (int) p.getY());

        }

        return null;

    }

    /**
     * Paints this Path using the provided Graphics object.
     *
     * @param   g   - the Graphics with which to paint this Path
     */
    public void draw(Graphics g) {
        PathNode curNode = head;
        Line2D.Double line;

        g.setColor(Color.RED);
        while (curNode != null) {
            if (null != curNode.getSegment()) {
                line = curNode.getSegment();

                g.drawLine((int) line.getX1(), (int) line.getY1(),
                        (int) line.getX2(), (int) line.getY2());

            }

            curNode = curNode.getNext();

        }

    }

}
