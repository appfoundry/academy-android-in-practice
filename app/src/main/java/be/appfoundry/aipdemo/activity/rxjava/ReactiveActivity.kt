package be.appfoundry.aipdemo.activity.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.appfoundry.aipdemo.R
import be.appfoundry.aipdemo.extension.app
import be.appfoundry.aipdemo.service.SwapiService
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_reactive.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ReactiveActivity : AppCompatActivity() {

    @Inject
    lateinit var swapiService: SwapiService

    private val disposables: CompositeDisposable = CompositeDisposable()

    private var log = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app.appComponent.inject(this)

        setContentView(R.layout.activity_reactive)

        simpleRxJava()
        //simpleRxJavaWithOperators()
        //combinedObservables()
        //reactiveButton()
        //reactiveDebounceButton()
        //reactiveService()
        //reactiveServiceOneByOne()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.clear()
    }

    private fun simpleRxJava() {
        val simpleStringObservable = Observable.just("Eric", "Koen", "Bart", "Lies", "Nikki")

        val simpleStringObserver = object : DisposableObserver<String>() {
            override fun onNext(s: String) {
                logOnNext(s)
            }

            override fun onError(e: Throwable) {
                logOnError(e)
            }

            override fun onComplete() {
                logOnCompleted()
            }
        }

        simpleStringObservable.subscribe(simpleStringObserver)

        disposables.add(simpleStringObserver)
    }

    private fun simpleRxJavaWithOperators() {
        Observable.just("Eric", "Koen", "Bart", "Lies", "Nikki")
                .filter { s -> s.contains("i") }
                .map { s -> s.toUpperCase() }
                .subscribe(
                        { s -> logOnNext(s) },
                        { e -> logOnError(e) },
                        { logOnCompleted() }
                )
                .addTo(disposables)
    }

    private fun combinedObservables() {
        val simpleMenObservable = Observable.just("Eric", "Koen", "Bart")
        val simpleWomenObservable = Observable.just("Lies", "Nikki")

        Observable.concat(simpleMenObservable, simpleWomenObservable)
                .subscribe(
                        { logOnNext(it) },
                        { logOnError(it) },
                        { logOnCompleted() }
                )
                .addTo(disposables)
    }

    private fun reactiveButton() {
        RxView.clicks(activityLogButton)
                .subscribe(
                        { logOnNext("Click") },
                        { logOnError(it) },
                        { logOnCompleted() }
                )
                .addTo(disposables)
    }

    private fun reactiveDebounceButton() {
        RxView.clicks(activityLogButton)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { logOnNext("Click") },
                        { logOnError(it) },
                        { logOnCompleted() }
                )
                .addTo(disposables)
    }

    private fun reactiveService() {
        swapiService.getFilms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { logOnNext("Found " + it.results.size + " films") },
                        { logOnError(it) },
                        { logOnCompleted() }
                )
                .addTo(disposables)
    }

    private fun reactiveServiceOneByOne() {
        swapiService.getFilms()
                .flatMap { starWarsFilms -> Observable.fromIterable(starWarsFilms.results) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { logOnNext(it.title) },
                        { logOnError(it) },
                        { logOnCompleted() }
                )
                .addTo(disposables)
    }

    private fun logOnCompleted() {
        log("onCompleted")
    }

    private fun logOnNext(message: String) {
        log("onNext: $message")
    }

    private fun logOnError(error: Throwable) {
        log("onError: ${error.message}")
    }

    private fun log(message: String) {
        log += message + "\n"
        activityLogText.text = log
    }
}