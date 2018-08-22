package com.example.luic0.googlemaps.services;

import com.example.luic0.googlemaps.application.Services;
import com.example.luic0.googlemaps.models.requests.ContactRequest;
import com.example.luic0.googlemaps.models.responses.ContactResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Francisco Carranza on 8/21/18.
 * eSoft del Pacifico
 */
public class ContactService {
    private ContactApi mAPi;

    public ContactService(){
        mAPi = Services.getRetrofitInstance().create(ContactApi.class);
    }

    public interface ContactApi {

        @Headers("Content-Type: application/json")
        @POST("Contact/sendEmail")
        Observable<ContactResponse> contact(@Body ContactRequest request);
    }

    public ContactApi getAPi() {
        return mAPi;
    }
}
