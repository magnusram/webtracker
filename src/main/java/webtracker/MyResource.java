package webtracker;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import dao.GroupDAO;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("group")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)    
    public boolean createGroup(String formData) {
    	Gson gson = new Gson();
    	boolean isSuccess = true;
    	Group grp = gson.fromJson(formData, Group.class);
    	try {
			GroupDAO.createGroup(grp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		}
    	System.out.println(grp);
    	return isSuccess;
    }
}
