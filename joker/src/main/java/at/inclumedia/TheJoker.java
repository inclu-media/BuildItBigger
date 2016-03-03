package at.inclumedia;

import java.util.Random;

public class TheJoker {

    private static final String[] puns = {
            "It’s hard to explain puns to kleptomaniacs because they always take things literally.",
            "I used to think the brain was the most important organ. Then I thought, look what’s telling me that.",
            "The midget fortune teller who kills his customers is a small medium at large.",
            "A farmer in the field with his cows counted 196 of them, but when he rounded them up he had 200.",
            "What does a nosey pepper do? Get jalapeño business.",
            "What is Bruce Lee’s favorite drink? Wataaaaah!",
            "The dyslexic devil worshipper sold his soul to Santa.",
            "You kill vegetarian vampires with a steak to the heart.",
            "If you want to catch a squirrel just climb a tree and act like a nut.",
            "So this guy with a premature ejaculation problem comes out of nowhere.",
            "A magician was walking down the street and turned into a grocery store.",
            "A blind man walks into a bar. And a table. And a chair.",
            "Why don’t you ever see hippopotamus hiding in trees? Because they’re really good at it.",
            "Did you hear about the Mexican train killer? He had locomotives."
    };

    public static String tellJoke() {
        Random ran = new Random();
        int num = ran.nextInt(puns.length);

        return puns[num];
    }
}
