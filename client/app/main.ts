import {bootstrap} from '@angular/platform-browser-dynamic'
import {HTTP_PROVIDERS} from '@angular/http';
import { ROUTER_PROVIDERS } from '@angular/router';
import {Explorer} from "./Explorer";
// Dependencies for HTTP service
import 'rxjs/Rx'; // For using methods on observables
import {App} from "./App";
import {Connection} from "./Connection";
import {FirstPage} from "./FirstPage";

bootstrap(FirstPage, [HTTP_PROVIDERS]);
// bootstrap(Explorer, [HTTP_PROVIDERS]);

