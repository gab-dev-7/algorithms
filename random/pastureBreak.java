package random;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class pastureBreak {

    // Word Break Problem: Check if a string can be broken into space-separated
    // sequence of one or more dictionary words.
    public static boolean canBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        System.out.println("Can break 'leetcode'? " + canBreak(s, wordDict));

        String s2 = "applepenapple";
        List<String> wordDict2 = List.of("apple", "pen");
        System.out.println("Can break 'applepenapple'? " + canBreak(s2, wordDict2));
    }
}
