import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Login} from '../dtos/login.dtos';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
  }

  LoginUser(registerData: Login): Observable<any> {
    const urlApi = "http://localhost:8080/api/v1/user/login";
    const header = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(urlApi, registerData, {headers: header});
  }
}
