import { Component } from '@angular/core';

@Component({
    selector: "connection",
    templateUrl: "app/html/connection.html"
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
