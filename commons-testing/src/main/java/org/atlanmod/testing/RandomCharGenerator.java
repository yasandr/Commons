package org.atlanmod.testing;

import java.util.Random;

public class RandomCharGenerator implements Generator<Character>{
    @Override
    /**
     *Generate a char.
     */
    public Character generate() {
        Random random = new Random();
        int randomInt = random.nextInt(10) + 48;
        int randomUpperCaseAlphabet = random.nextInt(26) + 65;
        int randomLowerCaseAlphabet = random.nextInt(26) + 97;
        int[] possibleValues = {randomInt, randomLowerCaseAlphabet, randomUpperCaseAlphabet};
        int choice = random.nextInt(3);
        char generatedChar = (char)possibleValues[choice];
        return generatedChar;
    }

    @Override
    /**
     *return an array of class which contains the Character class.
     */
    public Class[] types() {
        Class[] types={Character.class};
        return types;
    }
}
