export class Fichier {

    public name:String;
    public dateModif:Date;
    public id:number;
    public taille:number;
    public type:String;

    constructor(id:number, name:String, type:String) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

}