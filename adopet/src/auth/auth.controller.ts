import { Body, Controller, Post } from '@nestjs/common';
import { AuthService } from './auth.service';
import { DtoAuth } from './dto';

@Controller('auth')
export class AuthController {
    constructor(private authService:AuthService){
    }
    @Post('signin')
    signin(@Body()dto:DtoAuth){
        return this.authService.signin(dto);
    };
    @Post('login')
    login(@Body()dto:DtoAuth){
        return this.authService.login(dto);
    }
}
