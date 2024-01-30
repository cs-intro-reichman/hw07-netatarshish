
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1,str.length());
	}

	public static String head(String str) {
		String head = String.valueOf(str.charAt(0));
		return head;
	}

	public static int levenshtein(String word1, String word2) {
    if (word1.equals("")) {
        return word2.length();
    }
    if (word2.equals("")) {
        return word1.length();
    }
    if (head(word1).equals(head(word2))) {
        return levenshtein(tail(word1), tail(word2));
    }

    int firstCheck = levenshtein(tail(word1), word2);
    int secondCheck = levenshtein(word1, tail(word2));
    int thirdCheck = levenshtein(tail(word1), tail(word2));

    return 1 + Math.min(firstCheck, Math.min(secondCheck, thirdCheck));
}
	

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
    String newWord = word;
    for (int i = 0; i < dictionary.length; i++) {
        int distance = levenshtein(word, dictionary[i]);

        if (distance <= threshold) {
        	if(newWord!=word){
        		if (distance < levenshtein(word, newWord)) {
        			 newWord = dictionary[i];
        		}
        	}
            newWord = dictionary[i];
        }
    }

    return newWord;
}

}
