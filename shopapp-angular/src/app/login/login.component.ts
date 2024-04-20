import {Component, ViewChild} from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import {Login} from '../dtos/login.dtos';
import {Router} from '@angular/router';
import {LoginService} from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm!: NgForm;
  login: Login = {
    phone_number: '',
    password: '',
    role_id: 1
  };

  constructor(private router: Router, private loginService: LoginService) {
  }

  login_button() {
    alert('đã ấn login');
    this.loginService.registerUser(this.login).subscribe({
      next: (response: any) => {
        if (response && (response.status == 200 || response.status == 201)) {
          const {token} = response;
          if (token) {
            // Lưu token vào sessionStorage
            sessionStorage.setItem('token', token);
            console.log('Token saved to sessionStorage:', token);
          } else {
            console.error('No token found in the response headers');
          }
          this.router.navigate(['/home']);
        }

      },
      error(error) {
        console.error('Error occurred:', error);
        // Xử lý lỗi ở đây nếu cần
      },
      complete() {
        debugger
        console.log('Request completed');
        // Xử lý khi yêu cầu hoàn thành nếu cần
      }

    });

  }
}
