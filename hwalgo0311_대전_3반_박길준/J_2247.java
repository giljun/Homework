import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static int N;
	static ArrayList<Student> students;
	static int result_in, result_out;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		students = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			students.add(new Student(sc.nextInt(), sc.nextInt()));
		}

		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.start > o2.start) {
					return 1;
				} else if (o1.start < o2.start) {
					return -1;
				} else {
					return o1.end - o2.end;
				}
			}
		});

		result_in = 0;
		result_out = 0;

		int tmp_start = 0;
		int tmp_end = 0;

		Student s = students.get(0);
		Student tmp = students.get(0);
		for (int i = 1; i < students.size(); i++) {
			if (s.end < students.get(i).end) {
				if (s.end < students.get(i).start) { // 시간이 벌어짐
					tmp_start = s.end;
					tmp_end = students.get(i).start;
					if (result_out < tmp_end - tmp_start) {
						result_out = tmp_end - tmp_start;
					}
					s = students.get(i);
				} else { // 길이가 더 길긴 하지만, 겹치는 부분
					tmp_start = s.start;
					tmp_end = students.get(i).end;
					if (result_in < tmp_end - tmp_start) {
						result_in = tmp_end - tmp_start;
					}
					s.end = students.get(i).end;
				}
			} else { // 시간이 겹침.
				tmp_end = s.end; // 시간이 겹치는 경우에는 기준이 되는 부분의 끝시간이 가장 길다.
				tmp_start = s.start;
				if (result_in < tmp_end - tmp_start) {
					result_in = tmp_end - tmp_start;
				}
			}
		}

		System.out.println(result_in + " " + result_out);
	}

	static class Student {
		int start;
		int end;

		Student(int s, int e) {
			start = s;
			end = e;
		}
	}
}
