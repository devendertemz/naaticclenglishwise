package com.englishwise.naaticclenglishwise.Rtrofit;

import com.englishwise.naaticclenglishwise.ModalResponse.Generate_otp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    /*  //getcategories
      @GET("getcategories")
      Call<List<CategoryListModel>> getcategories();

  */


    @FormUrlEncoded
    @POST("login/generate_otp.php")
    Call<ResponseBody> Generate_otp(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("login/validate_user.php")
    Call<ResponseBody> validate_user_bynumber(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("login/validate_user.php")
    Call<ResponseBody> validate_user_byEmail(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("registration/registration.php")
    Call<ResponseBody> registration(
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("language") String language
    );

    //read_vocabulary
    //http://misfitamigos.com/naticcl_englishwise/vocabulary/read_vocabulary.php?
    @FormUrlEncoded
    @POST("vocabulary/read_vocabulary.php")
    Call<ResponseBody> read_vocabulary(
            @Field("lan") String lan
    );


}
