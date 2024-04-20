export interface RegisterDto {
  full_name: string;
  phone_number: string;
  address: string;
  password: string;
  retype_password: string;
  date_of_birth: Date;
  facebook_accountId: number;
  google_accountId: number;
  role_id: number;
}
