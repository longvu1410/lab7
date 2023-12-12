package lab_7;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyWordCount {

	public static final String fileName = "D:\\laptrinh\\lab_7\\src\\lab_7\\hamlet.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Returns a set of WordCount objects that represents the number of timeseach
	// unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {
		List<WordCount> ans = new ArrayList<>();
		for (String word : words) {
			int cnt = 0;
			WordCount wc = new WordCount(word, count(word));
			if (!ans.contains(wc)) {
				ans.add(wc);
			} else {
				++cnt;
				wc.setCount(wc.getCount() + cnt);
			}
		}

		return ans;

	}

	private int count(String word) {
		int cnt = 0;
		for (String w : words) {
			if (w.equals(word)) {
				++cnt;
			}
		}
		return cnt;
	}

	// Returns the words that their appearances are 1, do not consider duplidated
	// words
	public Set<String> getUniqueWords() {
		Set<String> w1 = new HashSet<>();
		for (WordCount wc : getWordCounts()) {
			if (wc.getCount() == 1) {
				w1.add(wc.getWord());
			}
		}

		return w1;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	public Set<String> getDistinctWords() {
		Set<String> wc = new HashSet<>();
		for (WordCount ab : getWordCounts()) {
			if (ab.getCount() > 1) {
				wc.add(ab.getWord());
			}
		}

		return wc;
	}
	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according to ascending order of tokens
	// Example: An - 3, Bug - 10, ...

	public Set<WordCount> exportWordCounts() {
		Set<WordCount> wc = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {

				return o1.getWord().compareTo(o2.getWord());
			}

		});

		wc.addAll(getWordCounts());

		return wc;
	}
	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.

	public Set<WordCount> exportWordCountsOrderByOccurence() {
		Set<WordCount> wc = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				int inf = o2.getCount() - o1.getCount();
				if (inf == 0)
					return o1.getWord().compareTo(o2.getWord());
				return inf;
			}
		});
		wc.addAll(getWordCounts());
		return wc;
	}

	// delete words beginning with the given pattern (i.e., delete words begin
	// with 'A' letter
	public Set<WordCount> filterWords(String pattern) {
		Set<WordCount> wc = new HashSet<>();
		for (String word : words) {
			WordCount cwc = new WordCount(word, count(word));
			if (!cwc.getWord().startsWith(pattern)) {
				wc.add(cwc);
			}
		}
		return wc;
	}

	public static void main(String[] args) {
		MyWordCount mwc = new MyWordCount();
		String abc = "A";

		List<WordCount> listGetWordCount = mwc.getWordCounts();
		Set<String> wordsDoNotOverlap = mwc.getUniqueWords();
		Set<String> duplicatedWordsOnce = mwc.getDistinctWords();
		Set<WordCount> sortAB = mwc.exportWordCounts();
		Set<WordCount> sortedInDescendingOrder = mwc.exportWordCountsOrderByOccurence();
		Set<WordCount> delA = mwc.filterWords(abc);

		System.out.println("Danh Sách WordCount: ");
		for (WordCount wc : listGetWordCount) {
			System.out.println(wc);
		}

		System.out.println("In ra từ không trùng: ");
		System.out.println(wordsDoNotOverlap + "\n");

		System.out.println("In ra từ trùng lụp: ");
		System.out.println(duplicatedWordsOnce + "\n");

		System.out.println("Sắp Xếp Theo Thứ Tự Bảng Chữ Cái: ");
		for (WordCount wc : sortAB) {
			System.out.println(wc);
		}

		System.out.println("Sắp Xếp Theo Thứ Tự Giarm dần: ");
		for (WordCount wc : sortedInDescendingOrder) {
			System.out.println(wc);
		}

		System.out.println("In ra các đối tượng đã xóa chữ A đầu :");
		for (WordCount wc : delA) {
			System.out.println(wc);
		}
	}
}
