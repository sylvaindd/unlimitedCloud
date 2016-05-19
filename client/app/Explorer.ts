import { Component } from '@angular/core';
import {DossierComponent} from "./DossierComponent";
import {FichierComponent} from "./FichierComponent";
import {Dossier} from "./models/Dossier";
import {Fichier} from "./models/Fichier";

@Component({
    selector: "explorer",
    templateUrl: "app/html/explorer.html",
    directives:[DossierComponent, FichierComponent]
})



export class Explorer {

    private dossiers:Array<Dossier>;
    private fichiers:Array<Fichier>;
    
    constructor(){
        this.dossiers = new Array<Dossier>();
        this.fichiers = new Array<Fichier>();
        this.initTest();
    }

    initTest()
    {
        for(let i:number = 0 ; i < 19 ; i++ )
        {
            this.dossiers.push(new Dossier("dossier"+i));
        }
        for(let i:number = 0 ; i < 30 ; i++ )
        {
            this.fichiers.push(new Fichier("fichier"+i));
        }
    }
}