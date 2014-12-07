/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vic.collaborativeClouds.serverWorkers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import vic.collaborativeClouds.configs.ServerConnector;
import vic.collaborativeClouds.utils.SessionStore;


/**
 *
 * @author CollaborativeClouds Software Solutions
 * <www.collaborativeclouds.com>
 * <collaborativeclouds@gmail.com>
 */
public class HttpPostWorker {

    public String LoginPostRequest(String postUrl,String postJSONData) throws IOException, JSONException {
        try {
            HttpPost post = new HttpPost(postUrl);
            HttpClient httpClient = new DefaultHttpClient();
            StringEntity postingString = new StringEntity(postJSONData);
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(post);
            String server = response.getFirstHeader("x-session").getValue();
            System.err.println(response.getFirstHeader("status").getValue());
            System.err.println(server);
            SessionStore.session_id=server;
            return response.getFirstHeader("status").getValue();
        } catch (UnsupportedEncodingException ex) {
            return "Failed";
            //Logger.getLogger(HttpPostWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String PostRequest(String postUrl,String postJSONData) throws IOException, JSONException {
        try {
            HttpPost post = new HttpPost(postUrl);
            HttpClient httpClient = new DefaultHttpClient();
            StringEntity postingString = new StringEntity(postJSONData);
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            post.setHeader("username", SessionStore.username);
            post.setHeader(ServerConnector.session_header, SessionStore.session_id);
            HttpResponse response = httpClient.execute(post);
            HttpEntity mEntity=response.getEntity();
            String response_content=EntityUtils.toString(mEntity);
            System.err.println(response_content);
            //JOptionPane.showMessageDialog(null, response_content);
            return response_content;
        } catch (UnsupportedEncodingException ex) {
            return "Failed";
            //Logger.getLogger(HttpPostWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
