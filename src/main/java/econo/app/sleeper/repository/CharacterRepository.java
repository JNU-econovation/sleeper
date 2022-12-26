package econo.app.sleeper.repository;


import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Color;
import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CharacterRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Character character){
        em.persist(character);
        log.info("save: character={}", character);
    }

    public void delete(Character character){
        em.remove(character);
        log.info("delete: character={}", character);
    }



}
