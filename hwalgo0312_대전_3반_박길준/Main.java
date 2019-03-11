import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static int N;
	static ArrayList<Student> students;
	static int nu_time, u_time;

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

		// 정렬 완료

//  for (int i = 0; i < students.size(); i++) {
//   System.out.println(students.get(i).start + " " + students.get(i).end);
//  }

		u_time = 0;
		nu_time = 0;

		int t_time = 0;
		int tt_time = 0;
		int end_time = 0;

		for (int i = 0; i < students.size() - 1; i++) {
			for (int j = i + 1; j < students.size(); j++) {
				if (students.get(i).end < students.get(j).end) {
					if (students.get(i).end < students.get(j).start) {
						t_time = end_time - students.get(i).start;
						if (u_time < t_time) {
							u_time = t_time;
						}

						tt_time = students.get(j).start - end_time;
						if (nu_time < tt_time) {
							nu_time = tt_time;
						}
						i = j - 1;
						break;
					} else {
						t_time = students.get(j).end - students.get(i).start;
						if (u_time < t_time) {
							u_time = t_time;
						}
					}
				} else {
					end_time = students.get(i).end;
				}
			}
		}

		System.out.println(u_time + " " + nu_time);
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
