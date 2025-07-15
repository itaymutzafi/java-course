
public class StringUtils {
	public static int count_words(String str){
		String [] arr = str.split(" ");
		return arr.length;
	}

	public static String findSortedSequence(String str) {
		if (str.trim().isEmpty()) return "";
		String result = "";
		String[] str_Array = str.split(" ");
		int len_arr = str_Array.length;
		String tmp = "";
		for (int i = 0; i < len_arr - 1; i++) {
			int compare = str_Array[i].compareTo(str_Array[i + 1]);
			tmp = tmp.isEmpty()? str_Array[i]: tmp+" "+str_Array[i];
			if (compare > 0) {
				int result_count = count_words(result);
				int tmp_count = count_words(tmp); //+1 for the first space doesn't count
				if (result_count <= tmp_count) result = tmp;
				tmp = "";
			}
			}
		tmp = tmp.isEmpty()? str_Array[len_arr-1]: tmp+" "+str_Array[len_arr-1];
		int last_tmp_cnt = count_words(tmp);
		int result_count = count_words(result);
		if (result_count <= last_tmp_cnt) result = tmp;
		return result;

	}

	public static boolean isEditDistanceOne(String a, String b){
		int len_a = a.length();
		int len_b = b.length();
		int dis = Math.abs(len_a-len_b);
		if (dis > 1) { //if the length dif is greater than 1 it can't be True
            return false;
        }
		int cnt = 0; // count the difference between a and b
		int i = 0;
		int j = 0; //pointers for a and b
		while (i<a.length() && j<b.length()){ //until we reach the end of minimum string
			if (a.charAt(i) != b.charAt(j)){ //if there is a dif
				cnt+=1;
				if (cnt>1) return false; //stop iterating early
				if(len_a > len_b) i++;
				else if (len_b > len_a) j++;
				else {
					j++;
					i++;
				}
			}
			else{ //if there is not a difference move forward
				j++;
				i++;
			}
		}
		if (j< len_b || i<len_a){ //handle case of
			cnt+=1;
		}
		return cnt <= 1; //Replace this with the correct returned value
	}

}
