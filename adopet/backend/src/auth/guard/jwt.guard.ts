import { AuthGuard } from "@nestjs/passport";

export class JwtGuards extends AuthGuard('jwt'){
    constructor(){
        super()
    }
}