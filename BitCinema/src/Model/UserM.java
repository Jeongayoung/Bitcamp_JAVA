package Model;

import java.io.Serializable;

public class UserM implements Serializable {

	String name, phone, joomin, id, password, cardName, cardN;

	public UserM() {
		name = "";
		phone = "";
		joomin = "";
		id = "";
		password = "";
		cardName = "";
		cardN = "";
	}

public UserM(String name, String phone, String joomin, String id, String password, String cardName, String cardN) {
		this.name = name;
		this.phone = phone;
		this.joomin = joomin;
		this.id = id;
		this.password = password;
		this.cardName = cardName;
		this.cardN = cardN;
	}

public void msetAll(String name, String phone, String joomin, String id, String password, String cardName,
			String cardN) {
		this.name = name;
		this.phone = phone;
		this.joomin = joomin;
		this.id = id;
		this.password = password;
		this.cardName = cardName;
		this.cardN = cardN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getphone() {
		return phone;
	}

	public void setjphone(String phone) {
		this.phone = phone;
	}

	public String getjoomin() {
		return joomin;
	}

	public void setjoomin(String joomin) {
		this.joomin = joomin;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getpassword() {
		return password;
	}

	public void setpassowrd(String password) {
		this.password = password;
	}

	public String getcardName() {
		return cardName;
	}

	public void setcardName(String cardName) {
		this.cardName = cardName;
	}

	public String getcardN() {
		return cardN;
	}

	public void setcardN(String cardN) {
		this.cardN = cardN;
	}

}
