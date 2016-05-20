import {Fichier} from "./Fichier";

export class Dossier {
    public name:String;
    public fichiers:Array<Fichier>;
    public dossiers:Array<Dossier>;
    public dateModif:Date;
    public id:number;
    public type:String;
    public taille:number;

    constructor(id:number, name:String, type:String) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.dossiers = new Array<Dossier>();
        this.fichiers = new Array<Fichier>();
    }
}