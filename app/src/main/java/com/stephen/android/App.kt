package com.stephen.android

import swan.atom.core.basectx.SwanAtomBaseApplication

/**
 * Created by stephen on 09/03/2018.
 */
class App: SwanAtomBaseApplication() {

    override fun initModuleApplicationImpl(module: MutableList<String>) {
        module.add("swan.atom.core.AtomCoreApplicationImpl")
        module.add("swan.biz.koala.KoalaApplicationImpl")
    }
}