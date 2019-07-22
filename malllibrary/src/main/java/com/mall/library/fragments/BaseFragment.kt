package com.mall.library.fragments

import android.os.Bundle
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment
import java.lang.NullPointerException

abstract class BaseFragment:SupportFragment(){

    private var mRootView: View?=null
    abstract fun setLayout():Any

    abstract fun onBindView(savedInstanceState:Bundle?,rootView:View)

    fun<T:View> findView(@IdRes viewId:Int):T{
        if(mRootView!=null){
            return mRootView?.findViewById(viewId)!!
        }
        throw NullPointerException("rootView is null")
    }

    onCrea



}