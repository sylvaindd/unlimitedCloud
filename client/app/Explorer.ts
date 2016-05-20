import { Component } from '@angular/core';
import { Router, ROUTER_DIRECTIVES, Routes} from '@angular/router';
import {DossierComponent} from "./DossierComponent";
import {FichierComponent} from "./FichierComponent";
import {Dossier} from "./models/Dossier";
import {Fichier} from "./models/Fichier";
import {Upload} from "./Upload";
import {ToolbarExplorer} from "./ToolbarExplorer";

@Component({
    selector: "explorer",
    templateUrl: "app/html/explorer.html",
    directives:[DossierComponent, FichierComponent, ROUTER_DIRECTIVES]
})

@Routes([
    {path: '/', component: ToolbarExplorer},
    {path: '/Upload', component: Upload}
])

export class Explorer {

    private dossiers:Array<Dossier>;
    private fichiers:Array<Fichier>;
    
    constructor(private router:Router){
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