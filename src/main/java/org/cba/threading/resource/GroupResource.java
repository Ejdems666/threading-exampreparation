package org.cba.threading.resource;

import com.google.gson.Gson;
import org.cba.threading.Group;
import org.cba.threading.ScrapingCallable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by adam on 9/22/2017.
 */
@Path("/group")
public class GroupResource {
    private static String[] groupUrls;

    static {
        groupUrls = new String[]{
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://207.154.217.117/Group6-CA1_war/",
                "http://pcatana.eu/ca1",
                "http://207.154.217.117/Group6-CA1_war/"
        };
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getScrapedGroups(){
        List<Group> groups = getGroupsInParallel();
        Gson gson = new Gson();
        return gson.toJson(groups);
    }

    private List<Group> getGroupsInParallel() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Group>> futures = new ArrayList<>();
        for (String groupUrl : groupUrls) {
            futures.add(
                    executor.submit(new ScrapingCallable(groupUrl))
            );
        }
        List<Group> groups = new ArrayList<>();
        for (Future<Group> future : futures) {
            try {
                groups.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return groups;
    }
}
