package econo.app.sleeper.util;

import org.junit.Test;
import org.springframework.expression.spel.ast.OpNE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LevelTest {

    @Test
    public void test(){
        Map<Integer, Long> levelByEx = Arrays.stream(Level.values()).collect(Collectors.toMap(l -> l.getExperience(), l -> l.getLevel()));
        System.out.println("Level.ONE.getLevel() = " + Level.ONE.getLevel());
        System.out.println("Level.ONE.getLevel() = " + Level.ONE.getExperience());
    }
}