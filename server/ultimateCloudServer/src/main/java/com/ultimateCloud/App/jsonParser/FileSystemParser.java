package com.ultimateCloud.App.jsonParser;

import com.googlecode.concurrenttrees.common.PrettyPrinter;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import com.googlecode.concurrenttrees.radix.node.util.PrettyPrintable;
import com.ultimateCloud.App.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.ConcurrentRadixTreeInMemoryFileSystem;
import utils.InMemoryFileSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoma on 20/05/2016.
 */
public class FileSystemParser {
    public static  List<FileSystem> parse(String json){
       List<FileSystem> fileInFolder= new ArrayList<>();
        JSONObject jsonObj = new JSONObject(json);
       JSONArray fileFolderList= jsonObj.getJSONArray("entries");

        for (int i = 0; i < fileFolderList.length(); ++i) {
            JSONObject entrie = fileFolderList.getJSONObject(i);
            FileSystem fileSystem=null;
            String path = entrie.getString("path_lower");
            String name = entrie.getString("name");
            String id = entrie.getString("id").replace("id:","");
            if(entrie.getString(".tag").equals("folder")){

                fileSystem = new FolderCloud(id,name);
            }
            else if(entrie.getString(".tag").equals("file")){
                String dateDeModification = entrie.getString("client_modified");
                String size = String.valueOf(entrie.getBigInteger("size"));
                fileSystem = new FileCloud(id,name,dateDeModification,size);
            }
            fileInFolder.add(fileSystem);
        }

        return fileInFolder;
    }
}
