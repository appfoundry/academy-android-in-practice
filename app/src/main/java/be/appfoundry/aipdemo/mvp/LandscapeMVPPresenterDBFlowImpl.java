package be.appfoundry.aipdemo.mvp;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.database.Post;
import be.appfoundry.aipdemo.database.Post_Table;

public class LandscapeMVPPresenterDBFlowImpl implements LandscapeMVPPresenter {

    private LandscapeMVPView view;

    public LandscapeMVPPresenterDBFlowImpl() {
        AIPDemoApplication.getAppComponent().inject(this);
    }

    @Override
    public void attachView(LandscapeMVPView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        SQLite.select()
                .from(Post.class)
                .where(Post_Table.id.eq(2))
                .async()
                .queryResultCallback(new QueryTransaction.QueryResultCallback<Post>() {
                    @Override
                    public void onQueryResult(QueryTransaction<Post> transaction, @NonNull CursorResult<Post> tResult) {
                        Post post = tResult.toModel();
                        view.showPost(post);
                    }
                }).execute();
    }

}
