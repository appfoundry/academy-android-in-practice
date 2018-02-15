package be.appfoundry.aipdemo.activity.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.R;
import be.appfoundry.aipdemo.model.StarWarsFilm;
import be.appfoundry.aipdemo.model.StarWarsFilms;
import be.appfoundry.aipdemo.service.SwapiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ReactiveActivity extends AppCompatActivity {

    @BindView(R.id.activity_reactive_log) TextView logView;
    @BindView(R.id.activity_reactive_button) Button button;

    @Inject SwapiService swapiService;

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
        //reactiveServiceOneByOne();
    }

    private void simpleRxJava() {
        Observable<String> simpleStringObservable = Observable.just("Eric", "Koen", "Bart", "Lies", "Niki");

        Observer<String> simpleStringObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                logOnNext(s);
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e);
            }

            @Override
            public void onComplete() {
                logOnCompleted();
            }
        };

        simpleStringObservable.subscribe(simpleStringObserver);
    }

    private void simpleRxJavaWithOperators() {
        Observable<String> simpleStringObservable = Observable.just("Eric", "Koen", "Bart", "Lies", "Niki");

        Observer<String> simpleStringObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                logOnNext(s);
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e);
            }

            @Override
            public void onComplete() {
                logOnCompleted();
            }
        };

        simpleStringObservable
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.contains("i");
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s.toUpperCase();
                    }
                })
                .subscribe(simpleStringObserver);
    }

    private void combinedObservables() {
        Observable<String> simpleMenObservable = Observable.just("Eric", "Koen", "Bart");
        Observable<String> simpleWomenObservable = Observable.just("Lies", "Niki");

        Observer<String> simpleStringObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                logOnNext(s);
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e);
            }

            @Override
            public void onComplete() {
                logOnCompleted();
            }
        };

        Observable.concat(simpleMenObservable, simpleWomenObservable).subscribe(simpleStringObserver);
    }

    private void reactiveButton() {
        Observable<Object> clickObservable = RxView.clicks(button);

        Observer<Object> clickObserver = new DisposableObserver<Object>() {
            @Override
            public void onNext(Object o) {
                logOnNext("Click");
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e);
            }

            @Override
            public void onComplete() {
                logOnCompleted();
            }
        };

        clickObservable.subscribe(clickObserver);
    }

    private void reactiveDebounceButton() {
        Observable<Object> clickObservable = RxView.clicks(button);

        Observer<Object> clickObserver = new DisposableObserver<Object>() {
            @Override
            public void onNext(Object o) {
                logOnNext("Click");
            }

            @Override
            public void onError(Throwable e) {
                logOnError(e);
            }

            @Override
            public void onComplete() {
                logOnCompleted();
            }
        };

        clickObservable
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clickObserver);
    }

    private void reactiveService() {
        swapiService.getFilms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<StarWarsFilms>() {
                    @Override
                    public void onNext(StarWarsFilms starWarsFilms) {
                        logOnNext("Found " + starWarsFilms.getResults().size() + " films");
                    }

                    @Override
                    public void onError(Throwable e) {
                        logOnError(e);
                    }

                    @Override
                    public void onComplete() {
                        logOnCompleted();
                    }
                });
    }

    private void reactiveServiceOneByOne() {
        swapiService.getFilms()
                .flatMap(new Function<StarWarsFilms, Observable<StarWarsFilm>>() {
                    @Override
                    public Observable<StarWarsFilm> apply(StarWarsFilms starWarsFilms) throws Exception {
                        return Observable.fromIterable(starWarsFilms.getResults());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<StarWarsFilm>() {
                    @Override
                    public void onNext(StarWarsFilm starWarsFilm) {
                        logOnNext(starWarsFilm.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        logOnError(e);
                    }

                    @Override
                    public void onComplete() {
                        logOnCompleted();
                    }
                });
    }

    private void logOnCompleted() {
        log("onCompleted");
    }

    private void logOnNext(String message) {
        log("onNext: " + message);
    }

    private void logOnError(Throwable error) {
        log("onError: " + error.getMessage());
    }

    private void log(String message) {
        log += message + "\n";
        logView.setText(log);
    }

}