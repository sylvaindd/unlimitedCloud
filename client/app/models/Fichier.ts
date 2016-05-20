export class Fichier {
    
    private name:String;
    private dateModif:Date;
    private taille:number;

    constructor(name:String, taille:number) {
        this.name = name;
        this.taille = taille;
    }

}