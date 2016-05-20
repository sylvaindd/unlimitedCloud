import {ContextContainer} from "../utils/ContextContainer";
import { Injectable } from '@angular/core';

@Injectable()
export class WebSocketService {

    public ws:WebSocket;

    public callBackDropboxAdded;

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


            if (e.data.func == "dropboxAdded") {
                this.callBackDropboxAdded(e.data.data);
            }

        }
    }
}
