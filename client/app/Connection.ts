import {Component} from '@angular/core';
import {Explorer} from "./Explorer";
import {Tabs} from "./Tabs";
import {Tab} from "./Tab";
import {Routes , Router} from '@angular/router';

@Component({
    selector: "connection",
    templateUrl: "app/html/connection.html",
    directives: [Tabs, Tab]
    // providers: [Router]
})

// @Routes([
//     {path: '/Explorer', component: Explorer},
// ])

export class Connection {

    mdp:String;
    adresse:String;

    constructor() {
        this.adresse = "";
        this.mdp = "";
    }

    onSubmit() {
        if (this.adresse != "" && this.adresse.indexOf("@") > -1 && this.mdp != "") {
            console.log("Submit ! " + this.adresse + " - " + this.mdp);
            this.router.navigate(['/Explorer']);
        }
    }
}
