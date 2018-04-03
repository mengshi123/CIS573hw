package edu.upenn.cis573.travelingsalesman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class Stroke {

    // the color and width for a stroke on the screen
    private static final int PAINT_COLOR = Color.YELLOW;
    private static final float PAINT_WIDTH = 10;

    private final Paint paint;
    private final ArrayList<Point> points;
    private boolean isValidStroke;
    private Point firstPoint;

    public Stroke() {
        paint = new Paint();
        paint.setColor(PAINT_COLOR);
        paint.setStrokeWidth(PAINT_WIDTH);
        points = new ArrayList<Point>();
        isValidStroke = false;
    }

    /**
     * Draws a stroke to the screen if it is valid
     */
    public void drawStroke(Canvas canvas) {
        if (isValidStroke && points.size() > 1) {
            for (int i = 0; i < points.size()-1; i++) {
                int x1 = points.get(i).x;
                int y1 = points.get(i).y;
                int x2 = points.get(i+1).x;
                int y2 = points.get(i+1).y;
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
        }
    }

    /**
     * Adds a point to the stroke
     */
    public void addPoint(Point point) {
        if (isValidStroke) {
            points.add(point);
        }
    }

    /**
     * Creates a new stroke and marks it valid if the
     *  touch point is within 30 of any of the other points
     */
    public void ifStartStrokeValid(Point[] mapPoints, Point startPoint) {
        Point validPoint = getClosestPoint(mapPoints, startPoint);
        if (validPoint != null) {
            points.add(validPoint);
            firstPoint = validPoint;
            isValidStroke = true;
        }
    }

    /**
     * Ends a new stroke the same as the method above.
     */
    public LineSegment ifEndStrokeValid(Point[] mapPoints, Point endPoint) {
        if (isValidStroke) {
            points.clear();
            Point validPoint = getClosestPoint(mapPoints, endPoint);
            if (validPoint != null) {
                //make sure first point and current point aren't the same
                if (firstPoint.x != validPoint.x && firstPoint.y != validPoint.y) {
                    return new LineSegment(firstPoint, validPoint);
                }
            }
        }
        isValidStroke = false;
        return null;
    }

    /**
     * Find the closest point on the map for a given point.
     */
    private Point getClosestPoint(Point[] mapPoints, Point newPoint) {
        for (int i = 0; i < mapPoints.length; i++) {
            if (ShortestPath.distance(newPoint, mapPoints[i]) < 30) {
                newPoint.x = mapPoints[i].x + 10;
                newPoint.y = mapPoints[i].y + 10;
                return newPoint;
            }
        }
        return null;
    }
}