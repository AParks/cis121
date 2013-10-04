import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JavaScriptPointsWriter {

	public JavaScriptPointsWriter(String filename, String input, List<IntersectionI> points) {
		try {
			File file = new File("src/"+filename + ".js");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);


				bw.append("//JavaScript file \n");

				String varName = filename + input;
				String content = "var " + varName + " = new Array();";
				bw.append(content + "\n\n");
				bw.append("//Data \n");
				for (int j = 0; j < points.size(); j ++) {
					Point p = points.get(j).getLocation();
					double x = p.getx();
					double y = p.gety();
					String datax = varName + "[" + j*2 + "] =" + x + ";";
					String datay = varName + "[" + (j*2 + 1) + "] =" + y + ";";
					bw.append(datax + "\n");
					bw.append(datay + "\n");
				}
				bw.append("\n");

			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
