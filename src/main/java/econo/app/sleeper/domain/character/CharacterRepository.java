package econo.app.sleeper.domain.character;


import econo.app.sleeper.domain.character.Character;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CharacterRepository {

    @PersistenceContext
    private final EntityManager em;

    public Long save(Character character){
        em.persist(character);
        log.info("save: character={}", character);
        return character.getId();
    }

    public Optional<Character> find(Long characterPk){
        Character character = em.find(Character.class, characterPk);
        return Optional.ofNullable(character);
    }








}
