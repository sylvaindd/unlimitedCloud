import {Component} from '@angular/core';
import {Explorer} from "./Explorer";
import {Routes , Router} from '@angular/router';

@Component({
    selector: "connection",
    templateUrl: "app/html/connection.html"
})

@Routes([
    {path: '/Connection', component: Connection},
    {path: '/Explorer', component: Explorer},
])

export class Connection {

    mdp:String;
    adresse:String;

    constructor(private router:Router) {
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
