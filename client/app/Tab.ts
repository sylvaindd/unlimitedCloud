import {Component} from '@angular/core';
import {Tabs} from './Tabs';

@Component({
    selector: 'tab',
    inputs: [
        'title:tab-title',
        'active'
    ],
    templateUrl: "app/html/tabs.html"
})
export class Tab {
    title:string;
    active = this.active || false;

    constructor(tabs:Tabs) {

        tabs.addTab(this);

    }
}