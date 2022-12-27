package econo.app.sleeper.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExperienceManagerTest {

    @Test
    public void convertExToLevel() {
        System.out.println("level = " + ExperienceManager.convertExToLevel(100));
        System.out.println("level = " + ExperienceManager.convertExToLevel(1400));

    }
}