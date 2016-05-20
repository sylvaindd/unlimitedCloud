import {Router} from '@angular/router';

export class Actions{
    constructor(private router:Router){
    }

    selectAction(id:number){
        switch (id){
            case 1:
                this.onRenameFolder();
                break;
            case 2:
                this.onDeleteFolder();
                break;
            case 3:
                this.detailsFolder();
                break;
            case 4:
                this.onRenameFile();
                break;
            case 5:
                this.onDeleteFile();
                break;
            case 6:
                this.detailsFile();
                break;
        }
    }
    
    onRenameFolder(){
        console.log("rename folder")
    }

    onDeleteFolder(){
        console.log("delete folder")
    }

    detailsFolder(){
        this.router.navigate(['/App/Explorer/Details']);
        console.log("details folder")
    }

    onRenameFile(){
        console.log("rename File")
    }

    onDeleteFile(){
        console.log("delete File")
    }

    detailsFile(){
        this.router.navigate(['/App/Explorer/Details']);
        console.log("details File")
    }
}