import { Controller, Get, Req, UseGuards } from '@nestjs/common';
import { JwtGuards } from 'src/auth/guard';

@Controller('user')
export class UserController {
    @UseGuards(JwtGuards)
    @Get('me')
    getme(@Req() req:any){
        return req.user;
    }
}
