package edu.upenn.cis573.travelingsalesman;

import android.graphics.Point;

public class LineSegment {

    private Point start;
    private Point end;

    public LineSegment(Point s, Point e) {
        start = s;
        end = e;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
}