/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Arrays;

import static java.sql.Types.NULL;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<>();
    HashSet<String> wordSet = new HashSet<>();
    HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();
    ArrayList<String> wordsOfaParticularLength;
    HashMap<Integer, ArrayList<String>> sizeToWords = new HashMap<>();
    int wordLength;

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);
            wordList.add(word);
            wordLength=DEFAULT_WORD_LENGTH;

            /**
             * making entry in sizeToWords hashmap
             */
            int lengthOfWord = word.length();
            if(sizeToWords.containsKey(lengthOfWord)){
                ArrayList<String> list = sizeToWords.get(lengthOfWord);
                list.add(word);
                sizeToWords.put(lengthOfWord,list);
            }
            else{
                ArrayList<String> list = new ArrayList<>();
                list.add(word);
                sizeToWords.put(lengthOfWord,list);
            }

            /**
             * making entry in lettersToWord hashmap
             */
            String sortedReadWord = sortLetters(word);
            if(lettersToWord.containsKey(sortedReadWord)){
               ArrayList<String> list = lettersToWord.get(sortedReadWord);
               list.add(word);
               lettersToWord.put(sortedReadWord, list);
            }
            else{
                ArrayList<String> list = new ArrayList<>();
                list.add(word);
                lettersToWord.put(sortedReadWord,list);
            }
        }
    }

/*
    public boolean isGoodWord(String word, String base) {
      if(wordSet.contains(word)){
          return true;
      }
      return false;
    }*/

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        int n = word.length();
        for(Map.Entry<String,ArrayList<String>> entry : lettersToWord.entrySet()){
           //checking if key has length one more than the length of word and has the letters of word
            if(entry.getKey().length()==(n+1) && wordContainsLettersOfBase(entry.getKey(), word)){
               //for each entry in array list of that key
                for(String temp : entry.getValue()){
                   //if word is not a substring of the entry in array list
                    if(!temp.contains(word)){
                        result.add(temp);
                    }
                }
            }
        }
        return result;
    }

    public String pickGoodStarterWord(){
        int index = new Random().nextInt(wordList.size());
        for(int i=index; index<wordList.size(); i++){
           String starterWord = wordList.get(i);
           if(starterWord.length()==wordLength) {
               ArrayList<String> list = new ArrayList<>();
               list = getAnagramsWithOneMoreLetter(starterWord);
                    if (list.size() >= MIN_NUM_ANAGRAMS) {
                        if(wordLength<MAX_WORD_LENGTH){
                            wordLength++;
                        }
                        return starterWord;
                    }
           }
       }
       return "badge";
    }

    public String sortLetters(String wordToBeSorted){
        char unsorted[] = wordToBeSorted.toCharArray();
        Arrays.sort(unsorted);
        return new String(unsorted);
    }

    /**
     *checks whether word contains all the letters of base..
     * word may contain extra letters but it must contain the letters of base atleast
     */

    public boolean wordContainsLettersOfBase(String word, String base){
        int n = base.length();
        for(int i=0; i<n; i++){
            if (word.indexOf(base.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }
}
