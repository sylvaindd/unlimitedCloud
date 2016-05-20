import {Component} from '@angular/core';
import {Dossier} from "./models/Dossier";
import {Fichier} from "./models/Fichier";

@Component({
    selector: "toolbarDetails",
    templateUrl: "app/html/toolbarDetails.html"
})

export class ToolbarDetails {
    dossier:Dossier
    fichier:Fichier

    constructor() {
        
    }
}