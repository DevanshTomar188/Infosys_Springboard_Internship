package com.CricketInformer.services;

import com.CricketInformer.entities.Match;

import java.util.List;
import java.util.Map;

public interface MatchService
{
    //get all matches
    List<Match> getAllMatches();

    //get live matches

    List<Match> getLiveMatches();

    List<List<String>> getPointTable();

}
