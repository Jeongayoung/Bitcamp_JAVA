package FileSave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import Controller.UserC;

public class FileSave {
	public void cListWriter(UserC clist) throws IOException {

		OutputStream os = new FileOutputStream("clist.clist");
		ObjectOutputStream out = new ObjectOutputStream(os);

		out.writeObject(clist);

		out.close();
	}

	public UserC cListReader() throws IOException, ClassNotFoundException {

		File f = new File("clist.clist");
		if (f.isFile()) {
			InputStream is = new FileInputStream("clist.clist");
			ObjectInputStream in = new ObjectInputStream(is);

			UserC clist = (UserC) in.readObject();
			in.close();

			return clist;
		} else
			return null;

	}

}
