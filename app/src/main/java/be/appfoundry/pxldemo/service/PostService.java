package be.appfoundry.pxldemo.service;

import java.util.List;

import be.appfoundry.pxldemo.database.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface PostService {

    @GET("/posts")
    Observable<List<Post>> getPosts();

    @GET("/posts/{id}")
    Call<Post> getPost(@Path("id") int id);

}
