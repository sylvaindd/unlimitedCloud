import { Component, Input } from '@angular/core';
import {Dossier} from "./models/Dossier";

@Component({
    selector: "dossierComponent",
    templateUrl: "app/html/dossier.html"
})
export class DossierComponent {

    @Input() dossier:Dossier;
    
    constructor(){

    }
}