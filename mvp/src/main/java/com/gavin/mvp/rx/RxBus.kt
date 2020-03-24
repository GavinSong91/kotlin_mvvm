package com.gavin.mvp.rx

import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor

class RxBus private constructor() {
    // 主题
    var bus: FlowableProcessor<Any>? = null

    companion object {
        val instance = SingletonHolder.holder
    }

    init {
        bus = PublishProcessor.create<Any>().toSerialized()
    }

    private object SingletonHolder {
        val holder = RxBus()
    }

    // 提供了一个新的事件
    fun post(o: Any) {
        bus!!.onNext(o)
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    fun <T> toFlowable(eventType: Class<T>): Flowable<T> {
        return bus!!.ofType(eventType)
    }

    // 封装默认订阅
    fun <T> toDefaultFlowable(eventType: Class<T>, act: Consumer<T>): Disposable {
        return bus!!.ofType(eventType).compose(RxUtil.instance.rxSchedulerHelper()).subscribe(act)
    }
}