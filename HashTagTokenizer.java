

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean doesExist = false;
		for(int i = 0; i < dictionary.length; i++){
			if(dictionary[i] == word){
				doesExist = true;
			}
		}
		return doesExist;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
        String newHashtag = hashtag.toLowerCase();
        String word = "";
        for (int i = 0; i < N; i++) {
        	word += newHashtag.charAt(i);
        	for(int j = 0; j < dictionary.length; j++){
        		if(word.equals(dictionary[j])){
        			System.out.println(word);
        			breakHashTag(newHashtag.substring(i+1,N), dictionary);
        			return;
        		}
        	
        	}
        }
    }

}
