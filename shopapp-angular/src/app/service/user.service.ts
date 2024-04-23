import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RegisterDto} from '../dtos/register.dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  registerUser(registerData: RegisterDto): Observable<any> {
    const urlApi = "http://localhost:8080/api/v1/user/register";
    const header = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(urlApi, registerData, {headers: header});

  }
}
