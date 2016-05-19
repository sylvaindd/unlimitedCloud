import { Component, Input } from '@angular/core';
import {Fichier} from "./models/Fichier";

@Component({
    selector: "fichierComponent",
    templateUrl: "app/html/fichier.html"
})
export class FichierComponent {

    @Input() fichier:Fichier;
    
    constructor(){
        
    }
}