import { Component } from '@angular/core';
import {Drawer} from "./Drawer";
import {Dossier} from "./models/Dossier";
import {Fichier} from "./models/Fichier";

@Component({
    selector: "explorer",
    templateUrl: "app/html/explorer.html",
    directives:[Drawer]
})
export class Explorer {

    private dossiers:Array<Dossier>;
    private fichiers:Array<Fichier>;
    
    constructor(){
        this.dossiers = new Array<Dossier>();
        this.fichiers = new Array<Fichier>();
    }
}