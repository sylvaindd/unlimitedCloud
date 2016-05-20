import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {Dossier} from "./models/Dossier";
import {Fichier} from "./models/Fichier";

@Component({
    selector: "toolbarDetails",
    templateUrl: "app/html/toolbarDetails.html",
    directives: [ROUTER_DIRECTIVES]
})

export class ToolbarDetails {
    dossier:Dossier
    fichier:Fichier

    constructor() {
        this.dossier = new Dossier("dossier", 20000);
        this.fichier = new Fichier("fichier", 1000);
    }
}