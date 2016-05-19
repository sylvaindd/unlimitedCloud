import {Component} from '@angular/core';
import {Tab} from './Tab';

@Component({
    selector: 'tabs',
    templateUrl: "app/html/tabs.html",
})
export class Tabs {

    tabs:Tab[];

    constructor() {
        this.tabs = [];
    }

    selectTab(tab) {

        this.tabs.forEach((tab)=>tab.active = false);
        tab.active = true;
    }

    addTab(tab:Tab) {
        if (this.tabs.length === 0) {
            tab.active = true;
        }
        this.tabs.push(tab);
    }
}
