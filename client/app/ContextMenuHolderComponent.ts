import {Component,Directive,Input} from '@angular/core';
import {ContextMenuService} from './services/ContextMenuService';
import {Actions} from './models/Actions';
import {Subject} from 'rxjs/Rx';

@Component({
    selector:'context-menu-holder',
    styles:[
        '.container{width:150px;background-color:#eee}',
        '.link{}','.link:hover{background-color:#abc}',
        'ul{margin:0px;padding:0px;list-style-type: none}'
    ],
    host:{
        '(document:click)':'clickedOutside()'
    },
    template:
        `<div [ngStyle]="locationCss" class="container">
      <ul>
        <li (click)="selectAction(link.idaction)" class="link" *ngFor="let link of links">
          {{link.title}}
        </li>
      </ul>
    </div>
  `
})
export class ContextMenuHolderComponent{
    links = [];
    isShown = false;
    private mouseLocation :{left:number,top:number} = {left:0,top:0};
constructor(private _contextMenuService:ContextMenuService){
    _contextMenuService.show.subscribe(e => this.showMenu(e.event,e.obj));
}

get locationCss(){
    return {
        'position':'fixed',
        'display':this.isShown ?  'block':'none',
    left:this.mouseLocation.left + 'px', 
        top:this.mouseLocation.top + 'px'
};
}
clickedOutside(){
    this.isShown= false
}

showMenu(event,links){
    this.isShown = true;
    this.links = links;
    this.mouseLocation = {
        left:event.clientX,
        top:event.clientY
    }
}
    selectAction(action:number){
        new Actions().selectAction(action);
    }
}

@Directive({
    selector:'[context-menu]',
    host:{'(contextmenu)':'rightClicked($event)'}
})
export class ContextMenuDirective{
    @Input('context-menu') links;
    constructor(private _contextMenuService:ContextMenuService){
    }
    rightClicked(event:MouseEvent){
        this._contextMenuService.show.next({event:event,obj:this.links});
        event.preventDefault();
    }
}

