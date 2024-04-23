import { RouterModule, Routes } from "@angular/router";
import { DetailProductComponent } from "./Components/detail-product/detail-product.component";
import { HomeComponent } from "./Components/home/home.component";
import { LoginComponent } from "./Components/login/login.component";
import { OrderComponent } from "./Components/order/order.component";
import { RegisterComponent } from "./Components/register/register.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'home', component: HomeComponent },
{ path: 'login', component: LoginComponent },
 { path: 'register', component: RegisterComponent }, 
 { path: 'products/:id', component: DetailProductComponent },
  { path: 'orders', component: OrderComponent },
  { path: 'orders/:id', component: OrderComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule{


}