package edu.upenn.cis573.travelingsalesman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class Segments {

    //the color and width for a segment on the screen
    private static final int PAINT_COLOR = Color.RED;
    private static final float PAINT_WIDTH = 10;

    private final Paint paint;
    private ArrayList<LineSegment> segments;

    public Segments() {
        segments = new ArrayList<LineSegment>();
        paint = new Paint();
        paint.setColor(PAINT_COLOR);
        paint.setStrokeWidth(PAINT_WIDTH);
    }

    /**
     * Add a segment to the list
     */
    public void addSegment(LineSegment lineSegment) {
        segments.add(lineSegment);
    }

    /**
     * Remove the last segment from a list
     */
    public boolean removeLastSegment() {
        if (segments.size() < 1) {
            return false;
        }
        segments.remove(segments.size() - 1);
        return true;
    }

    /**
     * Clear all segments from the list
     */
    public void removeAllSegments() {
        segments.clear();
    }

    public int size() {
        return segments.size();
    }

    /**
     * Calculates and @returns the length of all segments
     */
    public double calculateLength() {
        double segmentLength = 0;
        for (LineSegment segment : segments) {
            Point p1 = segment.getStart();
            Point p2 = segment.getEnd();
            segmentLength += ShortestPath.distance(p1, p2);
        }
        return segmentLength;
    }

    /**
     * Draws each segment on the screen
     */
    public void draw(Canvas canvas) {
        for (int i = 0; i < segments.size(); i++) {
            LineSegment segment = segments.get(i);
            canvas.drawLine(segment.getStart().x, segment.getStart().y, segment.getEnd().x, segment.getEnd().y, paint);
        }
    }

    public ArrayList<LineSegment> getSegments()
    {
        return segments;
    }

    private int searchSegment(ArrayList<LineSegment> asegs, Point p) {
        for (int i = 0; i < asegs.size(); i++) {
            if (asegs.get(i).getStart().equals(p) || asegs.get(i).getEnd().equals(p)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isCircuit() {
        if (segments.isEmpty()) {
            return false;
        }
        boolean result = true;
        ArrayList<LineSegment> lineSegs = new ArrayList<>(getSegments());
        LineSegment currLine = new LineSegment(lineSegs.get(0).getStart(), lineSegs.get(0).getEnd());
        Point startPoint = new Point(currLine.getStart());
        Point frontPoint = new Point(currLine.getEnd());
        lineSegs.remove(0);
        while (lineSegs.size() > 1) {
            int indexNextLine = searchSegment(lineSegs, frontPoint);
            currLine = new LineSegment(lineSegs.get(indexNextLine).getStart(), lineSegs.get(indexNextLine).getEnd()) ;
            if (currLine.getStart().equals(startPoint) || currLine.getEnd().equals(startPoint)) {
                return false;
            }
            if (currLine.getStart().equals(frontPoint)) {
                frontPoint = new Point(currLine.getEnd());
            } else {
                frontPoint = new Point(currLine.getStart());
            }
            lineSegs.remove(indexNextLine);
        }
        if (!lineSegs.get(0).getStart().equals(startPoint) && !lineSegs.get(0).getEnd().equals(startPoint)) {
            result = false;
        }
        return result;
    }
}