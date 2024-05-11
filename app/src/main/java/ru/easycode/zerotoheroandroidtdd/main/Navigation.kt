package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface Navigation {
    interface Observe : LiveDataWrapper.Observe<Screen>
    interface Update : LiveDataWrapper.Update<Screen>
    interface Mutable : Navigation, Update, Observe
    class Base : Mutable, LiveDataWrapper.Abstract<Screen>()
}