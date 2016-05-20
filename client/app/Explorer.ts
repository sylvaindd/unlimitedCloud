import {Component, Injectable} from '@angular/core';
import {Router, ROUTER_DIRECTIVES, Routes} from '@angular/router';
import {DossierComponent} from "./DossierComponent";
import {FichierComponent} from "./FichierComponent";
import {Dossier} from "./models/Dossier";
import {Fichier} from "./models/Fichier";
import {Upload} from "./Upload";
import {ToolbarExplorer} from "./ToolbarExplorer";
import {ContextMenuHolderComponent, ContextMenuDirective} from "./ContextMenuHolderComponent";
import {ToolbarDetails} from "./ToolbarDetails";
import {WebSocketService} from "./services/WebSocketService";
import {getFileName} from "gulp-typescript/release/tsapi";


@Component({
    selector: "explorer",
    templateUrl: "app/html/explorer.html",
    directives: [ContextMenuDirective, ContextMenuHolderComponent, DossierComponent, FichierComponent, ROUTER_DIRECTIVES]
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

    constructor(private router:Router, private webSocketService:WebSocketService) {
        this.dossiers = new Array<Dossier>();
        this.fichiers = new Array<Fichier>();
        this.linkFolder = [
            {title: 'Rennomer', idaction: 1},
            {title: 'Suppimer', idaction: 2},
            {title: 'Details', idaction: 3}
        ];
        this.linkFile = [
            {title: 'Rennomer', idaction: 4},
            {title: 'Suppimer', idaction: 5},
            {title: 'Details', idaction: 6}
        ];

        if (this.webSocketService.isConnected) {
            this.getFilesAndFolder();
        } else {
            this.webSocketService.callBackConnected = function () {
                this.getFilesAndFolder();
            }.bind(this);
        }

        this.webSocketService.callBackGetFiles = function (data) {
            this.dossiers = new Array<Dossier>();
            this.fichiers = new Array<Fichier>();
            for (let folder of data.folders) {
                this.dossiers.push(new Dossier(folder.id, folder.nom, folder.type));
            }
            for (let file of data.files) {
                this.fichiers.push(new Fichier(file.id, file.nom, file.type));
            }
        }.bind(this);
    }

    getFilesAndFolder() {
        this.webSocketService.askForFiles();
    }

    initTest() {
        for (let i:number = 0; i < 19; i++) {
            this.dossiers.push(new Dossier("dossier" + i, 100));
        }
        for (let i:number = 0; i < 30; i++) {
            this.fichiers.push(new Fichier("fichier" + i, 20));
        }
    }
}