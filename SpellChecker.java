
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
    String wordOne = word1.toLowerCase();
    String wordTwo = word2.toLowerCase();
    if (wordOne.equals("")) {
        return wordTwo.length();
    }
    if (wordTwo.equals("")) {
        return wordOne.length();
    }
    if (head(wordOne).equals(head(wordTwo))) {
        return levenshtein(tail(wordOne), tail(wordTwo));
    }

    int firstCheck = levenshtein(tail(wordOne), wordTwo);
    int secondCheck = levenshtein(wordOne, tail(wordTwo));
    int thirdCheck = levenshtein(tail(wordOne), tail(wordTwo));

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
            if (distance < levenshtein(word, newWord) || newWord.equals(word)) {
                newWord = dictionary[i];
            }
        }
    }

    return newWord;
}

}
