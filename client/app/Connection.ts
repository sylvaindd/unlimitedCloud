import { Component } from '@angular/core';
import {SocialConnection} from "./SocialConnection";

@Component({
    selector: "connection",
    templateUrl: "app/html/connection.html",
    directives:[SocialConnection]
})
export class Connection {

    mdp:String;
    adresse:String;

    constructor(){
        this.adresse = "";
        this.mdp = "";
    }

    onSubmit()
    {
         if(this.adresse != "" && this.adresse.indexOf("@") > -1 && this.mdp != "")
          {
              console.log("Submit ! " + this.adresse + " - " + this.mdp);
          }
    }
}
