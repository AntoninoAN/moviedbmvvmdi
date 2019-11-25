package com.example.walmartccmovies.di.modules

import com.example.walmartccmovies.model.Network
import com.example.walmartccmovies.viewmodel.CustomViewModelFactory

class ViewModelFactoryProvider {

    fun providerViewModelFactory(network: Network) = CustomViewModelFactory(network)
}