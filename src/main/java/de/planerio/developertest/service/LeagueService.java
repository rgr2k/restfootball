package de.planerio.developertest.service;

import com.google.common.base.Strings;
import de.planerio.developertest.exception.ResourceExistsException;
import de.planerio.developertest.model.Country;
import de.planerio.developertest.model.League;
import de.planerio.developertest.model.LeagueCreate;
import de.planerio.developertest.model.LeagueUpdate;
import de.planerio.developertest.repository.LeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static de.planerio.developertest.exception.Constants.THERE_IS_LEAGUE;

@Service
public class LeagueService {

    private static final Logger log = LoggerFactory.getLogger(LeagueService.class);
    private final LeagueRepository leagueRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public League save(LeagueCreate leagueRequest){
        existsLeague(leagueRequest.getCountry().getName());
        return leagueRepository.save(buildLeague(leagueRequest));
    }

    public Optional<League> find(long leagueId){
        return leagueRepository.findById(leagueId);
    }

    public Optional<Iterable<League>> findAll(){
        final Iterable<League> leagues = leagueRepository.findAll();
        return leagues.iterator().hasNext() ? Optional.of(leagues) : Optional.empty();
    }

    public void delete(long leagueId){
        leagueRepository.deleteById(leagueId);
    }

    public void update(League league, LeagueUpdate leagueUpdate){
        existsLeague(leagueUpdate.getCountry().getName());
        validate(league, leagueUpdate);
        leagueRepository.save(league);
    }

    public Optional<League> findLeagueByCountryName(String countryName){
       return leagueRepository.findLeagueByCountryName(countryName);
    }

    private League buildLeague(LeagueCreate leagueRequest){
        String countryName = leagueRequest.getCountry().getName();
        String countryLanguage = leagueRequest.getCountry().getLanguage();
        Country country = new Country(countryName, countryLanguage);
        return new League(leagueRequest.getName(), country);
    }

    private void validate(League league, LeagueUpdate leagueUpdate) {
        if(!Strings.isNullOrEmpty(leagueUpdate.getName())){
            league.setName(leagueUpdate.getName());
        }
        if(!Strings.isNullOrEmpty(leagueUpdate.getCountry().getLanguage())){
            league.getCountry().setLanguage(leagueUpdate.getCountry().getLanguage());
        }
        if(!Strings.isNullOrEmpty(leagueUpdate.getCountry().getName())){
            league.getCountry().setName(leagueUpdate.getCountry().getName());
        }
    }

    private void existsLeague(String countryName){
        Optional<League> league =
                this.findLeagueByCountryName(countryName);
        if(league.isPresent()){
            throw new ResourceExistsException(String.format(THERE_IS_LEAGUE, countryName));
        }
    }
}
