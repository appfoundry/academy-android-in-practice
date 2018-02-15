package be.appfoundry.aipdemo.mvp;

import be.appfoundry.aipdemo.model.StarWarsFilm;

public interface MVPView {

    void showStarWarsFilm(StarWarsFilm starWarsFilm);

    void showError(String error);

}
