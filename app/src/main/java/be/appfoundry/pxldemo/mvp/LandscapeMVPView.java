package be.appfoundry.pxldemo.mvp;

import be.appfoundry.pxldemo.database.Post;

public interface LandscapeMVPView {

    void showPost(Post post);
    void showError(String error);

}
