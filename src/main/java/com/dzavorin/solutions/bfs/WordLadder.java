package com.dzavorin.solutions.bfs;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);

        LinkedList<Pair<String, Integer>> list = new LinkedList<>();
        list.add(new Pair<>(beginWord, 1));

        while (!list.isEmpty()) {
            Pair<String, Integer> pair = list.poll();

            if (pair.getKey().equals(endWord)) {
                return pair.getValue();
            }

            Set<String> remove = new HashSet<>();

            for (String word : set) {
                int diff = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != pair.getKey().charAt(i)) {
                        diff++;
                    }
                }
                if (diff == 1) {
                    list.add(new Pair<>(word, pair.getValue() + 1));
                    remove.add(word);
                }

            }
            set.removeAll(remove);
        }

        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int len = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(word -> {
            for (int i = 0; i < len; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, len);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });

        // Queue for BFS
        Queue<Pair<String, Integer>> list = new LinkedList<>();
        list.add(new Pair<>(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!list.isEmpty()) {
            Pair<String, Integer> pair = list.poll();
            String word = pair.getKey();
            int level = pair.getValue();
            for (int i = 0; i < len; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, len);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.contains(adjacentWord)) {
                        list.add(new Pair<>(adjacentWord, level + 1));
                        visited.add(adjacentWord);
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new WordLadder()
                .ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

}
