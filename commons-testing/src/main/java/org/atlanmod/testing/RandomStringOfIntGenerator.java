package org.atlanmod.testing;

import java.util.Random;

public class RandomStringOfIntGenerator implements Generator{


    @Override
    /**
     *Generate a string.
     */
    public String generate() {
        Random random = new Random();
        int length= random.nextInt(10)+1;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(10) + 48;
            char generatedChar = (char)randomInt;
            sb.append(generatedChar);
        }
        return sb.toString();
    }

    @Override
    /**
     *return an array of class which contains the string class.
     */
    public Class[] types() {
        Class[] types={String.class};
        return types;
    }
}
