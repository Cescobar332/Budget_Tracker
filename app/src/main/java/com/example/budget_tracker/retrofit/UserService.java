package com.example.budget_tracker.retrofit;

import com.example.budget_tracker.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("usuarios/")
    Call<Object> obtenerTodo();

    @POST("usuarios/")
    Call<Object> agregar(@Body Usuario nuevoUsuario);

    @PUT("usuarios/")
    Call <Object> editar(@Path("llave") String id);

    @DELETE("usuarios/")
    Call <Object> eliminar(@Path("llave") String id);
}
