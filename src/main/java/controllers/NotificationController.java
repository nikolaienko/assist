package controllers;

/**
 * Created by vlad on 12/22/14.
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.AccidentDao;
import dao.UserDao;
import logic.DistanceCalculator;
import logic.DistanceCalculatorFactory;
import models.*;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.cache.NinjaCache;
import ninja.params.Param;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class NotificationController {
    private static final Double RANGE = 1d;
    private static final int ACCISTANCE_TIME = 30;  // in minutes
    private static final int MILISECONDS_IN_MINUTE = 60000;

    @Inject
    NinjaCache ninjaCache;
    @Inject
    AccidentDao accidentDao;
    @Inject
    UserDao userDao;
    public Result receiveAssistance(@Param("lat") Double lat,
                                    @Param("lng") Double lng,
                                    Context context) {
        //if (accident!=null && validateAccident(accident)){
            if (accidentDao.createAccident(new Accident(Lists.newArrayList(1),lat,lng)))
                return Results.status(201);
        return Results.badRequest();

    }

    public Result checkAssistence(Coord dto) {
        DistanceCalculator distanceCalculator = DistanceCalculatorFactory.getInstance().getDistanceCalculator("Native");
        List<Coord> list = getNearestAccident(dto,
                accidentDao.getAllAccidentsWithStatus(false)
                        .stream()
                        .filter(a -> (System.currentTimeMillis() - a.accidentDate.getTime()) / MILISECONDS_IN_MINUTE < ACCISTANCE_TIME)
                        .map(a -> new Coord(a.lat,a.lng))
                        .collect(Collectors.toList())
                , distanceCalculator);
        return Results.json().render(list);
    }

    private List<Coord> getNearestAccident(Coord coord,List<Coord> coords, DistanceCalculator distanceCalculator) {
        return coords.stream().filter(a->distanceCalculator.calculate(coord,a) <RANGE).collect(Collectors.toList());
    }

    private boolean validateAccident(Accident accident) {
        return true;
    }

}
