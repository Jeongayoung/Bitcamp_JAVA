package View;

import java.io.*;
import java.util.*;

import Controller.UserC;
import Controller.reservationC;
import FileSave.FileSave;
import FileSave.FileSave2;
import Model.UserM;
import Model.reservationM;

public class MovieMain {

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		View view = new View(); // 보여지기위한 클래스 호출 MVC패턴중 V에해당
		
		FileSave save = new FileSave();// 파일을 저장할수 있는 클래스
		ArrayList<UserM> clist = new ArrayList<UserM>();
		ArrayList<reservationM> rlist = new ArrayList<reservationM>();
		UserC ct = save.cListReader();// 이미 파일이 있을경우 파일을 불러와서 실행
		if (ct == null) {
			ct = new UserC(clist);
			save.cListWriter(ct);
		}
		
		FileSave2 save2 = new FileSave2();
		reservationC re = save2.rListReader();// 이미 파일이 있을경우 파일을 불러와서 실행
		if (re == null) {
			re = new reservationC(rlist);
			save2.rListWriter(re);
		}

		view.mainView(ct, re);// 메인 루프 실행

	}

}
