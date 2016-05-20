import {ContextContainer} from "../utils/ContextContainer";
import { Injectable } from '@angular/core';

@Injectable()
export class WebSocketService {

    public ws:WebSocket;

    public callBackDropboxAdded;
    public callBackGetFiles;

    constructor(private contextContainer:ContextContainer) {

    }

    init(){
        this.ws = new WebSocket("ws://localhost:8080/lebonnuage/socket");

        this.ws.onopen = function () {
            console.log('SOCKET : Connection open!');
            let json  = {token : this.contextContainer.token};
            this.ws.send(JSON.stringify(json));
        }.bind(this);

        this.ws.onclose = function () {
            console.log('SOCKET : Connection closed');
        }

        this.ws.onerror = function (error) {
            console.log('SOCKET : Error detected: ' + error);
        }

        this.ws.onmessage = function (e) {
            var server_message = e.data;
            console.log("SOCKET : " + server_message);


            switch(e.data.function){
                case "dropboxAdded":
                    this.callBackDropboxAdded(e.data.data);
                    break;
                case "getFilesFolders":
                    this.callBackGetFiles(e.data.data);
                    break;

            }
        }
    }

    askForFiles(){
        let json = {function: "getFilesFolders", path : ""};
        this.sendJson(json);
    }

    sendJson(json){
        this.ws.send(JSON.stringify(json));
    }
}
