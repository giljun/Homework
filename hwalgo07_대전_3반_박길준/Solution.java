package ssafy0128;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int tc = 10;
		
		for (int i = 0; i < tc; i++) {
			// 1. 원본 암호문의 길이
			int nCrypto = sc.nextInt();
			
			// 2. 원본 암호문
			LinkedList<Integer> cryptogram = new LinkedList<>();
			
			for (int j = 0; j < nCrypto; j++) {
				cryptogram.add(sc.nextInt());	
			}
			
			// 3. 명령어의 개수
			int nCommand = sc.nextInt();
			
			// 4. 명령어
			for (int j = 0; j < nCommand; j++) {
				// '|' 받기
				String s = sc.next();
				
				// 암호문이 들어갈 위치 (x)
				int start = sc.nextInt();
				
				// 들어갈 숫자의 개수 (y)
				int num = sc.nextInt();
				
				// 들어갈 숫자들. (s)
				for (int k = 0; k < num; k++) {
					cryptogram.add(start++, sc.nextInt());
				}
			}
			
			System.out.print("#"+(i+1)+" ");
			for (int j = 0; j < 10; j++) {
				System.out.print(cryptogram.get(j)+" ");
			}
			System.out.println();
		}
	}
	

}
