import {Component} from '@angular/core';
import {Explorer} from "./Explorer";
import {Settings} from "./Settings";
import {ContextContainer} from "./utils/ContextContainer";
import {Router, ROUTER_DIRECTIVES, Routes} from '@angular/router';
/// <reference path="./models/jquery.d.ts" />
import {WebSocketService} from "./services/WebSocketService";

declare var jQuery:JQueryStatic;

@Component({
    selector: "app",
    templateUrl: "app/html/app.html",
    directives: [Explorer, ROUTER_DIRECTIVES]
})

@Routes([
    {path: '/', component: Explorer},
    {path: '/Explorer', component: Explorer},
    {path: '/Settings', component: Settings}
])


export class App {

    constructor(private router:Router, private contextContainer:ContextContainer, private webSocketService:WebSocketService) {

        console.log("Token : " + this.contextContainer.token);
        this.webSocketService.init()
    }

    ngAfterViewInit() {
        jQuery("ul.toolbarTop li").click(function () {
            jQuery("ul.toolbarTop li").each(function () {
                jQuery(this).removeClass("active");
            });
            jQuery(this).addClass("active");
        });
    }

    ngOnInit() {
        this.router.navigate(['App/Explorer']);
    }

    deconnect() {
        this.contextContainer.token = "";
        this.webSocketService.ws.close();
    }
}