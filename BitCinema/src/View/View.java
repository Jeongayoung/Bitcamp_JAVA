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

	String[] sFood = { "선택하지않음", "카라멜 팝콘", "플레인 핫도그", "버터 오징어 구이", "칠리치즈 나쵸" };
	int[] lfPrice = { 0, 5500, 4000, 5000, 3500 };
	String[] sJuice = { "선택하지않음", "탄산음료", "아메리카노", "레몬 에이드", "복숭아 아이스티" };
	int[] ljPrice = { 0, 1500, 2000, 2500, 3500, 2500 };
	String[] sGoods = { "선택하지 않음", "스파이더맨 담요", "쥬디 당근펜", "해리포터 지팡이", "미키마우스 티셔츠" };
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
		System.out.println("종료되었습니다.");
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
						System.out.println("이미 존재하는 아이디 입니다.");
						System.out.println("다른아이디를 입력해주세요");
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
				System.out.println("잘못 입력되었습니다.");
				break;
			}
		}
	}

	// 홈메뉴
	public void homeprintMenu() {
		System.out.println();
		System.out.println("	      >>안녕하세요, 비트시네마입니다.	 ");
		System.out.println("	    >>원하시는 서비스 번호를 선택해주세요!	");
		System.out.println();
		System.out.println("     ┌──────────────────────────────────────┐");
		System.out.println("     │        =====<Home Menu>=====         │");
		System.out.println("     │                                      │");
		System.out.println("     │             [1. 회원가입]              │");
		System.out.println("     │             [2. 로 그 인 ]              │");
		System.out.println("     │             [3. 상영영화]              │");
		System.out.println("     │             [4. 스 낵 바 ]              │");
		System.out.println("     │             [5. 종료하기]              │");
		System.out.println("     │                                      │");
		System.out.println("     └──────────────────────────────────────┘");
		System.out.print(">> ");
	}

	public void UInsert(UserC ct) throws IOException {
		FileSave save = new FileSave();
		System.out.println("<< 회원가입 >>");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("핸드폰번호 : ");
		String phone = sc.next();
		System.out.print("주민번호: ");
		String joomin = sc.next();
		System.out.print("아이디 : ");
		String id = idchk(ct);
		System.out.print("비밀번호 : ");
		String password = sc.next();
		System.out.print("카드이름 : ");
		String cardName = sc.next();
		System.out.print("카드번호 : ");
		String cardN = sc.next();
		UserM i = new UserM();
		i.msetAll(name, phone, joomin, id, password, cardName, cardN);// 이렇게 하면 view에서 control로 거치지 않고 바로 모델로 감.
		ct.insert(i); // list에 입력받은 값을 객체를 저장
		save.cListWriter(ct);
		System.out.println("ㅡ회원가입이완료 되었습니다.ㅡ");
	}

	// 회원 탈퇴
	public void uRemove(UserC ct) throws IOException {
		FileSave save = new FileSave();
		System.out.println("탈퇴아이디");
		String a = sc.next();
		for(int i=0; i<ct.getSize(); i++) {
			if(ct.getLocate(i).getid().equals(id) && id == a) {
				System.out.println("정말 삭제?yn");
				String ans = sc.next();
				if(ans.toLowerCase().trim().equals("y")) {
					ct.remove(ct.getLocate(i));
					System.out.println("탈퇴가 완료되었습니다.");
					save.cListWriter(ct);
					homeprintMenu();
				} else {
					System.out.println("회원X");
				}
			} else {
				System.out.println("니아이디랑삭제하려는아이디가다름");
				break;
			}
		}
	}
	
	// 로그인
	public boolean login(UserC ct) throws IOException {
		System.out.println("아이디를 입력하세요");
		String id = sc.next();
		System.out.println("비밀번호를 입력하세요.");
		String password = sc.next();
		for (int i = 0; i < ct.getSize(); i++) {
			if (ct.getLocate(i).getid().equals(id) && ct.getLocate(i).getpassword().equals(password)) {
				System.out.println("로그인 성공!!");
				this.id = id;
				this.password = password;
				return true;
			}
		}
		System.out.println("로그인 실패 ");
		return false;
	}

	// 로그아웃
	public void logout(UserC ct, reservationC re) throws IOException {
		id = null;
		password = null;
		System.out.println("로그아웃 성공!");
		mainView(ct, re);
	}

	// 회원전용메뉴
	public void memberprintMenu(UserC ct, reservationC re) throws IOException {
		if (prompt(ct) == true) {
			System.out.println(">> [" + use.getName() + "]님 환영합니다.");
			System.out.println(">> 원하시는 서비스 번호를 선택해주세요!");
			System.out.println();
			System.out.println("     ┌──────────────────────────────────────┐");
			System.out.println("     │        =====<Member Menu>=====       │");
			System.out.println("     │                                      │");
			System.out.println("     │             [1. 예매버튼 ]	            │");
			System.out.println("     │             [2. 예매취소 ]	            │");
			System.out.println("     │             [3. 매점구매 ]	            │");
			System.out.println("     │             [4. MY 정보  ]	            │");
			System.out.println("     │             [5. 회원탈퇴 ]	            │");
			System.out.println("     │             [6. 로그아웃 ]	            │");
			System.out.println("     │            [7. 프로그램 종료 ]            │");
			System.out.println("     │                                      │");
			System.out.println("     └──────────────────────────────────────┘");
			System.out.print(">> ");
			memberSelect(ct, re);
		}
	}

	// 회원메뉴 선택메소드
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
			System.out.println("잘못 입력되었습니다.");
			return;
		}
	}

	// 내 정보 출력
	public void myInfo(UserC ct, reservationC re) throws IOException {
		while (true) {
			if (prompt(ct) == true) {
				System.out.println("=====<내 정보>=====");
				System.out.println("[" + use.getName() + "]님의 정보입니다.");
				System.out.println("ID: " + use.getid());
				System.out.println("Password: " + use.getpassword());
				System.out.println("이름: " + use.getName());
				System.out.println("폰번호: " + use.getphone());
				System.out.println("카드이름: " + use.getcardName());
				System.out.println("예매 내역: ");
				System.out.println(">> ");
				ticketView(re);
				System.out.println("=================");
			}
			memberprintMenu(ct, re);
		}
	}

	// 상영중인 영화입니다.
	public void movieinFo() {
		System.out.println("<<상영 중인 영화 정보>>");
		while (flag) {
			System.out.println();
			System.out.println("             [상영중 영화 리스트]");
			System.out.println();
			System.out.println("     ┌────────────────────────────────────────────────────────────────┐");
			System.out.println("     │    1. 반도  | 2. 알라딘  | 3. 파리의 인어  |  4. 밤쉘 -세상을 바꾼 폭탄선언       │");
			System.out.println("     └────────────────────────────────────────────────────────────────┘");
			System.out.println("         ┌────────────────────┐");
			System.out.println("         │     0.메인으로 가기       │");
			System.out.println("         └────────────────────┘");

			System.out.println(">>정보확인을 원하는 영화 번호를 입력해주세요.");
			System.out.println();
			System.out.println("                ");
			movieSelect();
		}
	}

	// 영화 선택문
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
			System.out.println(">>잘못 입력하셨습니다.");
		}
	}

	// 영화1
	public void MovieInfo1() {

		this.title = "반도";
		this.director = "연상호";
		this.actor = "강동원, 이정현";
		this.grade = "15세 이상";
		this.genre = "액션, 드라마";
		this.summary = "전대미문의 재난 그 후 4년\r\n" + "폐허의 땅으로 다시 들어간다!\r\n" + "\r\n"
				+ "4년 전, 나라 전체를 휩쓸어버린 전대미문의 재난에서 가까스로 탈출했던 ‘정석’(강동원).\r\n"
				+ "바깥세상으로부터 철저히 고립된 반도에 다시 들어가야 하는 피할 수 없는 제안을 받는다.\r\n" + "\r\n"
				+ "제한 시간 내에 지정된 트럭을 확보해 반도를 빠져 나와야 하는 미션을 수행하던 중\r\n"
				+ "인간성을 상실한 631부대와 4년 전보다 더욱 거세진 대규모 좀비 무리가 정석 일행을 습격한다.\r\n" + "\r\n" + "절체절명의 순간,\r\n"
				+ "폐허가 된 땅에서 살아남은 ‘민정’(이정현) 가족의 도움으로 위기를 가까스로 모면하고\r\n" + "이들과 함께 반도를 탈출할 수 있는 마지막 기회를 잡기로 한다.\r\n"
				+ "\r\n" + "되돌아온 자, 살아남은 자 그리고 미쳐버린 자\r\n" + "필사의 사투가 시작된다!";

		System.out.println("제목    | " + title + "\n" + "감독    | " + director + "\n" + "등급    | " + grade + "\n"
				+ "장르    | " + genre + "\n" + "줄거리 | " + "\n" + "\n" + summary + "\n");
	}

	// 영화2
	public void MovieInfo2() {

		this.title = "알라딘";
		this.director = "가이 리치";
		this.actor = "메나 마수드 ,  윌 스미스 ,  나오미 스콧";
		this.grade = "전체 관람가";
		this.genre = " 어드벤처, 가족, 환타지";
		this.summary = "머나먼 사막 속 신비의 아그라바 왕국의 시대.\r\n" + "좀도둑 ‘알라딘’은 마법사 ‘자파’의 의뢰로 마법 램프를 찾아 나섰다가\r\n"
				+ "주인에게 세 가지 소원을 들어주는 지니를 만나게 되고,\r\n" + "자스민 공주의 마음을 얻으려다 생각도 못했던 모험에 휘말리게 되는데…";

		System.out.println("제목    | " + title + "\n" + "감독    | " + director + "\n" + "등급    | " + grade + "\n"
				+ "장르    | " + genre + "\n" + "줄거리 | " + "\n" + "\n" + summary + "\n");
	}

	// 영화3
	public void MovieInfo3() {

		this.title = "파리의 인어";
		this.director = "마티아스 말지우";
		this.actor = "마릴린 리마 , 니콜라스 뒤보셸";
		this.grade = "12세 이상";
		this.genre = "로맨스, 멜로, 환타지";
		this.summary = "낭만으로 빛나는 파리의 밤,\r\n" + "세느 강변에 ‘인어’의 노래가 울려 퍼진다.\r\n" + "\r\n" + "“나는 심장이 터져버렸어요.”\r\n"
				+ "더 이상 사랑은 없다고 믿는 남자 ‘가스파르’는\r\n" + "세상에 남은 마지막 인어 ‘룰라’를 만난다.\r\n"
				+ "그리고, 그녀를 사랑하는 남자는 모두 죽게 된다는 사실을 알게 되는데…\r\n" + "\r\n" + "“당신이, 나를 사랑하지 않아서 기뻐요.”\r\n"
				+ "사랑이 시작되는 도시 파리, \r\n" + "사랑하면 안되는 운명으로 만난 ‘인간’과 ‘인어’의 \r\n" + "꿈같은 로맨스가 펼쳐진다!";

		System.out.println("제목    | " + title + "\n" + "감독    | " + director + "\n" + "등급    | " + grade + "\n"
				+ "장르    | " + genre + "\n" + "줄거리 | " + "\n" + "\n" + summary + "\n");
	}

	// 영화4
	public void MovieInfo4() {

		this.title = "밤쉘-세상을 바꾼 폭탄선언";
		this.director = "제이 로치";
		this.actor = "샤를리즈 테론 ,  니콜 키드먼 ,  마고 로비";
		this.grade = "15세 이상";
		this.genre = "드라마";
		this.summary = "판단은 날카롭게, 외침은 당당하게, 행동은 과감하게!\r\n" + "\r\n" + "대선후보 토론회에서 트럼프와 설전을 벌인\r\n"
				+ "폭스뉴스의 간판 앵커 메긴 켈리(샤를리즈 테론)는\r\n" + "트럼프의 계속되는 트위터 공격으로 화제의 중심에 선다.\r\n" + "\r\n"
				+ "한편, 동료 앵커인 그레천 칼슨(니콜 키드먼)은\r\n" + "‘언론 권력의 제왕’이라 불리는 폭스뉴스 회장을 고소하고\r\n"
				+ "이에 메긴은 물론, 야심 있는 폭스의 뉴페이스\r\n" + "케일라 포스피실(마고 로비) 역시 충격을 감추지 못하는데… \r\n" + "\r\n"
				+ "최대 권력을 날려버릴 폭탄선언\r\n" + "이제 이들의 통쾌하고 짜릿한 역전극이 시작된다!";

		System.out.println("제목    | " + title + "\n" + "감독    | " + director + "\n" + "등급    | " + grade + "\n"
				+ "장르    | " + genre + "\n" + "줄거리 | " + "\n" + "\n" + summary + "\n");
	}

	// 매점메뉴 출력
	public void snackbarList() {
		System.out.println("-----♡비트시네마 매점♡-----");
		// 푸드
		System.out.println("<간식>");
		for (int idx = 1; idx < this.sFood.length; idx++) {

			System.out.print(idx + "." + this.sFood[idx] + " " + this.lfPrice[idx] + "원  ");
			System.out.println();
		}
		// 음료
		System.out.println("<음료>");
		for (int idx = 1; idx < this.sJuice.length; idx++) {
			System.out.print(idx + "." + this.sJuice[idx] + " " + this.ljPrice[idx] + "원  ");
			System.out.println();
		}

		// 굿즈
		System.out.println("<상품>");
		for (int idx = 1; idx < this.sGoods.length; idx++) {
			System.out.print(idx + "." + this.sGoods[idx] + " " + this.ldPrice[idx] + "원  ");
			System.out.println();
		}
		System.out.println("");
		System.out.println("☆★로그인 후 구매해주세요★☆");
		System.out.println("");
	}

	// return 푸드
	public int add_food() {
		addflag = true;
		return this.order_food();
	}

	// return 쥬스
	public int add_juice() {
		addflag = true;
		return this.order_juice();
	}

	// return 굿즈
	public int add_goods() {
		addflag = true;
		return this.order_goods();
	}

	// 총얼마입니다
	public void price_display() {
		System.out.println("   ***  총액 : " + this.total + "원이 나왔습니다     ***");
	}

	public void mainOrder(UserC ct, reservationC re) throws IOException {
		boolean brunning = true;
		int menu = 1;
		while (brunning) {
			menu = intro();
			if (menu == 1) { // 주문하기
				order_food();
				order_juice();
				order_goods();
				price_display();
				cardNum(ct, re);
			} else if (menu == 2) { // 계산하고 나가기
				brunning = false;
			}
		}
	}

	public int intro() {
		int menu = 0;
		System.out.println("   ***     Snack Bar      ***");
		System.out.println();
		System.out.println("     ┌──────────────────────────────────────┐");
		System.out.println("     │      [1. 주문하기]    [2. 뒤로가기]       │");
		System.out.println("     └──────────────────────────────────────┘");
		System.out.println();
		System.out.print("   메뉴를 선택해주세요 : ");
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
					System.out.print(idx + "." + this.sFood[idx] + "  ------  " + this.lfPrice[idx] + "원  ");
				}
			}

			System.out.println();
			System.out.println();
			System.out.print("   음식을 선택해주세요 : ");

			try {

				num = sc.nextInt();
				System.out.println();
				System.out.println();
				System.out.println("*** " + sFood[num] + "의 주문이 완료되었습니다.");
				System.out.println();
				this.total += this.lfPrice[num];
				break;

			} catch (Exception e) {// e.printStackTrace();
				System.out.println();
				System.out.println("   잘못입력하셨습니다.");
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
					System.out.print(idx + "." + this.sJuice[idx] + "  ------  " + this.ljPrice[idx] + "원  ");
				}
			}
			System.out.println();
			System.out.println();
			System.out.print("   음료를 선택해주세요 : ");

			try {
				num = sc.nextInt();
				System.out.println();
				System.out.println("*** " + sJuice[num] + "의 주문이 완료되었습니다.");
				System.out.println();
				System.out.println("============================================");
				System.out.println();
				this.total += this.ljPrice[num];
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("   잘못입력하셨습니다.");
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
					System.out.print(idx + "." + this.sGoods[idx] + "  ------  " + this.ldPrice[idx] + "원  ");
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.print("   굿즈를 선택해주세요 : ");

			try {

				num = sc.nextInt();
				sc.nextLine();
				System.out.println();
				System.out.println("*** " + sGoods[num] + "의 주문이 완료되었습니다.");
				System.out.println();
				System.out.println("============================================");
				System.out.println();
				this.total += this.ldPrice[num];
				break;

			} catch (Exception e) {

				System.out.println();
				System.out.println("   잘못입력하셨습니다.");
				System.out.println();
			}

		}
		ss.GoodsSave(sGoods[num]);
		sSave.add(ss); //
		return num;
	}

	public void cardNum(UserC ct, reservationC re) throws IOException {
		System.out.println("     ┌───────────────────────────────┐");
		System.out.println("     │    등록된 카드로 결제하시겠습니까?  	 │");
		System.out.println("     │         (1.Yes / 2.No)        │");
		System.out.println("     └───────────────────────────────┘");
		System.out.print(">> ");
		int selectNum = sc.nextInt();
		switch (selectNum) {
		case 1:
			System.out.println();
			System.out.println("	결제 완료되었습니다. 감사합니다.");
			System.out.println("	처음 화면으로 돌아갑니다.");
			System.out.println();
			memberprintMenu(ct, re);
		case 2:
			System.out.println();
			System.out.println("	틀린 카드 번호입니다. 결제가  취소됐습니다.");
			System.out.println("	처음으로 돌아갑니다.");
			System.out.println();
			memberprintMenu(ct, re);
		default:
			System.out.println("	잘못 입력하셨습니다.");
			System.out.println("	처음으로 돌아갑니다.");
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
			System.out.println("원하는 날짜를 선택하세요.");
			System.out.println("-----------------------------");
			System.out.println("1. " + getToday("yyyy-MM-dd"));
			System.out.println("2. " + getTomorrow("yyyy-MM-dd"));
			System.out.println(" "); //
			System.out.println("3. [메인으로 돌아가기]"); // 추가
			System.out.println("-----------------------------");
			dinput = sc.nextInt();

			switch (dinput) {
			case 1:
				rv.dateSave(getToday("yyyy-MM-dd"));
				System.out.println("-----------------------------"); // 추가
				System.out.println("1." + getToday("yyyy-MM-dd") + "를 선택하셨습니다."); // 추가
				movieSelect1(ct, re);
				break;

			case 2:
				rv.dateSave(getTomorrow("yyyy-MM-dd"));
				System.out.println("-----------------------------"); // 추가
				System.out.println("2." + getTomorrow("yyyy-MM-dd") + "를 선택하셨습니다."); // 추가
				movieSelect2(ct, re);
				break;

			case 3:
				memberprintMenu(ct, re);
				break;

			default:
				System.out.println("잘못 입력하셨습니다.");
				continue; // 컨티뉴 추가
			}
		}
	}

	public void movieSelect1(UserC ct, reservationC re) throws IOException {
		movieList.add("알라딘");
		movieList.add("파리의 인어");
		movieBasic(ct, re);
	}

	public void movieSelect2(UserC ct, reservationC re) throws IOException {
		movieList.add("반도");
		movieList.add("밤쉘");
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
		System.out.println("잘못 입력하셨습니다.");
	}

	public void movieBasic(UserC ct, reservationC re) throws IOException {
		while (true) {
			System.out.println("-----------------------------");
			for (int i = 0; i < movieList.size(); i++) {
				System.out.println((i + 1) + ". " + movieList.get(i));
			}
			System.out.println(" "); //
			System.out.println("3. [뒤로 가기]"); // 뒤로가기 선택지 추가
			System.out.println("-----------------------------");
			System.out.println("원하는 영화를 선택하세요.");
			minput = sc.nextInt();
			switch (minput) {
			case 1:
				rv.movieSave(movieList.get(0));
				System.out.println("-----------------------------"); // 추가
				System.out.println((movieList.get(0)) + "을 선택하셨습니다."); // 추가
				movieList.clear();
				timeSelect1(ct, re);
				break;
			case 2:
				rv.movieSave(movieList.get(1));
				System.out.println("-----------------------------"); // 추가
				System.out.println((movieList.get(1)) + "을 선택하셨습니다."); // 추가
				movieList.clear();
				timeSelect2(ct, re);
				break;
			case 3: // 뒤로가기 선택지 추가
				movieList.clear(); //
				select(ct, re); //
				break; //

			default:
				System.out.println("잘못 입력하셨습니다.");
				continue; // 컨티뉴 추가
			}
		}

	}

	public void timeBasic(UserC ct, reservationC re) throws IOException {
		while (true) {
			System.out.println("-----------------------------");
			for (int i = 0; i < timeList.size(); i++) {
				System.out.println((i + 1) + ". " + timeList.get(i));
			}
			System.out.println(" "); // 추가
			System.out.println("3. [뒤로 가기]"); // 추가
			System.out.println("-----------------------------");
			System.out.println("원하는 회차를 선택하세요.");
			tinput = sc.nextInt();
			if (dinput == 1 && minput == 1 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				System.out.println("-----------------------------"); // 추가
				System.out.println((timeList.get(0)) + "을 선택하셨습니다."); // 추가
				timeList.clear();
				seats1(ct, re);
			} else if (dinput == 1 && minput == 1 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // 추가
				System.out.println((timeList.get(1)) + "을 선택하셨습니다."); // 추가
				timeList.clear();
				seats2(ct, re);
			} else if (dinput == 1 && minput == 2 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				timeList.clear();
				seats3(ct, re);
			} else if (dinput == 1 && minput == 2 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // 추가
				System.out.println((timeList.get(1)) + "을 선택하셨습니다."); // 추가
				timeList.clear();
				seats4(ct, re);
			} else if (dinput == 2 && minput == 1 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				System.out.println("-----------------------------"); // 추가
				System.out.println((timeList.get(0)) + "을 선택하셨습니다."); // 추가
				timeList.clear();
				seats5(ct, re);
			} else if (dinput == 2 && minput == 1 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // 추가
				System.out.println((timeList.get(1)) + "을 선택하셨습니다."); // 추가
				timeList.clear();
				seats6(ct, re);
			} else if (dinput == 2 && minput == 2 && tinput == 1) {
				rv.timeSave(timeList.get(0));
				System.out.println("-----------------------------"); // 추가
				System.out.println((timeList.get(0)) + "을 선택하셨습니다."); // 추가
				timeList.clear();
				seats7(ct, re);
			} else if (dinput == 2 && minput == 2 && tinput == 2) {
				rv.timeSave(timeList.get(1));
				System.out.println("-----------------------------"); // 추가
				System.out.println((timeList.get(1)) + "을 선택하셨습니다."); // 추가
				timeList.clear();
				seats8(ct, re);
			} else if (tinput == 3) { //
				timeList.clear(); //
				if (dinput == 1) { // 뒤로가기 추가
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

			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat1[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat1[i][j] = 1;
					rv.seatArraySave(seat1);
					System.out.println("예매 완료");

					ticket(ct, re);

				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat1[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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
			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat2[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat2[i][j] = 1;

					System.out.println("예매 완료");
					rv.seatArraySave(seat2);
					ticket(ct, re);

					// 멤버화면으로 가게해야함
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat2[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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
			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat3[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat3[i][j] = 1;

					System.out.println("예매 완료");
					rv.seatArraySave(seat3);
					ticket(ct, re);

					// 멤버화면으로 가게해야함
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat3[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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
			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat4[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat4[i][j] = 1;

					System.out.println("예매 완료");
					rv.seatArraySave(seat4);
					ticket(ct, re);

					// 멤버화면으로 가게해야함
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat4[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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

			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat5[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat5[i][j] = 1;

					System.out.println("예매 완료");
					rv.seatArraySave(seat5);
					ticket(ct, re);

					// 멤버화면으로 가게해야함
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat5[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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

			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat6[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat6[i][j] = 1;

					System.out.println("예매 완료");
					rv.seatArraySave(seat6);
					ticket(ct, re);

					// 멤버화면으로 가게해야함
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat6[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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

			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat7[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat7[i][j] = 1;

					System.out.println("예매 완료");
					rv.seatArraySave(seat7);
					ticket(ct, re);

					// 멤버화면으로 가게해야함
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat7[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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
			System.out.println("-----------스크린------------");
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
			System.out.println("o 예매 가능 좌석 ");
			System.out.println("x 예매 불가능 좌석");
			System.out.println("--------------------------");
			System.out.println("원하는 좌석의 행을 입력하시오");
			int i = sc.nextInt();
			rv.seatASave(Integer.toString(i));
			i = i - 1;
			if (i < 0 || i > 5) {
				Wrong();
				continue;
			}

			System.out.println("원하는 좌석의 열을 입력하시오");
			int j = sc.nextInt();
			rv.seatBSave(Integer.toString(j));
			j = j - 1;
			if (j < 0 || j > 5) {
				Wrong();
				continue;
			}
			if (seat8[i][j] != 1) {
				System.out.printf("[%d]행[%d]열 좌석을 선택하셨습니다.", i + 1, j + 1);
				System.out.println("등록된 카드로 예매하시겠습니까? y/n");
				String yes = sc.next();
				yes = yes.toUpperCase();
				if (yes.equals("Y")) {
					seat8[i][j] = 1;

					System.out.println("예매 완료");
					rv.seatArraySave(seat8);
					ticket(ct, re);

					// 멤버화면으로 가게해야함
				} else if (yes.equals("n")) {
				} else {
					Wrong();
					continue;
				}
			} else if (seat8[i][j] == 1) {
				System.out.println("이미 예약된 좌석입니다.");
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
		System.out.println("------------예매권------------");
		System.out.println("아이디: " + use.getid());
		System.out.println("예매번호: " + rv.noOut());
		System.out.println("날짜: " + rv.dateOut());
		System.out.println("영화: " + rv.movieOut());
		System.out.println("시간: " + rv.timeOut());
		System.out.println("좌석: " + rv.seatAOut() + "행 " + rv.seatBOut() + "열");
		System.out.println("-----------------------------");
		System.out.println("즐거운 관람되세요!");
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
						System.out.println("------------예매권------------");
						System.out.println("아이디: " + re.getLocate(i).getId());
						System.out.println("예매번호: " + re.getLocate(i).noOut());
						System.out.println("날짜: " + re.getLocate(i).dateOut());
						System.out.println("영화: " + re.getLocate(i).movieOut());
						System.out.println("시간: " + re.getLocate(i).timeOut());
						System.out
								.println("좌석: " + re.getLocate(i).seatAOut() + "행 " + re.getLocate(i).seatBOut() + "열");
						System.out.println("-----------------------------");
					}
				}
				System.out.println("----------추가 구매 내역----------");
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
	      FileSave2 save2 = new FileSave2(); // save2는 파일세이브투의 객체
	      ticketView(re);
	      System.out.println("ㅡ취소할 예매권의 예매번호를 선택하세요.ㅡ");
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
	      System.out.println("ㅡ선택하신 예매권이 삭제되었습니다.ㅡ");
	      System.out.println("ㅡ회원 화면으로 돌아갑니다.ㅡ");
	      memberprintMenu(ct, re);
	   }*/
	
	public void ticketCancel(UserC ct, reservationC re) throws IOException {
		FileSave2 save2 = new FileSave2(); // save2는 파일세이브투의 객체
		ticketView(re);
		System.out.println("ㅡ취소할 예매권의 예매번호를 선택하세요.ㅡ");
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
		System.out.println("ㅡ선택하신 예매권이 삭제되었습니다.ㅡ");
		System.out.println("ㅡ회원 화면으로 돌아갑니다.ㅡ");
		memberprintMenu(ct, re);
	}

//끝	
}
//끝