package lab_7;

import java.util.Objects;

public class WordCount {
	private String word;
	private int count;
	public WordCount(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getWord() {
		// TODO Auto-generated method stub
		return word;
	}

	@Override
	public int hashCode() {
		return Objects.hash(word);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordCount other = (WordCount) obj;
		return Objects.equals(word, other.word);
	}

	@Override
	public String toString() {
		return "WordCount [word=" + word + ", count=" + count + "]";
	}
	
	
	
}