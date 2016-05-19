import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class APIService {

    constructor(private http:Http) {

    }


    authenticate(username, password) {
        var body = "username=" + username + "&password=" + password;
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        let result, error, data;

        return this.http
            .post('http://localhost:8080/sessions/create',
                body, {
                    headers: headers
                })
            .map(response => response.json())
            .subscribe(
                response => {
                    console.log(data);
                    data = response;
                },
                err => error = err.json().message,
                () => console.log('Authentication Complete')
            );
    }

    getPokemon(id:number) {
        return this.http.get('http://pokeapi.co/api/v2/pokemon/' + id + '/')
            .toPromise()
            .then((response) => response.json());
    }
}