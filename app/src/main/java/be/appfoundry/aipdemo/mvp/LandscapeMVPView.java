package be.appfoundry.aipdemo.mvp;

import be.appfoundry.aipdemo.database.Post;

public interface LandscapeMVPView {

    void showPost(Post post);
    void showError(String error);

}
