import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {ContextContainer} from "../utils/ContextContainer";

@Injectable()
export class APIService {

    constructor(private http:Http, private contextContainer: ContextContainer) {

    }

    authent(mailOrUsername, password, callback) {

        var body = {mailOrUsername: mailOrUsername, password: password};
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        let result, error, data;

        this.http
            .post('http://localhost:8080/lebonnuage/authent',
                JSON.stringify(body), {
                    headers: headers
                })
            .map(response => response.json())
            .subscribe(
                response => callback(response),
                err => error = err.json().message,
                () => console.log('Authentication Complete')
            );
    }

    register(username, mail, phone, password, callback) {

        var body = {username: username, mail: mail, phone: phone, password: password};

        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        let result, error, data;

        this.http
            .post('http://localhost:8080/lebonnuage/register',
                JSON.stringify(body), {
                    headers: headers
                })
            .map(response => response.json())
            .subscribe(
                response => callback(response),
                err => error = err.json().message,
                () => console.log('Authentication Complete')
            );
    }

    getListFiles(username, mail, phone, password, callback) {

        var body = {username: username, mail: mail, phone: phone, password: password};

        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        let result, error, data;

        this.http
            .post('http://localhost:8080/lebonnuage/register',
                JSON.stringify(body), {
                    headers: headers
                })
            .map(response => response.json())
            .subscribe(
                response => callback(response),
                err => error = err.json().message,
                () => console.log('Authentication Complete')
            );
    }

}