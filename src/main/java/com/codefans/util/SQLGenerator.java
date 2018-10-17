package com.codefans.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SQLGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLGenerator sg = new SQLGenerator();
		// sg.addUser();
		sg.build();
	}

	public void addUser() {
		FileWriter fw = null;
		try {
			File f = new File("C:\\Users\\Administrator\\addUser.bat");
			int num = 20000;

			fw = new FileWriter(f);
			for (int i = 0; i < num; i++) {
				fw.write(
						"mysql msdb -uroot -e \"insert into mailuser(username, authenticName, displayname, status) values('displayname"
								+ i + "', 'username" + i + "', 'auname" + i + "', 1);\"");
				fw.write("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.flush();
					fw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void build() {
		try {
			// String[] file = new String[]{"C:/usernames.txt", "", "", "", ""};
			String[] file = new String[] { "C:/1-500.txt", "C:/501-1000.txt", "C:/1001-1500.txt", "C:/1501-2000.txt",
					"C:/2001-2602.txt" };
			// String[] file = new String[]{"C:/2001-2602.txt"};
			for (int i = 0; i < file.length; i++) {
				build(file[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public String build_old(String file) {
		StringBuffer sb = new StringBuffer();
		try {
			// String file = "C:/usernames.txt";
			Scanner sc = new Scanner(new File(file));
			String line = "";
			sb.append("update mailuser set status=-1 where status=1 and username in (");

			int index = 0;
			while (sc.hasNextLine()) {
				line = sc.nextLine().trim();
				if (index == 0) {
					sb.append("'").append(line).append("'");
				} else {
					sb.append(", '").append(line).append("'");
				}
				index++;
			}

			System.out.println("count:[" + (index + 1) + "]");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public String build(String file) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
			String line = "";
			sb.append("update mailuser set status=-1 where status=1 and username in (");

			int index = 0;
			line = br.readLine().trim();
			while (line != null && line.trim().length() > 0) {
				if (index == 0) {
					sb.append("'").append(line).append("'");
				} else {
					sb.append(", '").append(line).append("'");
				}
				line = br.readLine();
				index++;
			}
			sb.append(");");

			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public void build_backup() {
		try {
			String file = "C:/usernames.txt";
			Scanner sc = new Scanner(new File(file));
			String line = "";
			StringBuffer sb = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			StringBuffer sb3 = new StringBuffer();
			StringBuffer sb4 = new StringBuffer();
			StringBuffer sb5 = new StringBuffer();
			sb.append("update mailuser set status=-1 where status=1 and username in (");
			sb2.append("update mailuser set status=-1 where status=1 and username in (");
			sb3.append("update mailuser set status=-1 where status=1 and username in (");
			sb4.append("update mailuser set status=-1 where status=1 and username in (");
			sb5.append("update mailuser set status=-1 where status=1 and username in (");
			int index = 0;
			int count_01 = 0;
			int count_02 = 0;
			int count_03 = 0;
			int count_04 = 0;
			int count_05 = 0;
			while (sc.hasNext()) {
				line = sc.next().trim();
				if (index < 500) {
					count_01++;
					if (index == 0) {
						sb.append("'").append(line).append("'");
					} else {
						sb.append(", '").append(line).append("'");
					}
				} else if (index >= 500 && index < 1000) {
					count_02++;
					if (index == 500) {
						sb2.append("'").append(line).append("'");
					} else {
						sb2.append(", '").append(line).append("'");
					}
				} else if (index >= 1000 && index < 1500) {
					count_03++;
					if (index == 1000) {
						sb3.append("'").append(line).append("'");
					} else {
						sb3.append(", '").append(line).append("'");
					}
				} else if (index >= 1500 && index < 2000) {
					count_04++;
					if (index == 1500) {
						sb4.append("'").append(line).append("'");
					} else {
						sb4.append(", '").append(line).append("'");
					}
				} else if (index >= 2000) {
					count_05++;
					if (index == 2000) {
						sb5.append("'").append(line).append("'");
					} else {
						sb5.append(", '").append(line).append("'");
					}
				}
				index++;
			}
			sb.append(");");
			sb2.append(");");
			sb3.append(");");
			sb4.append(");");
			sb5.append(");");

			System.out.println(sb.toString());
			System.out.println(sb2.toString());
			System.out.println(sb3.toString());
			System.out.println(sb4.toString());
			System.out.println(sb5.toString());

			System.out.println("total:[" + (index + 1) + "]");
			System.out.println("count_01:[" + count_01 + "]");
			System.out.println("count_02:[" + count_02 + "]");
			System.out.println("count_03:[" + count_03 + "]");
			System.out.println("count_04:[" + count_04 + "]");
			System.out.println("count_05:[" + count_05 + "]");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
