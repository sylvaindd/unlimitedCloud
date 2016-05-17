import { Component } from '@angular/core';
import {Connection} from "./Connection";
import {$WebSocket} from 'angular2-websocket/angular2-websocket'

@Component({
    selector: "app",
    templateUrl: "app/app.html"
})
export class App {

    private ws:$WebSocket;

    constructor(){
        this.ws = new $WebSocket("ws://127.0.0.1:8080/rest");
        this.ws.send("connect");
    }
}