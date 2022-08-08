package com.example.boardofmessagesapp.repository.retrofit;

import com.example.boardofmessagesapp.repository.Board;
import com.example.boardofmessagesapp.repository.GeneralResponse;
import com.example.boardofmessagesapp.repository.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BoardInterface {

    @GET("board")
    public Call<List<Board>> getBoards();

    @POST("board")
    public Call<Board> addBoard(@Body Board board);

    @GET("board/{iid}")
    public Call<Board> getBoard(@Path("iid") long iid);

    @DELETE("board/{iid}")
    public Call<GeneralResponse> deleteBoard(@Path("iid") long iid);

    @POST("board/{iid}/messages")
    public Call<GeneralResponse> postMessage(@Path("iid") long iid, @Body Message message);

    // only for testing
    @GET("reset")
    public Call<GeneralResponse> reset();

    @GET("board/{iid}/messages")
    public Call<List<Message>> getMessages(@Path("iid") long iid);
}
