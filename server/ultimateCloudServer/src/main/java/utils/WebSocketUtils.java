package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultimateCloud.App.cloudServices.Dropbox.Dropbox;
import com.ultimateCloud.App.cloudServices.Dropbox.listFileJson;
import com.ultimateCloud.App.jdbc.JDBCMysSQL;
import com.ultimateCloud.App.models.FileCloud;
import com.ultimateCloud.App.models.FileSystem;
import com.ultimateCloud.App.models.FolderCloud;
import com.ultimateCloud.App.models.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoma on 20/05/2016.
 */
public class WebSocketUtils {
    public static JSONObject getFilesAndFolders(String path, User user){
        JSONObject jsonReturn = new JSONObject();
        List<FileSystem> allFileSystem = new ArrayList<>();
        Dropbox dropbox = new Dropbox();
        List<String> listTokenDropBox = JDBCMysSQL.getInstance().getAllTokenCloudFromUser(1,String.valueOf(user.getId()));
        for (String tokenDropBox : listTokenDropBox) {

            allFileSystem.addAll(dropbox.getFileList(new listFileJson(path), tokenDropBox));
        }
        /*List<String> listTokenGoogleDrive = JDBCMysSQL.getInstance().getAllTokenCloudFromUser(2,String.valueOf(user.getId()));
        for (String tokenDropBox : listTokenDropBox) {

            allFileSystem.addAll(dropbox.getFileList(new listFileJson(path), tokenDropBox));
        }*/

        for (FileSystem fileSystem : allFileSystem) {
            JSONArray actualArray = new JSONArray();
            if(fileSystem instanceof FileCloud)   {
                ObjectMapper mapper = new ObjectMapper();
                //Object to JSON in String
                String jsonInString = null;
                try {
                    jsonInString = mapper.writeValueAsString(fileSystem);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                jsonReturn.append("files",new JSONObject(jsonInString));
            }

            else if(fileSystem instanceof FolderCloud){
                ObjectMapper mapper = new ObjectMapper();
                //Object to JSON in String
                String jsonInString = null;
                try {
                    jsonInString = mapper.writeValueAsString(fileSystem);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                jsonReturn.append("folders",new JSONObject(jsonInString));
            }
        }
        System.out.println(jsonReturn.toString());


        return jsonReturn;
    }
}
