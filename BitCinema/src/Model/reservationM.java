package Model;

import java.io.Serializable;
import java.util.Calendar;

public class reservationM implements Serializable {

	int no;
	String SEAT;
	String date;
	String movie;
	String time;
	String seatA;
	String seatB;
	String id;
	int[][] seatArray;

	public reservationM() {
		no = 0;
		SEAT = "";
		date = "";
		movie = "";
		time = "";
		seatA = "";
		seatB = "";
		id = "";
	}

	public reservationM(int no, String SEAT, String date, String movie, String time, String seatA, String seatB,
			String id, int[][] seatArray) {
		this.no = no;
		this.SEAT = SEAT;
		this.date = date;
		this.movie = movie;
		this.time = time;
		this.seatA = seatA;
		this.seatB = seatB;
		this.id = id;
		this.seatArray = seatArray;
	}

	public void rsetAll(int no, String SEAT, String date, String movie, String time, String seatA, String seatB,
			String id, int[][] seatArray) {
		this.no = no;
		this.SEAT = SEAT;
		this.date = date;
		this.movie = movie;
		this.time = time;
		this.seatA = seatA;
		this.seatB = seatB;
		this.id = id;
		this.seatArray = seatArray;
	}

	public void seatArraySave(int[][] seatArray) {
		this.seatArray = seatArray;
	}
	
	public int[][] seatArrayOut() {
		return seatArray;
	}
	
	public void noSave(int no) {
		this.no = no;
	}

	public void SEATSave(String SEAT) {
		this.SEAT = SEAT;
	}

	public String getId() {
		return id;
	}

	public void dateSave(String date) {
		this.date = date;

	}

	public void movieSave(String movie) {
		this.movie = movie;
	}

	public void timeSave(String time) {
		this.time = time;
	}

	public void seatASave(String seatA) {
		this.seatA = seatA;
	}

	public void seatBSave(String seatB) {
		this.seatB = seatB;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String SEATOut() {
		return SEAT;
	}

	public int noOut() {
		return no;
	}

	public String timeOut() {
		return time;
	}

	public String movieOut() {
		return movie;
	}

	public String dateOut() {
		return date;
	}

	public String seatAOut() {
		return seatA;
	}

	public String seatBOut() {
		return seatB;
	}
}
