import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SecretSharing {

    public static List<Point> parseInput(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        JSONObject keys = json.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");
        List<Point> points = new ArrayList<>();

        for (String key : json.keySet()) {
            if (key.equals("keys")) continue;
            JSONObject root = json.getJSONObject(key);
            String baseString = root.getString("base");
            String valueString = root.getString("value");
            int base = Integer.parseInt(baseString);
            int value = Integer.parseInt(valueString, base);
            int x = Integer.parseInt(key);
            points.add(new Point(x, value));
        }
        return points;
}

    public static int findConstantTerm(List<Point> points) {
        int n = points.size();
        int c = 0;

        for (int i = 0; i < n; i++) {
            int xi = points.get(i).x;
            int yi = points.get(i).y;
            int L = 1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    L = L * (0 - points.get(j).x) / (xi - points.get(j).x);
                }
            }
            c += yi * L;
        }
        return c;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.json");
            Scanner scanner = new Scanner(inputFile);
            StringBuilder jsonString = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonString.append(scanner.nextLine());
            }

            List<Point> points = parseInput(jsonString.toString());
            int constantTerm = findConstantTerm(points);

            System.out.println("The secret (constant term 'c') is: " + constantTerm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();}
            }
}
