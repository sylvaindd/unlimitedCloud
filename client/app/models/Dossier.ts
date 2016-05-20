import {Fichier} from "./Fichier";

export class Dossier {
    private name:String;
    private fichiers:Array<Fichier>;
    private dossiers:Array<Dossier>;
    private dateModif:Date;
    private taille:number;

    constructor(name:String, taille:number) {
        this.name = name;
        this.taille = taille;
        this.dossiers = new Array<Dossier>();
        this.fichiers = new Array<Fichier>();
    }
}