package de.planerio.developertest.service;

import com.google.common.base.Strings;
import de.planerio.developertest.exception.LeagueNotFoundException;
import de.planerio.developertest.exception.ResourceExistsException;
import de.planerio.developertest.model.*;
import de.planerio.developertest.repository.CountryRepository;
import de.planerio.developertest.repository.LeagueRepository;
import de.planerio.developertest.transformer.LeagueTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.planerio.developertest.exception.Constants.*;
import static de.planerio.developertest.exception.Constants.THERE_IS_LEAGUE;

@Service
public class LeagueService {

    private static final Logger log = LoggerFactory.getLogger(LeagueService.class);
    private final LeagueRepository leagueRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository, CountryRepository countryRepository) {
        this.leagueRepository = leagueRepository;
        this.countryRepository = countryRepository;
    }

    public LeagueResponse save(LeagueRequest leagueRequest){
        existsLeague(leagueRequest.getCountry().getName());

        final Optional<Country> country =
                countryRepository.findCountryByNameAndLanguage(leagueRequest.getCountry().getName(), leagueRequest.getCountry().getLanguage());

        League league = LeagueTransformer.toEntity(leagueRequest, country);
        return LeagueTransformer.toResponse(leagueRepository.save(league));
    }

    public LeagueResponse find(long leagueId){
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new LeagueNotFoundException(LEAGUE_NOT_FOUND));
        return LeagueTransformer.toResponse(league);
    }

    public List<LeagueResponse> findAll(String language){
        if(Strings.isNullOrEmpty(language)){
            return this.findAll();
        }
        return findLeagueByLanguage(language);
    }

    public List<LeagueResponse> findAll(){
        List<LeagueResponse> leagueResponses =
                leagueRepository.findAll().stream()
                        .map(LeagueTransformer::toResponse).collect(Collectors.toList());
        if(leagueResponses.isEmpty()){
            throw new LeagueNotFoundException(LEAGUE_NOT_FOUND);
        }
        return leagueResponses;
    }

    public List<LeagueResponse> findLeagueByLanguage(String language){
        List<LeagueResponse> leagueResponses =
                leagueRepository.findAllByCountryLanguage(language).stream()
                        .map(LeagueTransformer::toResponse).collect(Collectors.toList());
        if(leagueResponses.isEmpty()){
            throw new LeagueNotFoundException(LEAGUE_NOT_FOUND);
        }
        return leagueResponses;
    }

    public void delete(long leagueId){
        try {
            leagueRepository.deleteById(leagueId);
        }catch (EmptyResultDataAccessException ex){
            throw new LeagueNotFoundException(LEAGUE_NOT_FOUND);
        }
    }

    public void update(LeagueUpdateRequest leagueUpdate, long leagueId){
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new LeagueNotFoundException(LEAGUE_NOT_FOUND));
        validate(league, leagueUpdate);
        leagueRepository.save(league);
    }

    private void validate(League league, LeagueUpdateRequest leagueUpdate) {
        if(!Strings.isNullOrEmpty(leagueUpdate.getName())){
            league.setName(leagueUpdate.getName());
        }
    }

    private void existsLeague(String countryName){
        Optional<League> league = leagueRepository.findLeagueByCountryName(countryName);
        if(league.isPresent()){
            throw new ResourceExistsException(String.format(THERE_IS_LEAGUE, countryName));
        }
    }
}
