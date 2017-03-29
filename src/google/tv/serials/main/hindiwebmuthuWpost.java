package google.tv.serials.main;

import java.net.MalformedURLException;
import java.util.HashMap;
 
import redstone.xmlrpc.XmlRpcClient;
import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcFault;
 
public class hindiwebmuthuWpost {
 
    public static void main(String title,String content,String category,String keywords){
        //username and password
        String username = "admin";
        String password = "Saudia@77";
        //replace my website's address with yours
        String xmlRpcUrl = "http://hindi.webmuthu.com/xmlrpc.php";
 
        try {
            //First step, init an client
            XmlRpcClient client = new XmlRpcClient(xmlRpcUrl, true);
            //Now, put data into the client. The client will encode all data into XML and send it to wordpress XML-RPC API
            
            HashMap hmContent = new HashMap();
            hmContent.put("title", title);
            hmContent.put("description", content);
            hmContent.put("mt_keywords", keywords);
            hmContent.put("post_status", "publish");
            hmContent.put("categories", category);
            
            
            System.out.println(hmContent.get("categories"));

            
            //All set!! Let's roll~ and call the wordpress.
            client.invoke("metaWeblog.newPost", new Object[] {new Integer(1), username,password,
                                                                      hmContent,
                                                                      true} );
        } catch (XmlRpcException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlRpcFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}


