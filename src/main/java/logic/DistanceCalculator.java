package logic;

import models.Coord;

/**
 * Created by vlad on 12/22/14.
 */
public interface DistanceCalculator {
    Double calculate(Coord from, Coord to);
}
