package com.ustinovauliana.citiesapp

import com.arkivanov.decompose.Cancellation
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.mvikotlin.core.store.Store


fun <T : Store<*, *, *>> InstanceKeeper.getStore(key: Any, factory: () -> T): T =
    getOrCreate(key) { StoreHolder(factory()) }
        .store

inline fun <reified T : Store<*, *, *>> InstanceKeeper.getStore(noinline factory: () -> T): T =
    getStore(T::class, factory)

private class StoreHolder<T : Store<*, *, *>>(
    val store: T
) : InstanceKeeper.Instance {
    override fun onDestroy() {
        store.dispose()
    }
}

fun <T : Any> Store<*, T, *>.asValue(): Value<T> =
    object : Value<T>() {
        override val value: T get() = state

        override fun subscribe(observer: (T) -> Unit): Cancellation {
            val disposable = states(com.arkivanov.mvikotlin.core.rx.observer(onNext = observer))

            return Cancellation {
                disposable.dispose()
            }
        }
    }

