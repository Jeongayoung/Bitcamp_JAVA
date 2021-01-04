package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import Model.reservationM;

public class reservationC implements Serializable {

	public ArrayList<reservationM> re;

	public reservationC(ArrayList<reservationM> list) {
		this.re = list;
	}

	public void insert(reservationM a) {
		re.add(a);
	}

	public void remove(reservationM a) {
		re.remove(a);
	}

	public int getSize() throws IOException {
		return re.size();
	}

	public reservationM getLocate(int num) throws IOException { // ��ü�� �ִ� index��ȣ�� ã�� �޼ҵ�
		return re.get(num);
	}

}
