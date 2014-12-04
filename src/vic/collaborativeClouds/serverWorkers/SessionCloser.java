/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vic.collaborativeClouds.serverWorkers;

import java.io.IOException;
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
public class SessionCloser {
    
    public void closeSession() throws JSONException, IOException{
        
        String currentSession=SessionStore.session_id;
        String sessionHeader=ServerConnector.session_header;
        String currentUser=SessionStore.username;
        
        JSONObject mUserData=new JSONObject();
        mUserData.put("username", currentUser);
        HttpPostWorker mPost=new HttpPostWorker();
        mPost.PostRequest(ServerConnector.logout_url, mUserData.toString());
        
    }
    
}
