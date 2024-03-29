import {ContextContainer} from "../utils/ContextContainer";
import {Injectable} from '@angular/core';

@Injectable()
export class WebSocketService {

    public ws:WebSocket;

    public callBackDropboxAdded;
    public callBackGetFiles;
    public callBackConnected;
    public isConnected;

    constructor(private contextContainer:ContextContainer) {

    }

    init() {
        this.ws = new WebSocket("ws://localhost:8080/lebonnuage/socket");

        this.ws.onopen = function () {
            console.log('SOCKET : Connection open!');
            let json = {token: this.contextContainer.token};
            this.sendJson(json);
            this.callBackConnected();
            this.isConnected = true;
        }.bind(this);

        this.ws.onclose = function () {
            console.log('SOCKET : Connection closed');
        }

        this.ws.onerror = function (error) {
            console.log('SOCKET : Error detected: ' + error);
        }

        this.ws.onmessage = function (e) {
            var server_message = JSON.parse(e.data);

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

    goDeeper(path){
        let json = {function: "getFilesFolders", path: path};
        this.sendJson(json);
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
