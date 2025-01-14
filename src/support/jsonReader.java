package support;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class jsonReader {
    private JSONObject jsonObject;

    public jsonReader( String filePath) {
        String content = "";
        try{
            content = new String(Files.readAllBytes(Paths.get(filePath)));
            jsonObject = new JSONObject(content);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getValue(String key){
        return jsonObject.getString(key);
    }
}
