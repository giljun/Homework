import java.util.*;

public class Solution_1244 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// TestCase
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			String str = sc.next();

			// 교환 횟수
			int change_num = sc.nextInt();

			int N = str.length();

			// 문자열 길이만큼의 정수형 배열을 만든다.
			int[] nArr = new int[N];

			// 문자열의 값을 정수형 데이터도 바꿔서 저장한다.
			for (int i = 0; i < nArr.length; i++) {
				nArr[i] = str.charAt(i) - '0';
			}

			// 교환 시작
			int max_num = 0;
			int index = 0;
			while (change_num > 0) {
				// 앞자리부터 시작한다.
				for (int i = 0; i < nArr.length; i++) {
					max_num = 0;
					for (int j = i+1; j < nArr.length; j++) {
						// 최대값을 찾아서 인덱스를 넘겨준다.
						if( max_num <= nArr[j]) {
							max_num = nArr[j];
							index = j;
						}
					}
					// 첫번째 위치의 숫자와 최대값을 비교하여,
					// 크면,
					// 자리를 변경한다. 교환 횟수 차감.
					if(nArr[i] < max_num) {
						swap(nArr, i, index);
						change_num--;
					}
					// 교환 횟수가 남아있지 않다면, 반복문 탈출.
					// 남아있다면, 다음 자리로 넘어간다.
					if(change_num == 0) {
						break;
					}
				}
				
				// 최고상금을 구했는데,
				// 교환횟수가 짝수번(짝수번은 계산안해도 된다. 원상복귀 시키는데, 2번의 교환횟수를 사용하니까.)이 아닌,
				// 홀수번만큼 남아있다면,
				// 오른쪽에서 출발하여, 가장 작은 값을 찾아서 스위칭!
				if(change_num%2 == 0) {
					change_num = 0;
				} else {
					int tmp = 0; // 인접한 배열에 같은 값이 있으면, ++해줄 것.
					for (int i = 0; i < nArr.length-1; i++) {
						if(nArr[i] == nArr[i+1]) {
							tmp++;
						}
					}
					// 인접한 배열에 같은 값이 있다는 뜻이므로, 그 두 개의 값을 스위칭해주면 교환 횟수를 차감할 수 있다.
					if( tmp != 0 ) {
						change_num = 0;
					} else { // 인접한 배열에 같은 값이 없다면,
						// 가장 오른쪽에 위치한 2개의 값을 바꿔준다.
						swap(nArr, nArr.length-1, nArr.length-2);
						change_num = 0;
					}
				}
			}
			
			System.out.print("#"+tc+" ");
			for (int i = 0; i < nArr.length; i++) {
				System.out.print(nArr[i]);
			}
			System.out.println();
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}