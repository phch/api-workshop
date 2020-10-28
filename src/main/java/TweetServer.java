import model.Tweet;
import repository.TweetRepository;
import repository.TweetRepositoryImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.Instant;
import java.util.List;

@Path("/tweets")
public class TweetServer {
    private final TweetRepository tweetRepository = new TweetRepositoryImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTweet(Tweet tweet, @Context UriInfo uriInfo) {
        Tweet publishedTweet = tweetRepository.add(tweet);
        URI uri = uriInfo.getAbsolutePathBuilder().path(publishedTweet.getId()).build();
        return Response.created(uri).entity(publishedTweet).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> getAllTweets() {
        return tweetRepository.getNewerThan(Instant.MAX);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweet(@PathParam("id") String id) {
        Tweet tweet = tweetRepository.get(id);
        if (tweet == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(tweet).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTweet(@PathParam("id") String id, Tweet tweet, @Context UriInfo uriInfo) {
        if (tweet.getId() != null && !id.equals(tweet.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tweet ID cannot be modified").build();
        }

        tweet.setId(id);
        tweetRepository.update(tweet);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTweet(@PathParam("id") String id) {
        tweetRepository.remove(id);
        return Response.ok().build();
    }
}