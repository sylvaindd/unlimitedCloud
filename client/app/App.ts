import { Component } from '@angular/core';
import {Connection} from "./Connection";
import {$WebSocket} from 'angular2-websocket/angular2-websocket'

@Component({
    selector: "app",
    templateUrl: "app/app.html"
})
export class App {

    constructor(){
        var ws = new $WebSocket("127.0.0.1:8080/rest");
        ws.send("connect");
    }
}