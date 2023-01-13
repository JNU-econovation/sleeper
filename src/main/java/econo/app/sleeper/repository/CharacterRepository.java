package econo.app.sleeper.repository;


import econo.app.sleeper.domain.character.Character;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.Optional;

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

    public Optional<Character> findByPk(Long characterPk){
        Character character = em.find(Character.class, characterPk);
        return Optional.of(character);
    }

    public Optional<Character> findById(String userId) {
        return em.createQuery("select c from Character c join c.user u where u.userId = :userId",Character.class)
                .setParameter("userId",userId)
                .getResultList().stream().findFirst();
    }






}
