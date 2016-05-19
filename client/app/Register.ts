import {Component} from '@angular/core';
import {Explorer} from "./Explorer";
import {Routes , Router} from '@angular/router';
@Component({
    selector: "register",
    templateUrl: "app/html/register.html"
    // providers: [Router]
})

// @Routes([
//     {path: '/Explorer', component: Explorer},
// ])

export class Register {

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
