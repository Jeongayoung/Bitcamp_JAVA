package View;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import Controller.UserC;
import Controller.reservationC;
import FileSave.FileSave;
import FileSave.FileSave2;
import Model.SnackM;
import Model.UserM;
import Model.reservationM;

public class View implements Serializable {

	Scanner sc = new Scanner(System.in);
	SnackM ss = new SnackM();
	List<String> movieList = new ArrayList<>();
	List<String> timeList = new ArrayList<>();
	List<SnackM> sSave = new ArrayList<>();
	reservationM rv = new reservationM();
	UserM use = new UserM();
	Calendar cal = new GregorianCalendar();
	Calendar oCalendar = Calendar.getInstance();

	String[] sFood = { "������������", "ī��� ����", "�÷��� �ֵ���", "���� ��¡�� ����", "ĥ��ġ�� ����" };
	int[] lfPrice = { 0, 5500, 4000, 5000, 3500 };
	String[] sJuice = { "������������", "ź������", "�Ƹ޸�ī��", "���� ���̵�", "������ ���̽�Ƽ" };
	int[] ljPrice = { 0, 1500, 2000, 2500, 3500, 2500 };
	String[] sGoods = { "�������� ����", "�����̴��� ���", "��� �����", "�ظ����� ������", "��Ű���콺 Ƽ����" };
	int[] ldPrice = { 0, 15000, 6500, 18000, 20000 };

	int[][] seat1 = new int[5][5];
	int[][] seat2 = new int[5][5];
	int[][] seat3 = new int[5][5];
	int[][] seat4 = new int[5][5];
	int[][] seat5 = new int[5][5];
	int[][] seat6 = new int[5][5];
	int[][] seat7 = new int[5][5];
	int[][] seat8 = new int[5][5];

	int hour = oCalendar.get(Calendar.HOUR_OF_DAY);
	int min = oCalendar.get(Calendar.MINUTE);
	int sec = oCalendar.get(Calendar.SECOND);

	int no;

	int dinput;
	int minput;
	int tinput;
	boolean flag = true;
	boolean addflag = false;
	int total;
	String cardNum;
	String password;
	String id;
	String title;
	String director;
	String actor;
	String grade;
	String genre;
	String summary;

	public String getSelectMenu() {
		String sel = sc.next();
		return sel;
	}

	public void programExit() {
		System.out.println("����Ǿ����ϴ�.");
		System.exit(0);
	}

	public String idchk(UserC ct) throws IOException {
		String check = "";
		boolean a = true;
		while (a) {
			check = sc.next();
			if (ct.getSize() == 0) {
				return check;
			} else {
				for (int i = 0; i < ct.getSize(); i++) {
					if (ct.getLocate(i).getid().equals(check)) {
						System.out.println("�̹� �����ϴ� ���̵� �Դϴ�.");
						System.out.println("�ٸ����̵� �Է����ּ���");
						break;
					}
					if (i == ct.getSize() - 1) {
						a = false;
					}
				}
			}
		}
		return check;
	}

	public boolean prompt(UserC ct) throws IOException {
		for (int i = 0; i < ct.getSize(); i++)
			if (ct.getLocate(i).getid().equals(this.id))
				use = ct.getLocate(i);
		return true;
	}

	public static String getToday(String pattern) {
		DateFormat dtf = new SimpleDateFormat(pattern);
		final Calendar cal = Calendar.getInstance();
		return dtf.format(cal.getTime());
	}

	public static String getTomorrow(String pattern) {
		DateFormat dtf = new SimpleDateFormat(pattern);
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		return dtf.format(cal.getTime());
	}

	// View
	public void mainView(UserC ct, reservationC re) throws IOException {
		while (true) {
			homeprintMenu();
			String sel = getSelectMenu();
			switch (sel) {
			case "1":
				UInsert(ct);
				break;
			case "2":
				if (login(ct))
					memberprintMenu(ct, re);
				break;
			case "3":
				movieinFo();
				break;
			case "4":
				snackbarList();
				break;
			case "5":
				programExit();
				break;
			default:
				System.out.println("�߸� �ԷµǾ����ϴ�.");
				break;
			}
		}
	}

	// Ȩ�޴�
	public void homeprintMenu() {
		System.out.println();
		System.out.println("	      >>�ȳ��ϼ���, ��Ʈ�ó׸��Դϴ�.	 ");
		System.out.println("	    >>���Ͻô� ���� ��ȣ�� �������ּ���!	");
		System.out.println();
		System.out.println("     ��������������������������������������������������������������������������������");
		System.out.println("     ��        =====<Home Menu>=====         ��");
		System.out.println("     ��                                      ��");
		System.out.println("     ��             [1. ȸ������]              ��");
		System.out.println("     ��             [2. �� �� �� ]              ��");
		System.out.println("     ��             [3. �󿵿�ȭ]              ��");
		System.out.println("     ��             [4. �� �� �� ]              ��");
		System.out.println("     ��             [5. �����ϱ�]              ��");
		System.out.println("     ��                                      ��");
		System.out.println("     ��������������������������������������������������������������������������������");
		System.out.print(">> ");
	}

	public void UInsert(UserC ct) throws IOException {
		FileSave save = new FileSave();
		System.out.println("<< ȸ������ >>");
		System.out.print("�̸� : ");
		String name = sc.next();
		System.out.print("�ڵ�����ȣ : ");
		String phone = sc.next();
		System.out.print("�ֹι�ȣ: ");
		String joomin = sc.next();
		System.out.print("���̵� : ");
		String id = idchk(ct);
		System.out.print("��й�ȣ : ");
		String password = sc.next();
		System.out.print("ī���̸� : ");
		String cardName = sc.next();
		System.out.print("ī���ȣ : ");
		String cardN = sc.next();
		UserM i = new UserM();
		i.msetAll(name, phone, joomin, id, password, cardName, cardN);// �̷��� �ϸ� view���� control�� ��ġ�� �ʰ� �ٷ� �𵨷� ��.
		ct.insert(i); // list�� �Է¹��� ���� ��ü�� ����
		save.cListWriter(ct);
		System.out.println("��ȸ�������̿Ϸ� �Ǿ����ϴ�.��");
	}

	// ȸ�� Ż��
	public void uRemove(UserC ct) throws IOException {
		FileSave save = new FileSave();
		System.out.println("Ż����̵�");
		String a = sc.next();
		for(int i=0; i<ct.getSize(); i++) {
			if(ct.getLocate(i).getid().equals(id) && id == a) {
				System.out.println("���� ����?yn");
				String ans = sc.next();
				if(ans.toLowerCase().trim().equals("y")) {
					ct.remove(ct.getLocate(i));
					System.out.println("Ż�� �Ϸ�Ǿ����ϴ�.");
					save.cListWriter(ct);
					homeprintMenu();
				} else {
					System.out.println("ȸ��X");
				}
			} else {
				System.out.println("�Ͼ��̵�������Ϸ��¾��̵𰡴ٸ�");
				break;
			}
		}
	}
	
	// �α���
	public boolean login(UserC ct) throws IOException {
		System.out.println("���̵� �Է��ϼ���");
		String id = sc.next();
		System.out.println("��й�ȣ�� �Է��ϼ���.");
		String password = sc.next();
		for (int i = 0; i < ct.getSize(); i++) {
			if (ct.getLocate(i).getid().equals(id) && ct.getLocate(i).getpassword().equals(password)) {
				System.out.println("�α��� ����!!");
				this.id = id;
				this.password = password;
				return true;
			}
		}
		System.out.println("�α��� ���� ");
		return false;
	}

	// �α׾ƿ�
	public void logout(UserC ct, reservationC re) throws IOException {
		id = null;
		password = null;
		System.out.println("�α׾ƿ� ����!");
		mainView(ct, re);
	}

	// ȸ������޴�
	public void memberprintMenu(UserC ct, reservationC re) throws IOException {
		if (prompt(ct) == true) {
			System.out.println(">> [" + use.getName() + "]�� ȯ���մϴ�.");
			System.out.println(">> ���Ͻô� ���� ��ȣ�� �������ּ���!");
			System.out.println();
			System.out.println("     ��������������������������������������������������������������������������������");
			System.out.println("     ��        =====<Member Menu>=====       ��");
			System.out.println("     ��                                      ��");
			System.out.println("     ��             [1. ���Ź�ư ]	            ��");
			System.out.println("     ��             [2. ������� ]	            ��");
			System.out.println("     ��             [3. �������� ]	            ��");
			System.out.println("     ��             [4. MY ����  ]	            ��");
			System.out.println("     ��             [5. ȸ��Ż�� ]	            ��");
			System.out.println("     ��             [6. �α׾ƿ� ]	            ��");
			System.out.println("     ��            [7. ���α׷� ���� ]            ��");
			System.out.println("     ��                                      ��");
			System.out.println("     ��������������������������������������������������������������������������������");
			System.out.print(">> ");
			memberSelect(ct, re);
		}
	}

	// ȸ���޴� ���ø޼ҵ�
	public void memberSelect(UserC ct, reservationC re) throws IOException {
		String sel = getSelectMenu();
		switch (sel) {
		case "1":
			ticketing(ct, re);
			memberprintMenu(ct, re);
		case "2":
			ticketCancel(ct, re);
			break;
		case "3":
			mainOrder(ct, re);
			break;
		case "4":
			myInfo(ct, re);
			break;
		case "5":
			uRemove(ct);
			break;
		case "6":
			logout(ct, re);
			break;
		case "7":
			programExit();
			break;
		default:
			System.out.println("�߸� �ԷµǾ����ϴ�.");
			return;
		}
	}

	// �� ���� ���
	public void myInfo(UserC ct, reservationC re) throws IOException {
		while (true) {
			if (prompt(ct) == true) {
				System.out.println("=====<�� ����>=====");
				System.out.println("[" + use.getName() + "]���� �����Դϴ�.");
				System.out.println("ID: " + use.getid());
				System.out.println("Password: " + use.getpassword());
				System.out.println("�̸�: " + use.getName());
				System.out.println("����ȣ: " + use.getphone());
				System.out.println("ī���̸�: " + use.getcardName());
				System.out.println("���� ����: ");
				System.out.println(">> ");
				ticketView(re);
				System.out.println("=================");
			}
			memberprintMenu(ct, re);
		}
	}

	// ������ ��ȭ�Դϴ�.
	public void movieinFo() {
		System.out.println("<<�� ���� ��ȭ ����>>");
		while (flag) {
			System.out.println();
			System.out.println("             [���� ��ȭ ����Ʈ]");
			System.out.println();
			System.out.println("     ������������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("     ��    1. �ݵ�  | 2. �˶��  | 3. �ĸ��� �ξ�  |  4. �㽩 -������ �ٲ� ��ź����       ��");
			System.out.println("     ������������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("         ��������������������������������������������");
			System.out.println("         ��     0.�������� ����       ��");
			System.out.println("         ��������������������������������������������");

			System.out.println(">>����Ȯ���� ���ϴ� ��ȭ ��ȣ�� �Է����ּ���.");
			System.out.println();
			System.out.println("                ");
			movieSelect();
		}
	}

	// ��ȭ ���ù�
	public void movieSelect() {
		int selectNum = sc.nextInt();
		switch (selectNum) {
		case 1:
			MovieInfo1();
			movieinFo();
			break;
		case 2:
			MovieInfo2();
			movieinFo();
			break;
		case 3:
			MovieInfo3();
			movieinFo();
			break;
		case 4:
			MovieInfo4();
			movieinFo();
			break;
		case 0:
			flag = false;
			break;
		default:
			System.out.println(">>�߸� �Է��ϼ̽��ϴ�.");
		}
	}

	// ��ȭ1
	public void MovieInfo1() {

		this.title = "�ݵ�";
		this.director = "����ȣ";
		this.actor = "������, ������";
		this.grade = "15�� �̻�";
		this.genre = "�׼�, ���";
		this.summary = "����̹��� �糭 �� �� 4��\r\n" + "������ ������ �ٽ� ����!\r\n" + "\r\n"
				+ "4�� ��, ���� ��ü�� �۾������ ����̹��� �糭���� ����� Ż���ߴ� ��������(������).\r\n"
				+ "�ٱ��������κ��� ö���� ���� �ݵ��� �ٽ� ���� �ϴ� ���� �� ���� ������ �޴´�.\r\n" + "\r\n"
				+ "���� �ð� ���� ������ Ʈ���� Ȯ���� �ݵ��� ���� ���;� �ϴ� �̼��� �����ϴ� ��\r\n"
				+ "�ΰ����� ����� 631�δ�� 4�� ������ ���� �ż��� ��Ը� ���� ������ ���� ������ �����Ѵ�.\r\n" + "\r\n" + "��ü������ ����,\r\n"
				+ "���㰡 �� ������ ��Ƴ��� ��������(������) ������ �������� ���⸦ ����� ����ϰ�\r\n" + "�̵�� �Բ� �ݵ��� Ż���� �� �ִ� ������ ��ȸ�� ���� �Ѵ�.\r\n"
				+ "\r\n" + "�ǵ��ƿ� ��, ��Ƴ��� �� �׸��� ���Ĺ��� ��\r\n" + "�ʻ��� ������ ���۵ȴ�!";

		System.out.println("����    | " + title + "\n" + "����    | " + director + "\n" + "���    | " + grade + "\n"
				+ "�帣    | " + genre + "\n" + "�ٰŸ� | " + "\n" + "\n" + summary + "\n");
	}

	// ��ȭ2
	public void MovieInfo2() {

		this.title = "�˶��";
		this.director = "���� ��ġ";
		this.actor = "�޳� ������ ,  �� ���̽� ,  ������ ����";
		this.grade = "��ü ������";
		this.genre = " ��庥ó, ����, ȯŸ��";
		this.summary = "�ӳ��� �縷 �� �ź��� �Ʊ׶�� �ձ��� �ô�.\r\n" + "������ ���˶���� ������ �����ġ��� �Ƿڷ� ���� ������ ã�� �����ٰ�\r\n"
				+ "���ο��� �� ���� �ҿ��� ����ִ� ���ϸ� ������ �ǰ�,\r\n" + "�ڽ��� ������ ������ �������� ������ ���ߴ� ���迡 �ָ����� �Ǵµ���";

		System.out.println("����    | " + title + "\n" + "����    | " + director + "\n" + "���    | " + grade + "\n"
				+ "�帣    | " + genre + "\n" + "�ٰŸ� | " + "\n" + "\n" + summary + "\n");
	}

	// ��ȭ3
	public void MovieInfo3() {

		this.title = "�ĸ��� �ξ�";
		this.director = "��Ƽ�ƽ� ������";
		this.actor = "������ ���� , ���ݶ� �ں���";
		this.grade = "12�� �̻�";
		this.genre = "�θǽ�, ���, ȯŸ��";
		this.summary = "�������� ������ �ĸ��� ��,\r\n" + "���� ������ ���ξ�� �뷡�� ��� ������.\r\n" + "\r\n" + "������ ������ �������Ⱦ��.��\r\n"
				+ "�� �̻� ����� ���ٰ� �ϴ� ���� �������ĸ�����\r\n" + "���� ���� ������ �ξ� ����󡯸� ������.\r\n"
				+ "�׸���, �׳ฦ ����ϴ� ���ڴ� ��� �װ� �ȴٴ� ����� �˰� �Ǵµ���\r\n" + "\r\n" + "�������, ���� ������� �ʾƼ� �⻵��.��\r\n"
				+ "����� ���۵Ǵ� ���� �ĸ�, \r\n" + "����ϸ� �ȵǴ� ������� ���� ���ΰ����� ���ξ�� \r\n" + "�ް��� �θǽ��� ��������!";

		System.out.println("����    | " + title + "\n" + "����    | " + director + "\n" + "���    | " + grade + "\n"
				+ "�帣    | " + genre + "\n" + "�ٰŸ� | " + "\n" + "\n" + summary + "\n");
	}

	// ��ȭ4
	public void MovieInfo4() {

		this.title = "�㽩-������ �ٲ� ��ź����";
		this.director = "���� ��ġ";
		this.actor = "�������� �׷� ,  ���� Ű��� ,  ���� �κ�";
		this.grade = "15�� �̻�";
		this.genre = "���";
		this.summary = "�Ǵ��� ��ī�Ӱ�, ��ħ�� ����ϰ�, �ൿ�� �����ϰ�!\r\n" + "\r\n" + "�뼱�ĺ� ���ȸ���� Ʈ������ ������ ����\r\n"
				+ "���������� ���� ��Ŀ �ޱ� �̸�(�������� �׷�)��\r\n" + "Ʈ������ ��ӵǴ� Ʈ���� �������� ȭ���� �߽ɿ� ����.\r\n" + "\r\n"
				+ "����, ���� ��Ŀ�� �׷�õ Į��(���� Ű���)��\r\n" + "����� �Ƿ��� ���ա��̶� �Ҹ��� �������� ȸ���� ����ϰ�\r\n"
				+ "�̿� �ޱ��� ����, �߽� �ִ� ������ �����̽�\r\n" + "���϶� �����ǽ�(���� �κ�) ���� ����� ������ ���ϴµ��� \r\n" + "\r\n"
				+ "�ִ� �Ƿ��� �������� ��ź����\r\n" + "���� �̵��� �����ϰ� ¥���� �������� ���۵ȴ�!";

		System.out.println("����    | " + title + "\n" + "����    | " + director + "\n" + "���    | " + grade + "\n"
				+ "�帣    | " + genre + "\n" + "�ٰŸ� | " + "\n" + "\n" + summary + "\n");
	}

	// �����޴� ���
	public void snackbarList() {
		System.out.println("-----����Ʈ�ó׸� ������-----");
		// Ǫ��
		System.out.println("<����>");
		for (int idx = 1; idx < this.sFood.length; idx++) {

			System.out.print(idx + "." + this.sFood[idx] + " " + this.lfPrice[idx] + "��  ");
			System.out.println();
		}
		// ����
		System.out.println("<����>");
		for (int idx = 1; idx < this.sJuice.length; idx++) {
			System.out.print(idx + "." + this.sJuice[idx] + " " + this.ljPrice[idx] + "��  ");
			System.out.println();
		}

		// ����
		System.out.println("<��ǰ>");
		for (int idx = 1; idx < this.sGoods.length; idx++) {
			System.out.print(idx + "." + this.sGoods[idx] + " " + this.ldPrice[idx] + "��  ");
			System.out.println();
		}
		System.out.println("");
		System.out.println("�١ڷα��� �� �������ּ���ڡ�");
		System.out.println("");
	}

	// return Ǫ��
	public int add_food() {
		addflag = true;
		return this.order_food();
	}

	// return �꽺
	public int add_juice() {
		addflag = true;
		return this.order_juice();
	}

	// return ����
	public int add_goods() {
		addflag = true;
		return this.order_goods();
	}

	// �Ѿ��Դϴ�
	public void price_display() {
		System.out.println("   ***  �Ѿ� : " + this.total + "���� ���Խ��ϴ�     ***");
	}

	public void mainOrder(UserC ct, reservationC re) throws IOException {
		boolean brunning = true;
		int menu = 1;
		while (brunning) {
			menu = intro();
			if (menu == 1) { // �ֹ��ϱ�
				order_food();
				order_juice();
				order_goods();
				price_display();
				cardNum(ct, re);
			} else if (menu == 2) { // ����ϰ� ������
				brunning = false;
			}
		}
	}

	public int intro() {
		int menu = 0;
		System.out.println("   ***     Snack Bar      ***");
		System.out.println();
		System.out.println("     ��������������������������������������������������������������������������������");
		System.out.println("     ��      [1. �ֹ��ϱ�]    [2. �ڷΰ���]       ��");
		System.out.println("     ��������������������������������������������������������������������������������");
		System.out.println();
		System.out.print("   �޴��� �������ּ��� : ");
		menu = sc.nextInt();
		System.out.println();
		System.out.println("============================================");
		System.out.println();
		return menu;
	}

	public int order_food() {
		int num = 0;
		while (true) {
			System.out.println("*** Snack Menu");
			for (int idx = 0; idx < this.sFood.length; idx++) {
				System.out.println();
				if (idx == 0) {
					System.out.print(idx + "." + this.sFood[idx]);
				} else {
					System.out.print(idx + "." + this.sFood[idx] + "  ------  " + this.lfPrice[idx] + "��  ");
				}
			}

			System.out.println();
			System.out.println();
			System.out.print("   ������ �������ּ��� : ");

			try {

				num = sc.nextInt();
				System.out.println();
				System.out.println();
				System.out.println("*** " + sFood[num] + "�� �ֹ��� �Ϸ�Ǿ����ϴ�.");
				System.out.println();
				this.total += this.lfPrice[num];
				break;

			} catch (Exception e) {// e.printStackTrace();
				System.out.println();
				System.out.println("   �߸��Է��ϼ̽��ϴ�.");
				System.out.println();
			}
		}

		ss.FoodSave(sFood[num]);
		return num;

	}

	public int order_juice() {

		System.out.println("============================================");
		int num = 0;
		while (true) {
			System.out.println();
			System.out.println("*** Drink Menu");
			System.out.println();
			for (int idx = 0; idx < this.sJuice.length; idx++) {
				if (idx == 0) {
					System.out.print(idx + "." + this.sJuice[idx]);
				} else {
					System.out.println();
					System.out.print(idx + "." + this.sJuice[idx] + "  ------  " + this.ljPrice[idx] + "��  ");
				}
			}
			System.out.println();
			System.out.println();
			System.out.print("   ���Ḧ �������ּ��� : ");

			try {
				num = sc.nextInt();
				System.out.println();
				System.out.println("*** " + sJuice[num] + "�� �ֹ��� �Ϸ�Ǿ����ϴ�.");
				System.out.println();
				System.out.println("============================================");
				System.out.println();
				this.total += this.ljPrice[num];
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("   �߸��Է��ϼ̽��ϴ�.");
				System.out.println();
			}

		}
		ss.JuiceSave(sJuice[num]);
		return num;
	}

	public int order_goods() {
		int num = 0;
		while (true) {

			System.out.println();
			System.out.println("*** Goods Menu");
			System.out.println();
			for (int idx = 0; idx < this.sGoods.length; idx++) {
				if (idx == 0) {
					System.out.print(idx + "." + this.sGoods[idx]);
				} else {
					System.out.println();
					System.out.print(idx + "." + this.sGoods[idx] + "  ------  " + this.ldPrice[idx] + "��  ");
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.print("   ��� �������ּ��� : ");

			try {

				num = sc.nextInt();
				sc.nextLine();
				System.out.println();
				System.out.println("*** " + sGoods[num] + "�� �ֹ��� �Ϸ�Ǿ����ϴ�.");
				System.out.println();
				System.out.println("============================================");
				System.out.println();
				this.total += this.ldPrice[num];
				break;

			} catch (Exception e) {

				System.out.println();
				System.out.println("   �߸��Է��ϼ̽��ϴ�.");
				System.out.println();
			}

		}
		ss.GoodsSave(sGoods[num]);
		sSave.add(ss); //
		return num;
	}

	public void cardNum(UserC ct, reservationC re) throws IOException {
		System.out.println("     ������������������������������������������������������������������");
		System.out.println("     ��    ��ϵ� ī��� �����Ͻðڽ��ϱ�?  	 ��");
		System.out.println("     ��         (1.Yes / 2.No)        ��");
		System.out.println("     ������������������������������������������������������������������");
		System.out.print(">> ");
		int selectNum = sc.nextInt();
		switch (selectNum) {
		case 1:
			System.out.println();
			System.out.println("	���� �Ϸ�Ǿ����ϴ�. �����մϴ�.");
			System.out.println("	ó�� ȭ������ ���ư��ϴ�.");
			System.out.println();
			memberprintMenu(ct, re);
		case 2:
			System.out.println();
			System.out.println("	Ʋ�� ī�� ��ȣ�Դϴ�. ������  ��ҵƽ��ϴ�.");
			System.out.println("	ó������ ���ư��ϴ�.");
			System.out.println();
			memberprintMenu(ct, re);
		default:
			System.out.println("	�߸� �Է��ϼ̽��ϴ�.");
			System.out.println("	ó������ ���ư��ϴ�.");
			memberprintMenu(ct, re);

		}
	}

	public void dinputSet(int dinput) {
		this.dinput = dinput;
	}

	public void minputSet(int minput) {
		this.minput = minput;
	}

	public void tinputSet(int tinput) {
		this.tinput = tinput;
	}

	public void ticketing(UserC ct, reservationC re) throws IOException {
		select(ct, re);
		movieBasic(ct, re);
		timeBasic(ct, re);
	}

	public void select(UserC ct, reservationC re) throws IOException {
		while (true) {
			System.out.println("���ϴ� ��¥�� �����ϼ���.");
			System.out.println("-----------------------------");
			System.out.println("1. " + getToday("yyyy-MM-dd"));
			System.out.println("2. " + getTomorrow("yyyy-MM-dd"));
			System.out.println(" "); //
			System.out.println("3. [�������� ���ư���]"); // �߰�
			System.out.println("-----------------------------");
			dinput = sc.nextInt();

			switch (dinput) {
			case 1:
				rv.dateSave(getToday("yyyy-MM-dd"));
				System.out.println("-----------------------------"); // �߰�
				System.out.println("1." + getToday("yyyy-MM-dd") + "�� �����ϼ̽��ϴ�."); // �߰�
				movieSelect1(ct, re);
				break;

			case 2:
				rv.dateSave(getTomorrow("yyyy-MM-dd"));
				System.out.println("-----------------------------"); // �߰�
				System.out.println("2." + getTomorrow("yyyy-MM-dd") + "�� �����ϼ̽��ϴ�."); // �߰�
				movieSelect2(ct, re);
				break;

			case 3:
				memberprintMenu(ct, re);
				break;

			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue; // ��Ƽ�� �߰�
			}
		}
	}

	public void movieSelect1(UserC ct, reservationC re) throws IOException {
		movieList.add("�˶��");
		movieList.add("�ĸ��� �ξ�");
		movieBasic(ct, re);
	}

	public void movieSelect2(UserC ct, reservationC re) throws IOException {
		movieList.add("�ݵ�");
		movieList.add("�㽩");
		movieBasic(ct, re);
	}

	public void timeSelect1(UserC ct, reservationC re) throws IOException {
		timeList.add("10:30");
		timeList.add("19:30");
		timeBasic(ct, re);
	}

	public void timeSelect2(UserC ct, reservationC re) throws IOException {
		timeList.add("11:50");
		timeList.add("20:20");
		timeBasic(ct, re);
	}

	void Wrong() {
		System.out.println("�߸� �Է��ϼ̽��ϴ�.");
	}

	public void movieBasic(UserC ct, reservationC re) throws IOException {
		while (true) {
			System.out.println("-----------------------------");
			for (int i = 0; i < movieList.size(); i++) {
				System.out.println((i + 1) + ". " + movieList.get(i));
			}
			System.out.println(" "); //
			System.out.println("3. [�ڷ� ����]"); // �ڷΰ��� ������ �߰�
			System.out.println("-----------------------------");
			System.out.println("���ϴ� ��ȭ�� �����ϼ���.");
			minput = sc.nextInt();
			switch (minput) {
			case 1:
				rv.movieSave(movieList.get(0));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((movieList.get(0)) + "�� �����ϼ̽��ϴ�."); // �߰�
				movieList.clear();
				timeSelect1(ct, re);
				break;
			case 2:
				rv.movieSave(movieList.get(1));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((movieList.get(1)) + "�� �����ϼ̽��ϴ�."); // �߰�
				movieList.clear();
				timeSelect2(ct, re);
				break;
			case 3: // �ڷΰ��� ������ �߰�
				movieList.clear(); //
				select(ct, re); //
				break; //

			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue; // ��Ƽ�� �߰�
			}
		}

	}

	public void timeBasic(UserC ct, reservationC re) throws IOException {
		while (true) {
			System.out.println("-----------------------------");
			for (int i = 0; i < timeList.size(); i++) {
				System.out.println((i + 1) + ". " + timeList.get(i));
			}
			System.out.println(" "); // �߰�
			System.out.println("3. [�ڷ� ����]"); // �߰�
			System.out.println("-----------------------------");
			System.out.println("���ϴ� ȸ���� �����ϼ���.");
			tinput = sc.nextInt();
			if (dinput == 1 && minput == 1 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((timeList.get(0)) + "�� �����ϼ̽��ϴ�."); // �߰�
				timeList.clear();
				seats1(ct, re);
			} else if (dinput == 1 && minput == 1 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((timeList.get(1)) + "�� �����ϼ̽��ϴ�."); // �߰�
				timeList.clear();
				seats2(ct, re);
			} else if (dinput == 1 && minput == 2 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				timeList.clear();
				seats3(ct, re);
			} else if (dinput == 1 && minput == 2 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((timeList.get(1)) + "�� �����ϼ̽��ϴ�."); // �߰�
				timeList.clear();
				seats4(ct, re);
			} else if (dinput == 2 && minput == 1 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((timeList.get(0)) + "�� �����ϼ̽��ϴ�."); // �߰�
				timeList.clear();
				seats5(ct, re);
			} else if (dinput == 2 && minput == 1 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((timeList.get(1)) + "�� �����ϼ̽��ϴ�."); // �߰�
				timeList.clear();
				seats6(ct, re);
			} else if (dinput == 2 && minput == 2 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((timeList.get(0)) + "�� �����ϼ̽��ϴ�."); // �߰�
				timeList.clear();
				seats7(ct, re);
			} else if (dinput == 2 && minput == 2 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // �߰�
				System.out.println((timeList.get(1)) + "�� �����ϼ̽��ϴ�."); // �߰�
				timeList.clear();
				seats8(ct, re);
			} else if (tinput == 3) { //
				timeList.clear(); //
				if (dinput == 1) { // �ڷΰ��� �߰�
					movieSelect1(ct, re);
				} //
				else {
					movieSelect2(ct, re); //
				}

			} else {
				Wrong();
			}

		}

	}

	public void seats1(UserC ct, reservationC re) throws IOException {

		while (true) {
			rv.SEATSave("seat1");

			if (hour == 00 && min == 00 && sec == 01) {
				seat1 = seat5;
			}

			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat1.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat1.length; a++) {
				System.out.println();
				for (int b = 0; b < seat1[a].length; b++) {
					if (seat1[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat1[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat1[i][j] = 1;
					rv.seatArraySave(seat1);
					System.out.println("���� �Ϸ�");

					ticket(ct, re);

				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat1[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}
		}
	}

	public void seats2(UserC ct, reservationC re) throws IOException {
		rv.SEATSave("seat2");
		while (true) {
			if (hour == 00 && min == 00 && sec == 01) {
				seat2 = seat6;
			}
			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat2.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat2.length; a++) {
				System.out.println();
				for (int b = 0; b < seat2[a].length; b++) {
					if (seat2[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat2[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat2[i][j] = 1;

					System.out.println("���� �Ϸ�");
					rv.seatArraySave(seat2);
					ticket(ct, re);

					// ���ȭ������ �����ؾ���
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat2[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}
		}
	}

	public void seats3(UserC ct, reservationC re) throws IOException {
		rv.SEATSave("seat3");
		while (true) {
			if (hour == 00 && min == 00 && sec == 01) {
				seat3 = seat7;
			}
			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat3.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat3.length; a++) {
				System.out.println();
				for (int b = 0; b < seat3[a].length; b++) {
					if (seat3[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat3[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat3[i][j] = 1;

					System.out.println("���� �Ϸ�");
					rv.seatArraySave(seat3);
					ticket(ct, re);

					// ���ȭ������ �����ؾ���
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat3[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}

		}
	}

	public void seats4(UserC ct, reservationC re) throws IOException {
		rv.SEATSave("seat4");
		while (true) {
			if (hour == 00 && min == 00 && sec == 01) {
				seat4 = seat8;
			}
			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat4.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat4.length; a++) {
				System.out.println();
				for (int b = 0; b < seat4[a].length; b++) {
					if (seat4[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat4[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat4[i][j] = 1;

					System.out.println("���� �Ϸ�");
					rv.seatArraySave(seat4);
					ticket(ct, re);

					// ���ȭ������ �����ؾ���
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat4[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}
		}
	}

	public void seats5(UserC ct, reservationC re) throws IOException {
		rv.SEATSave("seat5");
		while (true) {
			if (hour == 00 && min == 00 && sec == 02) {
				seat5 = new int[5][5];
			}

			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat5.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat5.length; a++) {
				System.out.println();
				for (int b = 0; b < seat5[a].length; b++) {
					if (seat5[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat5[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat5[i][j] = 1;

					System.out.println("���� �Ϸ�");
					rv.seatArraySave(seat5);
					ticket(ct, re);

					// ���ȭ������ �����ؾ���
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat5[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}
		}
	}

	public void seats6(UserC ct, reservationC re) throws IOException {
		rv.SEATSave("seat6");
		while (true) {
			if (hour == 00 && min == 00 && sec == 02) {
				seat6 = new int[5][5];
			}

			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat6.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat6.length; a++) {
				System.out.println();
				for (int b = 0; b < seat6[a].length; b++) {
					if (seat6[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat6[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat6[i][j] = 1;

					System.out.println("���� �Ϸ�");
					rv.seatArraySave(seat6);
					ticket(ct, re);

					// ���ȭ������ �����ؾ���
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat6[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}
		}
	}

	public void seats7(UserC ct, reservationC re) throws IOException {
		rv.SEATSave("seat7");
		while (true) {
			if (hour == 00 && min == 00 && sec == 02) {
				seat7 = new int[5][5];
			}

			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat7.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat7.length; a++) {
				System.out.println();
				for (int b = 0; b < seat7[a].length; b++) {
					if (seat7[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat7[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat7[i][j] = 1;

					System.out.println("���� �Ϸ�");
					rv.seatArraySave(seat7);
					ticket(ct, re);

					// ���ȭ������ �����ؾ���
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat7[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}
		}
	}

	public void seats8(UserC ct, reservationC re) throws IOException {
		rv.SEATSave("seat8");
		while (true) {
			if (hour == 00 && min == 00 && sec == 02) {
				seat8 = new int[5][5];
			}
			System.out.println("-----------��ũ��------------");
			System.out.println();
			for (int a = 0; a < seat8.length; a++) {
				System.out.print(" [" + (a + 1) + "] ");
			}
			for (int a = 0; a < seat8.length; a++) {
				System.out.println();
				for (int b = 0; b < seat8[a].length; b++) {
					if (seat8[a][b] != 1) {
						System.out.print("  O  ");
					} else {
						System.out.print("  X  ");
					}
				}
				System.out.println(" [" + (a + 1) + "] ");
			}
			System.out.print(" ");
			System.out.println("--------------------------");
			System.out.println("o ���� ���� �¼� ");
			System.out.println("x ���� �Ұ��� �¼�");
			System.out.println("--------------------------");
			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("���ϴ� �¼��� ���� �Է��Ͻÿ�");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat8[i][j] != 1) {
				System.out.printf("[%d]��[%d]�� �¼��� �����ϼ̽��ϴ�.", i + 1, j + 1);
				System.out.println("��ϵ� ī��� �����Ͻðڽ��ϱ�? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat8[i][j] = 1;

					System.out.println("���� �Ϸ�");
					rv.seatArraySave(seat8);
					ticket(ct, re);

					// ���ȭ������ �����ؾ���
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat8[i][j] == 1) {
				System.out.println("�̹� ����� �¼��Դϴ�.");
			} else {
				Wrong();
				continue;
			}
		}
	}

	public void ticket(UserC ct, reservationC re) throws IOException {
		FileSave2 save2 = new FileSave2();
		no++;
		rv.noSave(no);
		System.out.println("------------���ű�------------");
		System.out.println("���̵�: " + use.getid());
		System.out.println("���Ź�ȣ: " + rv.noOut());
		System.out.println("��¥: " + rv.dateOut());
		System.out.println("��ȭ: " + rv.movieOut());
		System.out.println("�ð�: " + rv.timeOut());
		System.out.println("�¼�: " + rv.seatAOut() + "�� " + rv.seatBOut() + "��");
		System.out.println("-----------------------------");
		System.out.println("��ſ� �����Ǽ���!");
		reservationM q = new reservationM();
		q.rsetAll(rv.noOut(), rv.SEATOut(), rv.dateOut(), rv.movieOut(), rv.timeOut(), rv.seatAOut(), rv.seatBOut(),
				use.getid(), rv.seatArrayOut());
		rv.setId(use.getid());
		re.insert(q);
		save2.rListWriter(re);
		memberprintMenu(ct, re);
	}

	public void ticketView(reservationC re) {
		try {
			if (re.getSize() == 0) {
				return;
			} else {
				for (int i = 0; i < re.getSize(); i++) {
					if (re.getLocate(i).getId().equals(this.id)) {
						System.out.println("------------���ű�------------");
						System.out.println("���̵�: " + re.getLocate(i).getId());
						System.out.println("���Ź�ȣ: " + re.getLocate(i).noOut());
						System.out.println("��¥: " + re.getLocate(i).dateOut());
						System.out.println("��ȭ: " + re.getLocate(i).movieOut());
						System.out.println("�ð�: " + re.getLocate(i).timeOut());
						System.out
								.println("�¼�: " + re.getLocate(i).seatAOut() + "�� " + re.getLocate(i).seatBOut() + "��");
						System.out.println("-----------------------------");
					}
				}
				System.out.println("----------�߰� ���� ����----------");
				System.out.println("Snack: " + ss.FoodOut());
				System.out.println("Juice: " + ss.JuiceOut());
				System.out.println("Goods: " + ss.GoodsOut());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean Rprompt(reservationC re) throws IOException {
		for (int i = 0; i < re.getSize(); i++)
			if (re.getLocate(i).getId().equals(id))
				rv = re.getLocate(i);
		return true;
	}

	/*public void seatclear(reservationC re) throws IOException {
		for (int i = 0; i < re.getSize(); i++) {
			if (re.getLocate(i).getId().equals(id) && re.getLocate(i).noOut().equals(no)) {
				int[][] z = re.getLocate(i).seatArrayOut();
				int a = Integer.parseInt(re.getLocate(i).seatAOut());
				int b = Integer.parseInt(re.getLocate(i).seatBOut());
				for(int j; j<z.length; j++) {
					if(z[a][b] == 1) {
					z[a][b] = 0;
					}
				}
			}
		}
	}*/
	
	/*public void ticketCancel(UserC ct, reservationC re) throws IOException {
	      FileSave2 save2 = new FileSave2(); // save2�� ���ϼ��̺����� ��ü
	      ticketView(re);
	      System.out.println("������� ���ű��� ���Ź�ȣ�� �����ϼ���.��");
	      System.out.print(">> ");
	      int sel = sc.nextInt();
	      for (int i = 0; i < re.getSize(); i++) {
	         if (re.getLocate(i).getId().equals(id) && re.getLocate(i).noOut() == sel) {
	            
	            int[][] js = re.getLocate(i).seatArrayOut();
	            
	            int a = Integer.parseInt(re.getLocate(i).seatAOut());
	            int b = Integer.parseInt(re.getLocate(i).seatAOut());
	            System.out.println(js[a][b]);
	            js[a][b] =0;
	            rv.seatArraySave(js);
	            re.remove(re.getLocate(i));
	            save2.rListWriter(re);
	         }
	      }
	   
	      ticketView(re);
	      System.out.println("�Ѽ����Ͻ� ���ű��� �����Ǿ����ϴ�.��");
	      System.out.println("��ȸ�� ȭ������ ���ư��ϴ�.��");
	      memberprintMenu(ct, re);
	   }*/
	
	public void ticketCancel(UserC ct, reservationC re) throws IOException {
		FileSave2 save2 = new FileSave2(); // save2�� ���ϼ��̺����� ��ü
		ticketView(re);
		System.out.println("������� ���ű��� ���Ź�ȣ�� �����ϼ���.��");
		System.out.print(">> ");
		int sel = sc.nextInt();
		for (int i = 0; i < re.getSize(); i++) {
			if (re.getLocate(i).getId().equals(id) && re.getLocate(i).noOut() == sel) {
				re.remove(re.getLocate(i));
			}
		}
		//seatclear(re);
		save2.rListWriter(re);
		ticketView(re);
		System.out.println("�Ѽ����Ͻ� ���ű��� �����Ǿ����ϴ�.��");
		System.out.println("��ȸ�� ȭ������ ���ư��ϴ�.��");
		memberprintMenu(ct, re);
	}

//��	
}
//��