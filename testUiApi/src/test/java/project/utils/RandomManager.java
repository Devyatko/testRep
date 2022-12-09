package project.utils;

import java.security.SecureRandom;
import java.util.*;

public class RandomManager {
    public static int getNumber(int min, int max) {
        int maxBound = max + 1;
        return new SecureRandom().nextInt(maxBound - min) + min;
    }

    public static String getLowerLatinStr() {
        Random random = new Random();
        char c = (char)(random.nextInt(26) + 'a');
        return String.valueOf(c);
    }

    public static String getRandomText(int min, int max) {
        int length = getNumber(min, max);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String randomStr = getLowerLatinStr();
            builder.append(randomStr);
        }
        return builder.toString();
    }

    public static String getRandomListItem(List list) {
        int size = list.size();
        int num = RandomManager.getNumber(0, size-1);
        return list.get(num).toString();
    }

    public static char getRandomArrayElement(char[] array) {
        int index = RandomManager.getNumber(0, array.length - 1);
        return array[index];
    }

    public static String getRandomCharacter() {
        Random random = new Random();
        char lowerCaseLetter = (char)(random.nextInt(26) + 'a');
        char upperCaseLetter = (char)(random.nextInt(26) + 'A');
        char[] digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char digit = RandomManager.getRandomArrayElement(digits);
        char[] symbols = new char[] { '#', '!', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^',
                '_', '`', '{', '}', '|', '~' };
        char character = RandomManager.getRandomArrayElement(symbols);
        char[] arrayOfRandomCharacters = new char[] {digit, lowerCaseLetter, upperCaseLetter, character};
        int numberOfRandomCharacter = RandomManager.getNumber(0, arrayOfRandomCharacters.length - 1);
        char randomCharacter = arrayOfRandomCharacters[numberOfRandomCharacter];
        return String.valueOf(randomCharacter);
    }

    /*
     * this method gets a set of symbol indexes in word satisfying the condition that
     * a symbol cannot be at the start or at the end of word and symbols cannot be placed side by side
     */
    public static Set<Integer> getSetIndexes(int lengthWord, int numberOfSymbolInWord){
        Set<Integer> setIndexes = new HashSet<>();
        Set<Integer> setNearbyIndexes = new HashSet<>();
        while (setIndexes.size() < numberOfSymbolInWord){
            Integer symbolIndex = RandomManager.getNumber(1, lengthWord - 2);
            if(!setNearbyIndexes.contains(symbolIndex)){
                setIndexes.add(symbolIndex);
                Integer preSymbolIndex = symbolIndex - 1;
                setNearbyIndexes.add(preSymbolIndex);
                Integer postSymbolIndex = symbolIndex + 1;
                setNearbyIndexes.add(postSymbolIndex);
            }
        }
        return setIndexes;
    }

    /*
     * this method gets a random username up to 64 characters long
     * it contains digits, latin letters in upper and lower cases, symbols #!$%&'*+-/=?^_`{}|~ and periods
     * but a username cannot start or end with a period and periods cannot be placed side by side
     */
    public static String getRandomEmailUserName() {
        int lengthUserName = RandomManager.getNumber(1, 64);
        int numberOfPeriodsInUserName = RandomManager.getNumber(0, lengthUserName / 2);
        Set<Integer> setOfPeriodIndexes = RandomManager.getSetIndexes(lengthUserName, numberOfPeriodsInUserName);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lengthUserName; i++){
            if (setOfPeriodIndexes.contains(i)){
                builder.append(".");
            } else {
                String randomStr = getRandomCharacter();
                builder.append(randomStr);
            }
        }
        return builder.toString();
    }

    /*
     * this method gets a random domain up to 255 characters long
     * and satisfies the condition that an email contains no more than 256 in total
     * a domain contains latin letters in lower case and hyphens, but
     * it cannot start or end with a hyphen and hyphens cannot be placed side by side
     */
    public static String getRandomDomain(String randomUserName, String randomTLD){
        int lengthDomain = RandomManager.getNumber(1, (256 - randomTLD.length() - randomUserName.length()));
        int numberOfHyphensInDomain = RandomManager.getNumber(0, lengthDomain / 2);
        Set<Integer> setOfHyphenIndexes = RandomManager.getSetIndexes(lengthDomain, numberOfHyphensInDomain);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lengthDomain; i++){
            if (setOfHyphenIndexes.contains(i)){
                builder.append("-");
            } else {
                String randomStr = getLowerLatinStr();
                builder.append(randomStr);
            }
        }
        return builder.toString();
    }

    //this method gets a random email like userName@domain.TopLevelDomain in accordance with the RFC 2822
    public static String getRandomEmail(){
        StringBuilder builder = new StringBuilder();
        // length of Top Level Domain must be between 2 and 6 characters
        String randomTLD = RandomManager.getRandomText(2, 6);
        String randomUserName = RandomManager.getRandomEmailUserName();
        String randomDomain = RandomManager.getRandomDomain(randomUserName, randomTLD);
        builder.append(randomUserName);
        builder.append("@");
        builder.append(randomDomain);
        builder.append(".");
        builder.append(randomTLD);
        return builder.toString();
    }
}

