/**
 * @author Thomas Bruvold
 * https://github.com/Artorp
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;

public class QueryImporter {
	
	/**
	 * Get the query in the given file
	 * 
	 * @param string filename of the desiered file
	 * @return content of file
	 */
	public static String getQuery(String resourceName) {
		String fileContents = "";
		try (InputStream is = QueryImporter.class.getResourceAsStream(resourceName)) {
			if (is == null) {
				throw new NoSuchFileException("No resource of name " + resourceName + " found");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line.trim()).append('\n');
			}
			fileContents = sb.toString();
		} catch (IOException e) {
			throw new RuntimeException("Failed to load resource " + resourceName, e);
		}
		return fileContents;
	}

}