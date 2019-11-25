package com.example.walmartccmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walmartccmovies.model.Network

@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory(val network: Network): ViewModelProvider.Factory{

    lateinit var viewModel: CustomViewModel

//    constructor(viewModel: CustomViewModel):super{
//        this.viewModel = viewModel
//    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //return viewModel as T
        return CustomViewModel(network) as T
    }

}