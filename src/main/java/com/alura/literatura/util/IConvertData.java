package com.alura.literatura.util;

public interface IConvertData {
    <T> T obtenerDatos(String json, Class<T> clase);
}

