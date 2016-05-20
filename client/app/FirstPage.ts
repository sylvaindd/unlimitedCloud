import {Component} from '@angular/core';
import {Tabs} from "./Tabs";
import {Tab} from "./Tab";
import {Connection} from "./Connection";
import {Register} from "./Register";
import {MainPage} from "./MainPage";


@Component({
    selector: "firstPage",
    templateUrl: "app/html/firstPage.html",
    directives: [Tabs, Tab, Connection, Register]
})

export class FirstPage {

    constructor() {
    }

}
