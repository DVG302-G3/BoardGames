
package boardgames.g3.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerFiaMedKnuff {

	public static List<String> readCoordinate()
			throws IOException {
		
		List<String> lines = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("src\\FiaMedKnuffSpelplan2.txt"));
		
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			else
				lines.add(line);
		}

		br.close();
		return lines;

	}
}