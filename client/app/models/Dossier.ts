import {Fichier} from "./Fichier";

export class Dossier {
    public name:String;
    public fichiers:Array<Fichier>;
    public dossiers:Array<Dossier>;
    public dateModif:Date;
    public taille:number;

    constructor(name:String, taille:number) {
        this.name = name;
        this.taille = taille;
        this.dossiers = new Array<Dossier>();
        this.fichiers = new Array<Fichier>();
    }
}