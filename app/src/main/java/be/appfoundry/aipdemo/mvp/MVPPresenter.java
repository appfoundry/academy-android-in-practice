package be.appfoundry.aipdemo.mvp;

public interface MVPPresenter {

    void loadData();

    void attachView(MVPView view);
}
