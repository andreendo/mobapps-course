package com.example.boardofmessagesapp.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boardofmessagesapp.repository.Board;
import com.example.boardofmessagesapp.repository.BoardRepository;
import com.example.boardofmessagesapp.repository.GeneralResponse;
import com.example.boardofmessagesapp.repository.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Board>> boards = new MutableLiveData<>();
    private final MutableLiveData<List<Message>> messages = new MutableLiveData<>();
    private final MutableLiveData<Integer> currentBoardId = new MutableLiveData<>();
    private final MutableLiveData<String> addMessageError = new MutableLiveData<>();
    private final MutableLiveData<String> showMessageError = new MutableLiveData<>();
    private final MutableLiveData<String> deleteMessage = new MutableLiveData<>("");
    private BoardRepository repo = new BoardRepository();

    public MutableLiveData<List<Board>> getBoards() {
        return boards;
    }

    public MutableLiveData<List<Message>> getMessages() {
        return messages;
    }

    public MutableLiveData<String> getAddMessageError() {
        return addMessageError;
    }

    public MutableLiveData<String> getShowMessageError() {
        return showMessageError;
    }

    public MutableLiveData<String> getDeleteMessage() {
        return deleteMessage;
    }

    public MutableLiveData<Integer> getCurrentBoardId() {
        return currentBoardId;
    }

    public void collectBoards() {
        Log.d("MainViewModel", "collectBoards");
        repo.getBoards(new BoardRepository.BoardsCallback() {
            @Override
            public void onSuccess(List<Board> lBoards) {
                Log.d("MainViewModel", lBoards.toString());
                boards.setValue(lBoards);
            }

            @Override
            public void onError(String errorMessage) {
                Log.d("MainViewModel", errorMessage);
            }
        });
    }

    public void initAddPostMessage() {
        addMessageError.setValue("");
    }

    public void initDeleteBoardMessage() {
        deleteMessage.setValue("");
    }

    public void postMessage(int boardId, String from, String to, String msg) {
        if (boardId == -1) {
            addMessageError.postValue("empty value");
            return;
        }

        Message newMessage = new Message();
        newMessage.setFrom(from);
        newMessage.setTo(to);
        newMessage.setText(msg);
        repo.postMessage(boardId, newMessage, new BoardRepository.GeneralResponseCallback() {
            @Override
            public void onSuccess(GeneralResponse generalResponse) {
                addMessageError.postValue("success");
            }

            @Override
            public void onError(String error) {
                addMessageError.postValue(error);
            }
        });
    }

    public void showMessagesFor(int boardId) {
        if (boardId == -1) {
            showMessageError.postValue("empty value");
            messages.setValue(new ArrayList<>());
            return;
        }
        currentBoardId.setValue(boardId);
        repo.getMessagesFor(boardId, new BoardRepository.MessagesCallback() {
            @Override
            public void onSuccess(List<Message> msgs) {
                Collections.reverse(msgs);
                messages.setValue(msgs);
            }

            @Override
            public void onError(String errorMessage) {
                messages.setValue(new ArrayList<>());
                showMessageError.postValue(errorMessage);
            }
        });
    }

    public void deleteBoard(int iid) {
        repo.deleteBoard(iid, new BoardRepository.GeneralResponseCallback() {
            @Override
            public void onSuccess(GeneralResponse generalResponse) {
                deleteMessage.setValue(generalResponse.getMessage());
            }

            @Override
            public void onError(String errorMessage) {
                deleteMessage.setValue(errorMessage);
            }
        });
    }

    public void initShow() {
        showMessageError.setValue("");
    }
}
