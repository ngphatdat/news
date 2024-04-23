import {Component, ViewChild} from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import {Login} from '../../dtos/login.dtos';
import {Router, RouterLink} from '@angular/router';
import {LoginService} from '../../service/login.service';
import { TokenService } from 'src/app/service/token.service';

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

  constructor(private router: Router,
    private tokenService: TokenService, 
    private loginService: LoginService) {
  }

  login_button() {
    alert('đã ấn login');
    this.loginService.LoginUser(this.login).subscribe({
      next: (response: any) => {{
           const {token} = response;
          this.tokenService.setToken(token);        
          this.router.navigate(['/home']);
        alert(response.mess);
      }
      },
      error(error) {
        console.error('Error occurred:', error);
        // Xử lý lỗi ở đây nếu cần
        debugger;
    
      },
      complete() {
        debugger
        console.log('Request completed');
      }

    });

  }
}
