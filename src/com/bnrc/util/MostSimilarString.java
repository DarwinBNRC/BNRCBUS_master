package com.bnrc.util;

import java.util.ArrayList;

import android.R.integer;

public class MostSimilarString {
	private int compare(String str, String target) {
		int d[][]; // ����
		int n = str.length();
		int m = target.length();
		int i; // ����str��
		int j; // ����target��
		char ch1; // str��
		char ch2; // target��
		int temp; // ��¼��ͬ�ַ�,��ĳ������λ��ֵ������,����0����1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // ��ʼ����һ��
			d[i][0] = i;
		}

		for (j = 0; j <= m; j++) { // ��ʼ����һ��
			d[0][j] = j;
		}

		for (i = 1; i <= n; i++) { // ����str
			ch1 = str.charAt(i - 1);
			// ȥƥ��target
			for (j = 1; j <= m; j++) {
				ch2 = target.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}

				// ���+1,�ϱ�+1, ���Ͻ�+tempȡ��С
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1]
						+ temp);
			}
		}
		return d[n][m];
	}

	private int min(int one, int two, int three) {
		return (one = one < two ? one : two) < three ? one : three;
	}

	/**
	 * 
	 * ��ȡ���ַ��������ƶ�
	 * 
	 * 
	 * 
	 * @param str
	 * 
	 * @param target
	 * 
	 * @return
	 */

	public float getSimilarityRatio(String str, String target) {
		return 1 - (float) compare(str, target)
				/ Math.max(str.length(), target.length());
	}
	
	public String getMostSimilarStringWithArray(ArrayList<ArrayList<String>> array, String keyword,int index){
		String string = null;
		String mostSimilarString = null;
		float similer = 0.0f;
		float curSimiler = 0.0f;
		int j = array.size();
		for (int i = 0; i < j; i++) {
			string = array.get(i).get(index);
			curSimiler = getSimilarityRatio(array.get(i).get(index), keyword);
			if (curSimiler >= similer) {
				mostSimilarString = string;
				similer = curSimiler;
			}
		}
		  
		if (mostSimilarString == null) {
			mostSimilarString =  array.get(0).get(index);
		}
		return mostSimilarString;
	}

}