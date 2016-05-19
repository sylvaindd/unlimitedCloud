import {Component} from '@angular/core';
import {Tabs} from "./Tabs";
import {Tab} from "./Tab";
import {Connection} from "./Connection";
import {App} from "./App";
import {Register} from "./Register";
import { Router, ROUTER_DIRECTIVES, Routes } from '@angular/router';

@Component({
    selector: "firstPage",
    templateUrl: "app/html/firstPage.html",
    directives: [Tabs, Tab, Connection, Register, ROUTER_DIRECTIVES]
})

@Routes([
    {path: '/App', component: App}
])

export class FirstPage {

    mdp:String;
    adresse:String;

    constructor(private router: Router) {
        this.adresse = "";
        this.mdp = "";
    }

    onSubmit() {
        this.router.navigate(['/App']);
    }
}
