package com.mall.library.net.callback

import android.os.Handler
import com.mall.library.global.GlobalKeys
import com.mall.library.global.Mall
import com.mall.library.ui.loader.MallLoader
import com.mall.library.ui.loader.Style
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class RequestCallback (
    private val request:IRequest?,
    private val success:ISuccess?,
    private val failue:IFailure?,
    private val error:IError?,
    private val complete:IComplete?,
    private val loaderStyle:Style?
): Callback<String> {
    override fun onFailure(call: Call<String>, t: Throwable) {
        if(failue!=null){
            failue.onFailure()

        }
        if(request!=null)
        {
            request.onRequestEnd()
        }


    }

    override fun onResponse(call: Call<String>, response: Response<String>) {
        if(response.isSuccessful){
            if(call.isExecuted){
                if(success!=null){
                    if(response.body()!=null){
                        success.onSuccess(response.body()!!)
                    }
                }
            }
        }else{
                error?.onError(response.code(),response.message())
        }
        onRequestFinish()
    }

    private fun onRequestFinish(){
        val delayed= Mall.getConfiguration<Long>(GlobalKeys.LOADER_DELAY)
        if(loaderStyle!=null)
        {

            HANDLER.postDelayed({MallLoader.stopLoading()},delayed)

        }
    }

    companion object {
        private val HANDLER=Mall.getConfiguration<Handler>(GlobalKeys.HANDLER)
    }





}