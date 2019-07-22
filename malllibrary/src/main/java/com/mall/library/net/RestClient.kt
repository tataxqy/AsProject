package com.mall.library.net

import android.content.Context
import android.hardware.camera2.CaptureFailure
import com.mall.library.net.callback.*
import com.mall.library.ui.loader.MallLoader
import com.mall.library.ui.loader.Style
import retrofit2.Call
import retrofit2.Callback
import java.util.*

/**
 * 在所有依赖mall-library的app中对外暴露直接使用的客户端
 */
class RestClient internal constructor(
    private val url:String?,
    private val params:WeakHashMap<String,Any>?,
    private val request:IRequest?,
    private val success:ISuccess?,
    private val failure: IFailure?,
    private val error:IError?,
    private val conplete:IComplete?,
    private val context: Context?,
    private val loaderStyle: Style?
){

    companion object {
        fun builder():RestClientBuilder{
            return RestClientBuilder()
        }

    }

    private fun request(method:HttpMethod){
        val service=RestCreater.restService
        val call: Call<String>?

        request?.onRequestStart()


        if(loaderStyle!=null){
            MallLoader.showLoading(context,loaderStyle)
        }


        call=when(method){

            HttpMethod.GET -> service.get(url,params)
            HttpMethod.POST -> service.post(url,params)
            HttpMethod.PUT -> service.put(url,params)
            HttpMethod.DELETE -> service.delete(url,params)
            HttpMethod.UPLOAD -> TODO()
        }

        call.enqueue(requestCallback)


    }

    private val requestCallback: Callback<String>
    get()=RequestCallback(request,success,failure,error,conplete,loaderStyle)

    fun get(){
        request(HttpMethod.GET)

    }

    fun post(){
        request(HttpMethod.POST)
    }
    fun delete(){
        request(HttpMethod.DELETE)
    }
    fun put(){
        request(HttpMethod.PUT)
    }


}