package de.planerio.developertest.transformer;

import de.planerio.developertest.model.League;
import de.planerio.developertest.model.LeagueRequest;
import de.planerio.developertest.model.LeagueResponse;

public class LeagueTransformer {

    public static LeagueResponse toResponse(League league){
        return new LeagueResponse(league.getId(), league.getName(), CountryTransformer.toResponse(league.getCountry()));
    }

    public static League toEntity(LeagueRequest leagueRequest){
        return new League(leagueRequest.getName(), CountryTransformer.toEntity(leagueRequest.getCountry()));
    }
}
