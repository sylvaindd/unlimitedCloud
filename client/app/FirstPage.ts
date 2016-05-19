import {Component} from '@angular/core';
import {Explorer} from "./Explorer";
import {Tabs} from "./Tabs";
import {Tab} from "./Tab";
import {Connection} from "./Connection";
import {Register} from "./Register";
import {Routes , Router} from '@angular/router';

@Component({
    selector: "firstPage",
    templateUrl: "app/html/firstPage.html",
    directives: [Tabs, Tab, Connection, Register],
    // providers: [Router]
})

// @Routes([
//     {path: '/Explorer', component: Explorer},
// ])

export class FirstPage {

    mdp:String;
    adresse:String;

    constructor() {
        this.adresse = "";
        this.mdp = "";
    }

    // onSubmit() {
    //     if (this.adresse != "" && this.adresse.indexOf("@") > -1 && this.mdp != "") {
    //         console.log("Submit ! " + this.adresse + " - " + this.mdp);
    //         this.router.navigate(['/Explorer']);
    //     }
    // }
}
