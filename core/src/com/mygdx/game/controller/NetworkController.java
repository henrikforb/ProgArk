package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.Settings;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class NetworkController {

    private Socket socket;

    public void connectSocket() {
        try {
            socket = IO.socket("https://progark-server.herokuapp.com/");
            //socket = IO.socket("http://localhost:8080");
            socket.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: " + id);
                    Gdx.app.log("SocketIO","connected to heroku server");
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("startGame", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    Settings.getInstance().enableMultiplayer(""+data.getInt("gameID"));
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error starting game");
                }
            }
        });
    }

    public void handleDeath() {
        JSONObject data = new JSONObject();
        try {
            data.put("gameID", Settings.getInstance().getId());
            socket.emit("death", data);
            socket.disconnect();
            System.out.println("disconnected");
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error sending update data");
        }
    }

    public void disconnect() {
        socket.disconnect();
    }

    public Socket getSocket() {
        return socket;
    }
}
