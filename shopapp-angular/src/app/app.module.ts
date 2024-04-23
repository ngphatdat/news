import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HomeComponent} from './Components/home/home.component';
import {HeaderComponent} from './Components/header/header.component';
import {FooterComponent} from './Components/footer/footer.component';
import {DetailProductComponent} from './Components/detail-product/detail-product.component';
import {OrderComponent} from './Components/order/order.component';
import {OrderConfirmComponent} from './Components/order-confirm/order-confirm.component';
import {LoginComponent} from './Components/login/login.component';
import {RegisterComponent} from './Components/register/register.component';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule, HttpHeaders, HttpInterceptor} from '@angular/common/http'
import {Router, RouterModule} from '@angular/router';
import { tokenIntercreptor } from './Intercreptors/token.intercreptor';
import { AppComponent } from './app/app.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    DetailProductComponent,
    OrderComponent,
    OrderConfirmComponent,
    LoginComponent,
    RegisterComponent,
    AppComponent
  ],
  imports: [
    BrowserModule
    , FormsModule
    , RouterModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [
    {

    provide: HTTP_INTERCEPTORS,
    useClass: tokenIntercreptor,
    multi: true 
  }
  ],
  bootstrap: [
    // HomeComponent,
    // DetailProductComponent,
    // OrderComponent,
    // OrderConfirmComponent,
    // LoginComponent,
    // RegisterComponent,
    AppComponent
  ]
})
export class AppModule {
}
