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

		View view = new View(); // ������������ Ŭ���� ȣ�� MVC������ V���ش�
		
		FileSave save = new FileSave();// ������ �����Ҽ� �ִ� Ŭ����
		ArrayList<UserM> clist = new ArrayList<UserM>();
		ArrayList<reservationM> rlist = new ArrayList<reservationM>();
		UserC ct = save.cListReader();// �̹� ������ ������� ������ �ҷ��ͼ� ����
		if (ct == null) {
			ct = new UserC(clist);
			save.cListWriter(ct);
		}
		
		FileSave2 save2 = new FileSave2();
		reservationC re = save2.rListReader();// �̹� ������ ������� ������ �ҷ��ͼ� ����
		if (re == null) {
			re = new reservationC(rlist);
			save2.rListWriter(re);
		}

		view.mainView(ct, re);// ���� ���� ����

	}

}
