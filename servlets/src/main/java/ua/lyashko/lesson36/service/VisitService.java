package ua.lyashko.lesson36.service;

import ua.lyashko.lesson36.model.Visit;

import java.util.*;

public final class VisitService {
    private static VisitService instance;

    private VisitService () {
    }

    public synchronized static VisitService getInstance () {
        if (instance == null) {
            instance = new VisitService ( );
        }
        return instance;
    }

    private final List<Visit> visits = new ArrayList<> ( 3 );


    public void add ( Visit visit ) {
        visits.add ( visit );
    }


    public List<Visit> getAll () {
        return visits;
    }
}
