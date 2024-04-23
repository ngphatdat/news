import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Login} from '../dtos/login.dtos';

@Injectable({
  providedIn: 'root'
})
export class TokenService{

    private readonly TOKEN_KEY = 'access_token';
    constructor(){}
    getToken():string|null{
        return localStorage.getItem(this.TOKEN_KEY);
    }
    setToken(token:string){
        localStorage.setItem(this.TOKEN_KEY,token);
    }
    removeToken () : void {
        localStorage.removeItem(this.TOKEN_KEY) ;
    }
}