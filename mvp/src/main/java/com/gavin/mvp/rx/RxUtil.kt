package com.gavin.mvp.rx

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
class RxUtil private constructor(){

    companion object {
        val instance = SingletonHolder.holder
    }


    private object SingletonHolder {
        val holder = RxUtil()
    }

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    fun <T> rxSchedulerHelper(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> rxSchedulerIOHelper(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        }
    }


    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    fun <T> createData(t: T): Flowable<T> {
        return Flowable.create({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }

    //=============时间定时工具=========================
    private var mDisposable: Disposable? = null

    /**
     * milliseconds毫秒后执行next操作
     *
     * @param milliseconds
     * @param next
     */
    fun timer(milliseconds: Long, next: IRxNext?) {
        Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onNext(number: Long) {
                    if (next != null) {
                        next!!.doNext(number)
                    }
                }

                override fun onSubscribe(@NonNull disposable: Disposable) {
                    mDisposable = disposable
                }

                override fun onError(@NonNull e: Throwable) {
                    //取消订阅
                    cancel()
                }

                override fun onComplete() {
                    //取消订阅
                    cancel()
                }
            })
    }

    /**
     * 每隔milliseconds毫秒后执行next操作
     *
     * @param milliseconds
     * @param next
     */
    fun interval(milliseconds: Long, next: IRxNext?) {
        Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onNext(number: Long) {
                    next?.doNext(number)
                }

                override fun onSubscribe(@NonNull disposable: Disposable) {
                    mDisposable = disposable
                }

                override fun onError(@NonNull e: Throwable) {
                    //取消订阅
                    cancel()
                }

                override fun onComplete() {

                }

            })
    }


    /**
     * 取消订阅
     */
    fun cancel() {
        mDisposable.let {
            if (!it!!.isDisposed)
                it.dispose()
        }
//        if (mDisposable != null && !mDisposable!!.isDisposed) {
//            mDisposable!!.dispose()
//        }
    }

    open interface IRxNext {
        fun doNext(number: Long)
    }
}