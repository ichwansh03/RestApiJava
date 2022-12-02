package com.jrektor.restapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("get_product.php")
    Call<List<Products>> getProducts();

    @FormUrlEncoded
    @POST("add_product.php")
    Call<Products> insertProduct(
            @Field("key") String key,
            @Field("name") String name,
            @Field("brand") String brand,
            @Field("type") String type,
            @Field("price") String price,
            @Field("date") String date,
            @Field("image") String image
    );

    @FormUrlEncoded
    @POST("update_product.php")
    Call<Products> updateProduct(
            @Field("key") String key,
            @Field("id") int id,
            @Field("name") String name,
            @Field("brand") String brand,
            @Field("type") String type,
            @Field("price") String price,
            @Field("date") String date,
            @Field("image") String image
    );

    @FormUrlEncoded
    @POST("delete_product.php")
    Call<Products> deleteProduct(
            @Field("key") String key,
            @Field("id") int id,
            @Field("image") String image
    );
}
