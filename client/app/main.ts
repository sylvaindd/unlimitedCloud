import {bootstrap} from '@angular/platform-browser-dynamic'
import {HTTP_PROVIDERS} from '@angular/http';
import { ROUTER_PROVIDERS} from '@angular/router';
// Dependencies for HTTP service
import 'rxjs/Rx'; // For using methods on observables
import {MainPage} from "./MainPage";
import {ContextContainer} from "./utils/ContextContainer";
import {WebSocketService} from "./services/WebSocketService";
import {ContextMenuService} from "./services/ContextMenuService";

bootstrap(MainPage, [HTTP_PROVIDERS, ROUTER_PROVIDERS, ContextContainer, [ContextMenuService]]);
bootstrap(MainPage, [HTTP_PROVIDERS, ROUTER_PROVIDERS, ContextContainer, WebSocketService]);

