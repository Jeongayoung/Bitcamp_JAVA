package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import Model.UserM;

public class UserC implements Serializable {

	public ArrayList<UserM> ct;
	
	public UserC(ArrayList<UserM> list) {
		this.ct = list;
	}
	
	public void insert(UserM a) throws IOException {
		ct.add(a);
	}
	
	public void remove(UserM a) throws IOException {
		ct.remove(a);
	}

	public int getSize() throws IOException {
		return ct.size();
	}

	public UserM getLocate(int num) throws IOException { // 객체에 있는 index번호를 찾는 메소드
		return ct.get(num);
	}

}
