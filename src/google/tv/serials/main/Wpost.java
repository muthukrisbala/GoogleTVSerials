package google.tv.serials.main;

import java.net.MalformedURLException;
import java.util.HashMap;
 
import redstone.xmlrpc.XmlRpcClient;
import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcFault;
 
public class Wpost {
 
    public static void main(String serialName,String channel,String title,String content,String category,String keywords,String sitename,String thumbnailId){
        //username and password
        String username = "admin";
        String password = "Saudia@77";
        //replace my website's address with yours
        String xmlRpcUrl = "";
        
        if(sitename.equalsIgnoreCase("webmuthu"))
        	xmlRpcUrl = "http://www.webmuthu.com/xmlrpc.php";
        else if(sitename.equalsIgnoreCase("hindiwebmuthu"))
        	xmlRpcUrl = "http://hindi.webmuthu.com/xmlrpc.php";
        else if(sitename.equalsIgnoreCase("malluwebmuthu"))
        	xmlRpcUrl = "http://mallu.webmuthu.com/xmlrpc.php";
        else if(sitename.equalsIgnoreCase("teluguwebmuthu"))
        	xmlRpcUrl = "http://telugu.webmuthu.com/xmlrpc.php";
        else if(sitename.equalsIgnoreCase("onlinetamilportal"))
        	xmlRpcUrl = "http://www.onlinetamilportal.com/xmlrpc.php";
        else if(sitename.equalsIgnoreCase("onlinekanadaportal"))
        	xmlRpcUrl = "http://www.onlinekanadaportal.com/xmlrpc.php";
        else if(sitename.equalsIgnoreCase("onlineteluguportal"))
        	xmlRpcUrl = "http://www.onlineteluguportal.com/xmlrpc.php";
        else if(sitename.equalsIgnoreCase("onlinehindiportal"))
        	xmlRpcUrl = "http://www.onlinehindiportal.com/xmlrpc.php";
        
 
        try {
            //First step, init an client
            XmlRpcClient client = new XmlRpcClient(xmlRpcUrl, true);
            //Now, put data into the client. The client will encode all data into XML and send it to wordpress XML-RPC API
            
            HashMap hmContent = new HashMap();
            hmContent.put("title", title);
            hmContent.put("description", content);
            hmContent.put("mt_keywords", keywords);
            
            String[] params = new String[]{serialName,channel };
            hmContent.put("categories", params);
            hmContent.put("post_status", "publish");
            
            if(thumbnailId.length()>0){
	            hmContent.put("post_thumbnail", thumbnailId);
	            hmContent.put("wp_post_thumbnail", thumbnailId);
	            hmContent.put("the_post_thumbnail", thumbnailId);
            }
            
            
            //hmContent.put("wp_slug", "test-slug");
            
            
            
            //System.out.println(hmContent.get("categories"));
System.out.println(title);
System.out.println(hmContent.get("categories"));

            
            //All set!! Let's roll~ and call the wordpress.
            client.invoke("metaWeblog.newPost", new Object[] {new Integer(1), username,password,
                                                                      hmContent,
                                                                      true} );
            System.out.println("OUT");
        } catch (XmlRpcException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("XmlRpcException Exception");
        } catch (XmlRpcFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}


