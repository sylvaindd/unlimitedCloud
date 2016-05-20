import {ContextContainer} from "../utils/ContextContainer";
import {Injectable} from '@angular/core';

@Injectable()
export class WebSocketService {

    public ws:WebSocket;

    public callBackDropboxAdded;
    public callBackGetFiles;
    public callBackConnected;

    constructor(private contextContainer:ContextContainer) {

    }

    init() {
        this.ws = new WebSocket("ws://localhost:8080/lebonnuage/socket");

        this.ws.onopen = function () {
            console.log('SOCKET : Connection open!');
            let json = {token: this.contextContainer.token};
            this.sendJson(json);
            this.callBackConnected();
        }.bind(this);

        this.ws.onclose = function () {
            console.log('SOCKET : Connection closed');
        }

        this.ws.onerror = function (error) {
            console.log('SOCKET : Error detected: ' + error);
        }

        this.ws.onmessage = function (e) {
            var server_message = JSON.parse(e.data);
            console.log("SOCKET : " + server_message);


            console.log("SOCKET : " + server_message.function);
            switch (server_message.function) {
                case "dropboxAdded":
                    this.callBackDropboxAdded(server_message.data);
                    break;
                case "getFilesFolders":
                    this.callBackGetFiles(server_message.data);
                    break;

            }
        }.bind(this);
    }

    createFolder(name) {

    }

    mkdir(name) {
        let json = {function: "mkdir", fileName: name};
        this.sendJson(json);
    }

    askForFiles() {
        let json = {function: "getFilesFolders", path: ""};
        this.sendJson(json);
    }

    sendJson(json) {
        this.ws.send(JSON.stringify(json));
    }
}
