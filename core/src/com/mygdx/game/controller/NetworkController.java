package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.singelton.Settings;
import com.mygdx.game.model.World;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class NetworkController {

    private CharacterController characterController;
    private World world;
    private String gameID;


    private Socket socket;

    public void connectSocket() {
        try {
            socket = IO.socket("https://progark-server.herokuapp.com/");
            // socket = IO.socket("http://localhost:8080");
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
                    gameID = id;
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
                    Settings.getInstance().multiplayerReady(""+data.getInt("gameID"));
                    System.out.println("Was told by server to start game");
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error starting game");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connect: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New PlayerID");
                }
            }
        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting disconnect message");
                }
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    if (data.getInt("movement") == 0) {
                        characterController.touch(world.getEnemy());
                    } else {
                        characterController.swipe(world.getEnemy(), data.getInt("direction"));
                    }
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting movement value or direction");
                }
            }
        }).on("victory", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                world.setEnemyDead();
                Gdx.app.log("SocketIO", "Other player died");
                disconnect();
                System.out.println("disconnected");
            }
        }).on("nextObstacle", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (Settings.getInstance().getMutiplayerReady()) {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        int obstacle = (int) data.getDouble("obstacle");
                        int height = (int) data.getDouble("height");
                        world.getObstacleFactory().setNextObstacle(obstacle, height);
                        Gdx.app.log("SocketIO", "obstacle: "+ obstacle + " height: " + height);
                    } catch (JSONException e) {
                        Gdx.app.log("SocketIO", "Error receiving obstacle");
                    }
                }
            }
        });
    }

    public void updateServer(int movementType, int direction) {
        JSONObject data = new JSONObject();
        try {
            data.put("movement", movementType);
            data.put("direction", direction);
            data.put("gameID", this.gameID);
            socket.emit("playerMoved", data);
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error sending update data");
        }
    }

    public void handleDeath() {
        JSONObject data = new JSONObject();
        try {
            data.put("gameID", Settings.getInstance().getId());
            socket.emit("death", data);
            disconnect();
            System.out.println("disconnected");
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error sending update data");
        }
    }

    public void disconnect() {
        socket.off();
        socket.disconnect();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setCharacterController(CharacterController characterController){
        this.characterController = characterController;
    }

    public void setWorld(World world){
        this.world = world;
    }

    public void setGameID(String gameID){
        this.gameID = gameID;
    }

}
