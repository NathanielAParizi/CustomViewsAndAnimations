import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MapOperatorActivity : AppCompatActivity() {
    private var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_operator)
        usersObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { user ->
                // modifying user object by adding email address
                // turning user name to uppercase
                user.setEmail(String.format("%s@rxjava.wtf", user.getName()))
                user.setName(user.getName().toUpperCase())
                user
            }
            .subscribe(object :
                Observer<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(user: com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User) {
                    Log.e(
                        TAG,
                        "onNext: " + user.getName() + ", " + user.getGender() + ", " + user.getAddress().getAddress()
                    )
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: " + e.message)
                }

                override fun onComplete() {
                    Log.e(TAG, "All users emitted!")
                }
            })
    }

    /**
     * Assume this method is making a network call and fetching Users
     * an Observable that emits list of users
     * each User has name and email, but missing email id
     */
    private val usersObservable: Observable<Any>
        private get() {
            val names =
                arrayOf("mark", "john", "trump", "obama")
            val users: MutableList<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User> =
                ArrayList()
            for (name in names) {
                val user: com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User =
                    com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User()
                user.setName(name)
                user.setGender("male")
                users.add(user)
            }
            return Observable
                .create(object :
                    ObservableOnSubscribe<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User?> {
                    @Throws(Exception::class)
                    override fun subscribe(emitter: ObservableEmitter<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User?>) {
                        for (user in users) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(user)
                            }
                        }
                        if (!emitter.isDisposed()) {
                            emitter.onComplete()
                        }
                    }
                }).subscribeOn(Schedulers.io())
        }

    override fun onDestroy() {
        super.onDestroy()
        disposable!!.dispose()
    }

    companion object {
        private val TAG = MapOperatorActivity::class.java.simpleName
    }
}