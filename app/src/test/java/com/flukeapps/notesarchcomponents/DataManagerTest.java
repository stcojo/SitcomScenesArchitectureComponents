package com.flukeapps.notesarchcomponents;

import com.flukeapps.notesarchcomponents.model.Scene;
import com.flukeapps.notesarchcomponents.utils.Utils;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {
    @Test
    public void createSceneTest(){
        final Scene testScene = Utils.generateRandomScene();
        final Scene testScene2 = Utils.generateRandomScene();
        assertNotEquals(testScene.getCharacter1(), testScene.getCharacter2());
        assertNotEquals(testScene2.getCharacter1(), testScene2.getCharacter2());
    }
}
