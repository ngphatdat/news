import {Component, Injectable, ViewChild, inject} from '@angular/core';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgForm} from '@angular/forms';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import {Router} from '@angular/router';
import {UserService} from '../service/user';
import {RegisterDto} from '../dtos/register.dto';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  @ViewChild('registerForm') registerForm!: NgForm;
  registerDto: RegisterDto = {
    full_name: '',
    phone_number: '',
    address: '',
    password: '',
    retype_password: '',
    date_of_birth: new Date(),
    facebook_accountId: 0,
    google_accountId: 0,
    role_id: 1
  };

  constructor(private router: Router, private userService: UserService) {

  }


  onPhoneChange() {
    console.log(this.registerDto.phone_number);
  }

  register() {
    alert('đã gửi form đăng kí');

    this.userService.registerUser(this.registerDto).subscribe({
      next: (response: any) => {
        if (response && (response.status == 200 || response.status == 201)) {
          this.router.navigate(['/login'])
        } else {
          // Xử lý trường hợp phản hồi không thành công
        }
      },
      error(error) {
        console.error('Error occurred:', error);
        // Xử lý lỗi ở đây nếu cần
      },
      complete() {
        console.log('Request completed');
        // Xử lý khi yêu cầu hoàn thành nếu cần
      }

    });
  }
}
