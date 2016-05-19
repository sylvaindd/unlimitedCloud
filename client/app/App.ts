import { Component } from '@angular/core';
import {Connection} from "./Connection";

@Component({
    selector: "app",
    templateUrl: "app/html/app.html",
    directives:[Connection]
})
export class App {

    private ws:WebSocket;
    constructor(){
        this.ws = new WebSocket("ws://127.0.0.1:8080/REST/");

        this.ws.onopen = function(){
            console.log('Connection open!');
        }

        this.ws.onclose = function(){
            console.log('Connection closed');
        }

        this.ws.onerror = function(error){
            console.log('Error detected: ' + error);
        }

        this.ws.onmessage = function(e){
            var server_message = e.data;
            console.log(server_message);
        }
    }
}