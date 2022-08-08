package com.example.checkweatherapp.weatherService;

import android.util.Log;

import com.example.checkweatherapp.weatherService.response.WeatherResponse;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    // get appid in the site
    private static String APPID = "1b3d0e73c2837e05efe700a16d3756e2";
    private static String UNITS = "metric";
    public static void getTemperatureFor(String cityName, WeatherCallback cb) {
        // create interface to access the web api
        WeatherInterface client = WeatherAPIClient.getClient().create(WeatherInterface.class);
        Call<JsonElement> call = client.getWeatherByCityName(APPID, cityName, UNITS);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful())
                    Log.d("WeatherRepository", "" + response.body().toString());

                try {
                    JSONObject jobj = new JSONObject(response.body().toString());
                    double rTemp = jobj.getJSONObject("main").getDouble("temp");
                    cb.onSuccess(rTemp);
                } catch (Exception e) {
                    e.printStackTrace();
                    cb.onError("Fail to retrieve/parse Json");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public static void getTemperatureForV2(String cityName, WeatherCallback cb) {
        WeatherInterface client = WeatherAPIClient.getClient().create(WeatherInterface.class);
        Call<WeatherResponse> call = client.getWeatherByCityNameV2(APPID, cityName, UNITS);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful())
                    Log.d("WeatherRepository", "" + response.body().toString());

                try {
                    double rTemp = response.body().getMain().getTemp();
                    cb.onSuccess(rTemp);
                } catch (Exception e) {
                    e.printStackTrace();
                    cb.onError("Fail to retrieve/parse Json");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public interface WeatherCallback {
        public void onSuccess(double temperature);

        public void onError(String errorMessage);
    }
}
