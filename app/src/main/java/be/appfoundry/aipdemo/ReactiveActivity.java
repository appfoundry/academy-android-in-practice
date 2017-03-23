package be.appfoundry.aipdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import be.appfoundry.aipdemo.database.Post;
import be.appfoundry.aipdemo.service.PostService;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ReactiveActivity extends AppCompatActivity {

    @Inject PostService postService;

    @BindView(R.id.reactive_log) TextView logView;
    @BindView(R.id.reactive_button) Button button;

    private String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reactive);

        ButterKnife.bind(this);

        AIPDemoApplication.getAppComponent().inject(this);

        simpleRxJava();
        //simpleRxJavaWithOperators();
        //combinedObservables();
        //reactiveButton();
        //reactiveDebounceButton();
        //reactiveService();
    }

    private void simpleRxJava() {
        Observable<String> simpleStringObservable = Observable.just("Eric", "Koen", "Bart", "Lies", "Niki");

        Subscriber<String> simpleStringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                logOnCompleted();
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                logOnNext(s);
            }
        };

        simpleStringObservable.subscribe(simpleStringSubscriber);
    }

    private void simpleRxJavaWithOperators() {
        Observable<String> simpleStringObservable = Observable.just("Eric", "Koen", "Bart", "Lies", "Niki");

        Subscriber<String> simpleStringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                logOnCompleted();
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                logOnNext(s);
            }
        };

        simpleStringObservable
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.contains("i");
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s.toUpperCase();
                    }
                })
                .subscribe(simpleStringSubscriber);
    }

    private void combinedObservables() {
        Observable<String> simpleMenObservable = Observable.just("Eric", "Koen", "Bart");
        Observable<String> simpleWomenObservable = Observable.just("Lies", "Niki");

        Subscriber<String> simpleStringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                logOnCompleted();
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                logOnNext(s);
            }
        };

        Observable.concat(simpleMenObservable, simpleWomenObservable).subscribe(simpleStringSubscriber);
    }

    private void reactiveButton() {
        Observable<Object> clickObservable = RxView.clicks(button);

        Subscriber<Object> clickSubscriber = new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                logOnCompleted();
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                logOnNext("Click");
            }
        };

        clickObservable.subscribe(clickSubscriber);
    }

    private void reactiveDebounceButton() {
        Observable<Object> clickObservable = RxView.clicks(button);

        Subscriber<Object> clickSubscriber = new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                logOnCompleted();
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                logOnNext("Click");
            }
        };

        clickObservable
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clickSubscriber);
    }

    private void reactiveService() {
        postService.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        logOnCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        logOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        logOnNext("Found " + posts.size() + " posts");
                    }
                });
    }

    private void logOnCompleted() {
        log("onCompleted");
    }

    private void logOnNext(String message) {
        log("onNext: " + message);
    }

    private void logOnError(String error) {
        log("onError: " + error);
    }

    private void log(String message) {
        log += message + "\n";
        logView.setText(log);
    }

}