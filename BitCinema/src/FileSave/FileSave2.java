package FileSave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import Controller.reservationC;

public class FileSave2 {
	public void rListWriter(reservationC rlist) throws IOException {

		OutputStream os = new FileOutputStream("rlist.rlist");
		ObjectOutputStream out = new ObjectOutputStream(os);

		out.writeObject(rlist);

		out.close();
	}

	public reservationC rListReader() throws IOException, ClassNotFoundException {

		File e = new File("rlist.rlist");
		if (e.isFile()) {
			InputStream is = new FileInputStream("rlist.rlist");
			ObjectInputStream in = new ObjectInputStream(is);

			reservationC rlist = (reservationC) in.readObject();
			in.close();

			return rlist;
		} else
			return null;

	}
}
