import { Component } from '@angular/core';
import { Router, ROUTER_DIRECTIVES, Routes} from '@angular/router';
import {DossierComponent} from "./DossierComponent";
import {FichierComponent} from "./FichierComponent";
import {Dossier} from "./models/Dossier";
import {Fichier} from "./models/Fichier";
import {Upload} from "./Upload";
import {ToolbarExplorer} from "./ToolbarExplorer";
import {ContextMenuHolderComponent, ContextMenuDirective} from "./ContextMenuHolderComponent";
import {ToolbarDetails} from "./ToolbarDetails";

@Component({
    selector: "explorer",
    templateUrl: "app/html/explorer.html",
    directives:[ContextMenuDirective, ContextMenuHolderComponent, DossierComponent, FichierComponent, ROUTER_DIRECTIVES]
})

@Routes([
    {path: '/', component: ToolbarExplorer},
    {path: '/Upload', component: Upload},
    {path: '/Details', component: ToolbarDetails}
])

export class Explorer {

    private dossiers:Array<Dossier>;
    private fichiers:Array<Fichier>;

    currentFileId:number;
    currentFolderId:number;

    linkFolder;
    linkFile;

    constructor(private router:Router){
        this.dossiers = new Array<Dossier>();
        this.fichiers = new Array<Fichier>();
        this.initTest();
        this.linkFolder = [
            {title:'Rennomer', idaction: 1},
            {title:'Suppimer', idaction: 2},
            {title:'Details', idaction: 3}
        ];
        this.linkFile = [
            {title:'Rennomer', idaction: 4},
            {title:'Suppimer', idaction: 5},
            {title:'Details', idaction: 6}
        ];
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