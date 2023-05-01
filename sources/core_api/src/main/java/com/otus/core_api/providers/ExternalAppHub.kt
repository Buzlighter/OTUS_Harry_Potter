package com.otus.core_api.providers

// Для вызова app из Features
interface ExternalAppHub {
    fun getExternalHub(): ExternalProvider
}