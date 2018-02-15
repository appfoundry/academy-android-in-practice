package be.appfoundry.aipdemo.mvp;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.model.StarWarsFilm;
import be.appfoundry.aipdemo.model.StarWarsFilm_Table;

public class MVPPresenterDBFlowImpl implements MVPPresenter {

    private MVPView view;

    public MVPPresenterDBFlowImpl() {
        AIPDemoApplication.getAppComponent().inject(this);
    }

    @Override
    public void attachView(MVPView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        SQLite.select()
                .from(StarWarsFilm.class)
                .where(StarWarsFilm_Table.episodeId.eq(5))
                .async()
                .queryResultCallback(new QueryTransaction.QueryResultCallback<StarWarsFilm>() {
                    @Override
                    public void onQueryResult(QueryTransaction<StarWarsFilm> transaction, @NonNull CursorResult<StarWarsFilm> tResult) {
                        StarWarsFilm starWarsFilm = tResult.toModel();
                        if (starWarsFilm != null) {
                            view.showStarWarsFilm(starWarsFilm);
                        }
                    }
                }).execute();
    }

}
