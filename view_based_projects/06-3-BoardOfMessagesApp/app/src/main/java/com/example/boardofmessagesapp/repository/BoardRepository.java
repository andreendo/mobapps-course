package com.example.boardofmessagesapp.repository;

import com.example.boardofmessagesapp.repository.retrofit.APIClient;
import com.example.boardofmessagesapp.repository.retrofit.BoardInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BoardRepository {

    private Retrofit apiClient;

    public BoardRepository() {
        apiClient = APIClient.getClient();
    }

    public BoardRepository(boolean test) {
        apiClient = APIClient.getTestClient();
    }

    public void reset() throws Exception {
        BoardInterface client = apiClient.create(BoardInterface.class);
        // sync call
        client.reset().execute();
    }

    public void getBoards(BoardsCallback cb) {
        BoardInterface client = apiClient.create(BoardInterface.class);
        Call<List<Board>> call = client.getBoards();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                if (! response.isSuccessful())
                    cb.onError(response.message());
                else
                    cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void getBoard(long boardId, BoardCallback cb) {
        BoardInterface client = apiClient.create(BoardInterface.class);
        Call<Board> call = client.getBoard(boardId);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if (! response.isSuccessful())
                    cb.onError(response.message());
                else
                    cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void getMessagesFor(long boardId, MessagesCallback cb) {
        BoardInterface client = apiClient.create(BoardInterface.class);
        Call<List<Message>> call = client.getMessages(boardId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (! response.isSuccessful())
                    cb.onError(response.message());
                else
                    cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void deleteBoard(long boardId, GeneralResponseCallback cb) {
        BoardInterface client = apiClient.create(BoardInterface.class);
        Call<GeneralResponse> call = client.deleteBoard(boardId);
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (! response.isSuccessful())
                    cb.onError(response.message());
                else
                    cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void addBoard(Board board, BoardCallback cb) {
        BoardInterface client = apiClient.create(BoardInterface.class);
        Call<Board> call = client.addBoard(board);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void postMessage(long boardId, Message message, GeneralResponseCallback cb) {
        BoardInterface client = apiClient.create(BoardInterface.class);
        Call<GeneralResponse> call = client.postMessage(boardId, message);
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (! response.isSuccessful())
                    cb.onError(response.message());
                else
                    cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public interface BoardsCallback {
        public void onSuccess(List<Board> boards);
        public void onError(String errorMessage);
    }

    public interface MessagesCallback {
        public void onSuccess(List<Message> messages);
        public void onError(String errorMessage);
    }

    public interface BoardCallback {
        public void onSuccess(Board board);
        public void onError(String errorMessage);
    }

    public interface GeneralResponseCallback {
        public void onSuccess(GeneralResponse generalResponse);
        public void onError(String errorMessage);
    }
}
