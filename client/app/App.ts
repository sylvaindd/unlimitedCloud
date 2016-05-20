import { Component } from '@angular/core';
import {Explorer} from "./Explorer";
import {Settings} from "./Settings";
import {ContextContainer} from "./utils/ContextContainer";
import { Router, ROUTER_DIRECTIVES, Routes} from '@angular/router';
/// <reference path="./models/jquery.d.ts" />

declare var jQuery: JQueryStatic;

@Component({
    selector: "app",
    templateUrl: "app/html/app.html",
    directives:[Explorer, ROUTER_DIRECTIVES]
})

@Routes([
    {path: '/', component: Explorer},
    {path: '/Explorer', component: Explorer},
    {path: '/Settings', component: Settings}
])


export class App {

    private ws:WebSocket;
    constructor(private router: Router, private contextContainer: ContextContainer){
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

        console.log("Token : " + this.contextContainer.token);
    }

    ngAfterViewInit(){
        jQuery("ul.toolbarTop li").click(function(){
            jQuery("ul.toolbarTop li").each(function(){
                jQuery(this).removeClass("active");
            });
            jQuery(this).addClass("active");
        });
    }

    ngOnInit() {
        this.router.navigate(['App/Explorer']);
    }
}