package com.im.bin.observer

import android.os.FileObserver
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.io.File

class PhotosFileObserver(file: File) : FileObserver(file, ALL_EVENTS) {

    private val subject = PublishSubject.create<String>().toSerialized()
    val observable: Observable<String> =
        subject.doOnSubscribe { startWatching() }
            .doOnDispose { stopWatching() }
            .share()

    override fun onEvent(event: Int, path: String?) {
        path?.let { subject.onNext(it) }
    }
}