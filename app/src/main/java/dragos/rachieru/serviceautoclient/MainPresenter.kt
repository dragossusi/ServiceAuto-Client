package dragos.rachieru.serviceautoclient

import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * ServiceAutoClient by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class MainPresenter(val mainActivity: MainActivity) {

    val api: ServiceClientApi
    var disposable: Disposable? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://teamp.go.ro:3000")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor())
                    .build()
            )
            .build()
        api = retrofit.create(ServiceClientApi::class.java)
    }

    fun createRequest(
        name: String,
        phone: String,
        issue: String,
        car: String,
        model: String
    ) {
        api.sendIssue(IssueBody(name, phone, issue, car, model))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    mainActivity.onSent()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    mainActivity.onError(e)
                }
            })
    }

    fun dispose() {
        disposable?.dispose()
    }

}